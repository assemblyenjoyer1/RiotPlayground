package org.example.DTO.test.Ekko;


import com.mashape.unirest.http.HttpMethod;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class LeagueApi {
    private final String authToken;
    private final int authPort;
    private final HttpClient client;
    private final String baseUrl;

    public LeagueApi(String authToken, int authPort){
        this.authToken = authToken;
        this.authPort = authPort;
        this.client = HttpClient.newBuilder()
                .sslContext(createInsecureSslContext())
                .build();
        String encodedAuth = Base64.getEncoder().encodeToString(("riot:" + authToken).getBytes(StandardCharsets.UTF_8));
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .header("Authorization", "Basic " + encodedAuth)
                .header("User-Agent", "LeagueOfLegendsClient")
                .header("Accept", "application/json");
        this.baseUrl = "https://127.0.0.1:" + authPort;
    }

    public CompletableFuture<String> sendAsync(HttpMethod method, String endpoint, String body) {
        HttpRequest.BodyPublisher publisher = (body != null) ? HttpRequest.BodyPublishers.ofString(body) : HttpRequest.BodyPublishers.noBody();
        String encodedAuth = Base64.getEncoder().encodeToString(("riot:" + authToken).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(method.name(), publisher)
                .header("Authorization", "Basic " + encodedAuth)
                .header("User-Agent", "LeagueOfLegendsClient")
                .header("Accept", "application/json")
                .build();
        System.out.println(request);
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    private SSLContext createInsecureSslContext(){
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

            }};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            return sslContext;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
}
