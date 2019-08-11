package test.maksim.taxes.ws.calculator;

import org.junit.Test;
import test.maksim.example.domain.dto.InputProductData;
import test.maksim.example.domain.dto.OutputProductData;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TotalPriceCalculatorTest {

    private final TotalPriceCalculator calculator = new TotalPriceCalculator();

    @Test
    public void calculateTaxes_shouldCalculateTotalTaxes() {
        var productData1 = createProductData(.5, 2);
        var productData2 = createProductData(7.15, 1);

        double taxes = calculator.calculateTaxes(List.of(productData1, productData2));

        assertThat(taxes, equalTo(8.15));
    }

    @Test
    public void calculatePrice_shouldCalculateTotalPrice() {
        var productData1 = createProductData(.5, 10, 2);
        var productData2 = createProductData(7.15, 47.5, 1);

        double price = calculator.calculatePrice(List.of(productData1, productData2));

        assertThat(price, equalTo(75.65));
    }

    // Util methods

    private OutputProductData createProductData(double taxes,
                                                int quantity) {
        return createProductData(taxes, 0.0, quantity);
    }

    private OutputProductData createProductData(double taxes,
                                                double price,
                                                int quantity) {
        InputProductData inputProductData = InputProductData.builder()
                .price(price)
                .quantity(quantity)
                .build();

        return OutputProductData.builder()
                .productData(inputProductData)
                .itemTaxes(taxes)
                .build();
    }
}