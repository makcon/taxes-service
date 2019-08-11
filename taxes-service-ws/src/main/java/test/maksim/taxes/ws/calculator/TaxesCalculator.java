package test.maksim.taxes.ws.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.Product;
import test.maksim.taxes.domain.dto.ProductCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Component
public class TaxesCalculator {

    @Value("${exemptions.categories:BOOK,FOOD,MEDICAL}")
    private Set<ProductCategory> exemptionsCategories;
    @Value("${tax.rate:.10}")
    private double taxRate;
    @Value("${tax.rate.imported:.05}")
    private double taxRateImported;

    public double calculate(InputProductData productData) {
        Product product = productData.getProduct();

        double taxes = exemptionsCategories.contains(product.getCategory())
                ? 0.0
                : taxRate * productData.getPrice();

        if (product.isImported()) {
            taxes = productData.getPrice() * taxRateImported + taxes;
        }

        return new BigDecimal(Double.toString(taxes))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
