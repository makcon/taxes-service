package test.maksim.taxes.ws.calculator;

import org.springframework.stereotype.Component;
import test.maksim.example.domain.dto.OutputProductData;

import java.util.List;

@Component
public class TotalPriceCalculator {

    public double calculateTaxes(List<OutputProductData> productDataList) {
        return productDataList.stream()
                .mapToDouble(this::calculateItemTaxes)
                .sum();
    }

    public double calculatePrice(List<OutputProductData> productDataList) {
        return productDataList.stream()
                .mapToDouble(this::calculateItemPrice)
                .sum();
    }

    private double calculateItemTaxes(OutputProductData data) {
        return data.getItemTaxes() * data.getProductData().getQuantity();
    }

    private double calculateItemPrice(OutputProductData data) {
        return (data.getItemTaxes() + data.getProductData().getPrice()) * data.getProductData().getQuantity();
    }
}
