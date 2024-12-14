import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITests {

    @Test
    public void testCreateResource() {
        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"testResource\"}")
                .post("http://example.com/api/resource");
        Assert.assertEquals(response.getStatusCode(), 201);
        String id = response.jsonPath().getString("id");

        Response getResponse = given()
                .get("http://example.com/api/resource/" + id);
        Assert.assertEquals(getResponse.getStatusCode(), 200);
        Assert.assertEquals(getResponse.jsonPath().getString("name"), "testResource");
    }

    @Test
    public void testUpdateResource() {
        String resourceId = "123";
        given()
                .contentType("application/json")
                .body("{\"name\": \"updatedName\"}")
                .put("http://example.com/api/resource/" + resourceId)
                .then()
                .statusCode(200);

        Response getResponse = given()
                .get("http://example.com/api/resource/" + resourceId);
        Assert.assertEquals(getResponse.jsonPath().getString("name"), "updatedName");
    }

    @Test
    public void testDeleteResource() {
        String resourceId = "123";
        given()
                .delete("http://example.com/api/resource/" + resourceId)
                .then()
                .statusCode(204);

        given()
                .get("http://example.com/api/resource/" + resourceId)
                .then()
                .statusCode(404);
    }
}