package org.example.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LeagueApi {
    private final String authToken;
    private final int authPort;
    private final HttpClient client;
    private final String baseUrl;

    public LeagueApi(String authToken, int authPort) {
        this.authToken = authToken;
        this.authPort = authPort;
        this.client = HttpClient.newBuilder()
                .sslContext(SSLContext.getDefault())
                .build();
        this.baseUrl = "https://127.0.0.1:" + authPort;
    }

    public CompletableFuture<String> sendAsync(HttpMethod method, String endpoint, String body) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("User-Agent", "LeagueOfLegendsClient")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + Base64.getEncoder()
                        .encodeToString(("riot:" + authToken).getBytes(StandardCharsets.UTF_8)));
        if (body != null) {
            requestBuilder.header("Content-Type", "application/json");
            requestBuilder.method(method.toString(), HttpRequest.BodyPublishers.ofString(body));
        } else {
            requestBuilder.method(method.toString(), HttpRequest.BodyPublishers.noBody());
        }
        HttpRequest request = requestBuilder.build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> sendAsync(HttpMethod method, String endpoint) {
        return sendAsync(method, endpoint, null);
    }
}