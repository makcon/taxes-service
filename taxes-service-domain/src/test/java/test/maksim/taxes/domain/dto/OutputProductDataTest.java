package test.maksim.taxes.domain.dto;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class OutputProductDataTest {

    @Test
    public void build_shouldBuildFieldsUsingItemTaxes() {
        var inputProductData = InputProductData.builder()
                .price(10)
                .quantity(2)
                .build();

        var outputProductData = OutputProductData.builder()
                .productData(inputProductData)
                .itemTaxes(0.5)
                .build();

        assertThat(outputProductData.getItemTaxes(), equalTo(0.5));
        assertThat(outputProductData.getItemPrice(), equalTo(10.5));
        assertThat(outputProductData.getTotalTaxes(), equalTo(1.0));
        assertThat(outputProductData.getTotalPrice(), equalTo(21.0));
    }
}