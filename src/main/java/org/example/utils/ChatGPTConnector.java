package org.example.utils;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.utils.models.Choice;
import org.example.utils.models.TextCompletion;

import java.io.IOException;
import java.util.*;

public class ChatGPTConnector {

    private static final String API_KEY = System.getenv("APIKEY");
    private static final String MODEL_ID = "text-davinci-003";
    static String model = "gpt-3.5-turbo";
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ChatGPT 3.5! Type 'quit' to exit at any time.");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();
            if (input.equals("quit")) break;

            try {
                String response = getChatResponse(input, model);
                System.out.println("ChatGPT: " + response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    private static String getChatResponse(String input, String model) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"model\": \"" + model +"\", \"prompt\": \"" + input + "\", \"max_tokens\": 7, \"temperature\": 0}", mediaType);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            Gson gson = new Gson();
            System.out.println();
            System.out.println();
            System.out.println();
            TextCompletion textCompletion = gson.fromJson(responseBody, TextCompletion.class);
            for(Choice choice: textCompletion.getChoices()){
                System.out.println(choice.getText());
            }
            System.out.println();
            System.out.println();
            System.out.println();
            return textCompletion.getChoices().get(0).getText();
        } else {
            System.out.println("API request failed: " + response.code() + " " + response.message());
            return null;
        }
    }
}
