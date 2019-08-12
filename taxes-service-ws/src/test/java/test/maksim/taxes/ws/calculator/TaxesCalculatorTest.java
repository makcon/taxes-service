package test.maksim.taxes.ws.calculator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.Product;
import test.maksim.taxes.domain.dto.ProductCategory;

import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static test.maksim.taxes.domain.dto.ProductCategory.BOOK;
import static test.maksim.taxes.domain.dto.ProductCategory.MUSIC;

public class TaxesCalculatorTest {

    private static final double PRICE = 14.99;

    private final TaxesCalculator calculator = new TaxesCalculator();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(calculator, "exemptionsCategories", Set.of(BOOK));
        ReflectionTestUtils.setField(calculator, "taxRate", 0.1);
        ReflectionTestUtils.setField(calculator, "taxRateImported", 0.05);
        ReflectionTestUtils.setField(calculator, "taxNearestRound", 0.05);
    }

    @Test
    public void calculate_exemptionsCategoryAndNotImported_shouldReturnZero() {
        double taxes = calculator.calculate(createProductData(BOOK, false));

        assertThat(taxes, equalTo(0.0));
    }

    @Test
    public void calculate_exemptionsCategoryAndNotImported_shouldReturnWithImportedRate() {
        double taxes = calculator.calculate(createProductData(BOOK, true));

        assertThat(taxes, equalTo(0.75));
    }

    @Test
    public void calculate_notExemptionsCategoryAndNotImported_shouldReturnWithRate() {
        double taxes = calculator.calculate(createProductData(MUSIC, false));

        assertThat(taxes, equalTo(1.5));
    }

    @Test
    public void calculate_notExemptionsCategoryAndImported_shouldReturnWithRateAndWithImportedRate() {
        double taxes = calculator.calculate(createProductData(MUSIC, true));

        assertThat(taxes, equalTo(2.25));
    }

    // Util methods

    private InputProductData createProductData(ProductCategory category,
                                               boolean imported) {
        Product product = Product.builder()
                .category(category)
                .imported(imported)
                .build();

        return InputProductData.builder()
                .product(product)
                .price(PRICE)
                .build();
    }
}