package test.maksim.taxes.client;

import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.PackType;
import test.maksim.taxes.domain.dto.Product;
import test.maksim.taxes.domain.dto.TaxesCalculationResponse;

import java.util.List;

import static test.maksim.taxes.domain.dto.ProductCategory.FOOD;

public class TaxesServiceClientUsage {

    public static void main(String[] args) {
        TaxesServiceClientFactory factory = new TaxesServiceClientFactory();
        TaxesServiceClient client = factory.defaultClient("http://localhost:8080");

        TaxesCalculationResponse response = client.calculateTaxes(List.of(createProductDataExample())).join();
        System.out.println(response);
    }

    private static InputProductData createProductDataExample() {
        Product product = Product.builder()
                .category(FOOD)
                .imported(true)
                .name("chocolates")
                .build();

        return InputProductData.builder()
                .packType(PackType.BOX)
                .product(product)
                .price(10.0)
                .quantity(2)
                .build();
    }
}