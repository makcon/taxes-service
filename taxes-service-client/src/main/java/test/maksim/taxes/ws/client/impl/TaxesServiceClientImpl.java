package test.maksim.taxes.ws.client.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import test.maksim.taxes.ws.client.TaxesServiceClient;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscribers;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Builder
public class TaxesServiceClientImpl implements TaxesServiceClient
{

    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";

    private static final Type TYPE_VOID_RESPONSE = new TypeToken<Void>() {}.getType();

    private final String serviceUrl;
    private final HttpClient httpClient;
    private final Gson gson;

    private <T> CompletableFuture<T> executePost(String endpoint,
                                                 String method,
                                                 Type type,
                                                 Object payload) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(serviceUrl + endpoint))
                .header("Content-Type", "application/json")
                .method(method, HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                .build();

        return httpClient.sendAsync(request, getStringBodyHandler())
                .thenApply(response -> gson.fromJson(response.body(), type));
    }

    private <T> CompletableFuture<HttpResponse<T>> executeGet(String endpoint,
                                                               Map<String, String> params,
                                                               HttpResponse.BodyHandler<T> bodyHandler) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(serviceUrl + endpoint + '?' + buildParamsAsString(params)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return httpClient.sendAsync(request, bodyHandler);
    }

    private String buildParamsAsString(Map<String, String> params) {
        return params.entrySet()
                .stream()
                .map(e -> e.getKey() + '=' + URLEncoder.encode(e.getValue(), UTF_8))
                .collect(Collectors.joining("&"));
    }

    private HttpResponse.BodyHandler<String> getStringBodyHandler() {
        return responseInfo -> BodySubscribers.ofString(UTF_8);
    }
}
