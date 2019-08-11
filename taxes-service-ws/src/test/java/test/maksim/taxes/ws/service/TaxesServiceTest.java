package test.maksim.taxes.ws.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import test.maksim.example.domain.dto.InputProductData;
import test.maksim.example.domain.dto.OutputProductData;
import test.maksim.example.domain.dto.Product;
import test.maksim.example.domain.dto.TaxesCalculationResponse;
import test.maksim.taxes.ws.calculator.TaxesCalculator;
import test.maksim.taxes.ws.calculator.TotalPriceCalculator;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxesServiceTest {

    @InjectMocks
    private TaxesService service;

    @Mock
    private TaxesCalculator taxesCalculator;
    @Mock
    private TotalPriceCalculator totalPriceCalculator;

    @Test
    public void calculateTaxes() {
        var quantity = 2;
        var inputProductData = InputProductData.builder()
                .product(mock(Product.class))
                .quantity(quantity)
                .build();
        var taxes = 1.0;
        var totalTaxes = 2.1;
        var totalPrice = 3.5;
        when(taxesCalculator.calculate(any())).thenReturn(taxes);
        when(totalPriceCalculator.calculateTaxes(any())).thenReturn(totalTaxes);
        when(totalPriceCalculator.calculatePrice(any())).thenReturn(totalPrice);
        OutputProductData expectedOutputData = OutputProductData.builder()
                .productData(inputProductData)
                .itemTaxes(taxes)
                .build();

        TaxesCalculationResponse response = service.calculateTaxes(List.of(inputProductData));

        assertThat(response.getProducts(), equalTo(List.of(expectedOutputData)));
        assertThat(response.getTotalTaxes(), equalTo(totalTaxes));
        assertThat(response.getTotalPrice(), equalTo(totalPrice));
    }
}