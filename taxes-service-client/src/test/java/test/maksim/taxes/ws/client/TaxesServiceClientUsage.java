package test.maksim.taxes.ws.client;

public class TaxesServiceClientUsage {

    public static void main(String[] args) {
        TaxesServiceClientFactory factory = new TaxesServiceClientFactory();
        TaxesServiceClient client = factory.defaultClient("http://localhost:8080");

    }
}