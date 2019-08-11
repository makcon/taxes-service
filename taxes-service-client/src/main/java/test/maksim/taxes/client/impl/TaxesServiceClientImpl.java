package test.maksim.taxes.client.impl;

import com.google.gson.Gson;
import lombok.Builder;
import test.maksim.taxes.client.TaxesServiceClient;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.TaxesCalculationResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodySubscribers;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.nio.charset.StandardCharsets.UTF_8;
import static test.maksim.taxes.domain.constants.Endpoints.CALCULATE;
import static test.maksim.taxes.domain.constants.Endpoints.V1_PREFIX;

@Builder
public class TaxesServiceClientImpl implements TaxesServiceClient {

    private final String serviceUrl;
    private final HttpClient httpClient;
    private final Gson gson;

    @Override
    public CompletableFuture<TaxesCalculationResponse> calculateTaxes(List<InputProductData> productDataList) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(serviceUrl + V1_PREFIX + CALCULATE))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(productDataList)))
                .build();

        return httpClient.sendAsync(request, responseInfo -> BodySubscribers.ofString(UTF_8))
                .thenApply(response -> gson.fromJson(response.body(), TaxesCalculationResponse.class));
    }
}
