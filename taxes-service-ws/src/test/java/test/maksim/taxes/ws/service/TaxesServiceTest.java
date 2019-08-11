package test.maksim.taxes.ws.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.OutputProductData;
import test.maksim.taxes.domain.dto.Product;
import test.maksim.taxes.domain.dto.TaxesCalculationResponse;
import test.maksim.taxes.ws.calculator.TaxesCalculator;

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

    @Test
    public void calculateTaxes() {
        var quantity = 2;
        var inputProductData = InputProductData.builder()
                .product(mock(Product.class))
                .quantity(quantity)
                .build();
        var taxes = 1.0;
        when(taxesCalculator.calculate(any())).thenReturn(taxes);
        var expectedOutputData = OutputProductData.builder()
                .productData(inputProductData)
                .itemTaxes(taxes)
                .build();

        TaxesCalculationResponse response = service.calculateTaxes(List.of(inputProductData));

        assertThat(response.getProducts(), equalTo(List.of(expectedOutputData)));
    }
}