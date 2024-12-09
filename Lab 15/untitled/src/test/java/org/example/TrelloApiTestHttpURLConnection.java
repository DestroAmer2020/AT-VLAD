package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloApiTestHttpURLConnection {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String API_KEY = "7abaac4cf876c6b6282f8a1d29aa9fba";
    private static final String API_TOKEN = "ATTA8a3e46a07fa8f1ae69f4eb4ec4f1f6b3e0c721ccdb52bc56054caea671d9efc51040DFCE";

    @Test
    public void testTrelloAPI() throws IOException {
        // Step 1: Create a Board
        String boardId = createBoard("TestBoard");

        // Step 2: Get List ID
        String listId = getFirstListId(boardId);

        // Step 3: Create a Card
        String cardId = createCard(listId, "TestCard");

        // Step 4: Update Card Description
        updateCardDescription(cardId, "UpdatedDescription");

        // Step 5: Delete the Card
        deleteCard(cardId);
    }

    private String createBoard(String boardName) throws IOException {
        String url = BASE_URL + "/boards?name=" + boardName + "&key=" + API_KEY + "&token=" + API_TOKEN;
        String response = sendPostRequest(url);
        // Extract and return the boardId from the response
        return response.split("\"id\":\"")[1].split("\"")[0];
    }

    private String getFirstListId(String boardId) throws IOException {
        String url = BASE_URL + "/boards/" + boardId + "/lists?key=" + API_KEY + "&token=" + API_TOKEN;
        String response = sendGetRequest(url);
        // Extract and return the listId from the response
        return response.split("\"id\":\"")[1].split("\"")[0];
    }

    private String createCard(String listId, String cardName) throws IOException {
        String url = BASE_URL + "/cards?idList=" + listId + "&name=" + cardName + "&key=" + API_KEY + "&token=" + API_TOKEN;
        String response = sendPostRequest(url);
        // Extract and return the cardId from the response
        return response.split("\"id\":\"")[1].split("\"")[0];
    }

    private void updateCardDescription(String cardId, String description) throws IOException {
        String url = BASE_URL + "/cards/" + cardId + "?desc=" + description + "&key=" + API_KEY + "&token=" + API_TOKEN;
        String response = sendPutRequest(url);
        assertEquals(response.contains("\"desc\":\"" + description + "\""), true);
    }

    private void deleteCard(String cardId) throws IOException {
        String url = BASE_URL + "/cards/" + cardId + "?key=" + API_KEY + "&token=" + API_TOKEN;
        sendDeleteRequest(url);
    }

    private String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        return getResponse(connection);
    }

    private String sendPostRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setDoOutput(true);
        return getResponse(connection);
    }

    private String sendPutRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("PUT");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setDoOutput(true);
        return getResponse(connection);
    }

    private void sendDeleteRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("DELETE");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.getResponseCode(); // Just send the request
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }
}
