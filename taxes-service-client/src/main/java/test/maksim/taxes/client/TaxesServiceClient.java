package test.maksim.taxes.client;

import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.TaxesCalculationResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TaxesServiceClient {

    CompletableFuture<TaxesCalculationResponse> calculateTaxes(List<InputProductData> productDataList);
}
