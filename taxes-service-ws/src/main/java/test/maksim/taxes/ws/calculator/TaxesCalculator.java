package test.maksim.taxes.ws.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.Product;
import test.maksim.taxes.domain.dto.ProductCategory;
import test.maksim.taxes.domain.utils.NumberUtils;

import java.util.Set;

@Component
public class TaxesCalculator {

    @Value("${exemptions.categories:BOOK,FOOD,MEDICAL}")
    private Set<ProductCategory> exemptionsCategories;
    @Value("${tax.rate:.10}")
    private double taxRate;
    @Value("${tax.rate.imported:.05}")
    private double taxRateImported;
    @Value("${tax.nearest.round:.05}")
    private double taxNearestRound;

    public double calculate(InputProductData productData) {
        Product product = productData.getProduct();

        double taxes = exemptionsCategories.contains(product.getCategory())
                ? 0
                : taxRate * productData.getPrice();

        if (product.isImported()) {
            taxes = taxRateImported * productData.getPrice() + taxes;
        }

        return NumberUtils.roundUpTo(taxNearestRound, taxes);
    }
}
