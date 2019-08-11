package test.maksim.taxes.domain.dto;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TaxesCalculationResponseTest {

    @Test
    public void build_shouldBuildFieldsUsingOutputDataList() {
        var inputProductData1 = InputProductData.builder()
                .price(10)
                .quantity(2)
                .build();
        var outputProductData1 = OutputProductData.builder()
                .productData(inputProductData1)
                .itemTaxes(1.5)
                .build();
        var inputProductData2 = InputProductData.builder()
                .price(5)
                .quantity(1)
                .build();
        var outputProductData2 = OutputProductData.builder()
                .productData(inputProductData2)
                .itemTaxes(1.0)
                .build();

        var response = TaxesCalculationResponse.builder()
                .products(List.of(outputProductData1, outputProductData2))
                .build();

        assertThat(response.getSalesTaxes(), equalTo(4.0));
        assertThat(response.getSalesTotal(), equalTo(29.0));
    }
}