package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class TrelloAPITest {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String API_KEY = "7abaac4cf876c6b6282f8a1d29aa9fba";
    private static final String API_TOKEN = "ATTA8a3e46a07fa8f1ae69f4eb4ec4f1f6b3e0c721ccdb52bc56054caea671d9efc51040DFCE";

    @Test
    public void testTrelloAPI() {
        // Step 1: Create a Board
        Response createBoardResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("name", "Test Board")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .post(BASE_URL + "/boards")
                .then()
                .statusCode(200)
                .extract().response();
        String boardId = createBoardResponse.jsonPath().getString("id");

        // Step 2: Get the first List ID
        Response listResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .get(BASE_URL + "/boards/" + boardId + "/lists")
                .then()
                .statusCode(200)
                .extract().response();
        String listId = listResponse.jsonPath().getString("[0].id");

        // Step 3: Create a Card
        Response createCardResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("idList", listId)
                .queryParam("name", "Test Card")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .post(BASE_URL + "/cards")
                .then()
                .statusCode(200)
                .extract().response();
        String cardId = createCardResponse.jsonPath().getString("id");

        // Step 4: Update Card Description
        RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("desc", "Updated Description")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .put(BASE_URL + "/cards/" + cardId)
                .then()
                .statusCode(200)
                .body("desc", equalTo("Updated Description"));

        // Step 5: Delete the Card
        RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .delete(BASE_URL + "/cards/" + cardId)
                .then()
                .statusCode(200);
    }

}
