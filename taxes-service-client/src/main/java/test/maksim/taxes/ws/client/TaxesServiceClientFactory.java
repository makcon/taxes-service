package test.maksim.taxes.ws.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import test.maksim.taxes.ws.client.impl.TaxesServiceClientImpl;

import java.net.http.HttpClient;

public class TaxesServiceClientFactory {

    public TaxesServiceClient defaultClient(String serviceUrl) {
        return TaxesServiceClientImpl.builder()
                .gson(createGson())
                .httpClient(HttpClient.newHttpClient())
                .serviceUrl(serviceUrl)
                .build();
    }

    private Gson createGson() {
        return new GsonBuilder().create();
    }
}
