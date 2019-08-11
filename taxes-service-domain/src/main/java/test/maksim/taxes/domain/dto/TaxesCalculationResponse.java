package test.maksim.taxes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxesCalculationResponse {

    private List<OutputProductData> products;
    private double salesTaxes;
    private double salesTotal;

    public static TaxesCalculationResponseBuilder builder() {
        return new CustomBuilder();
    }

    private static class CustomBuilder extends TaxesCalculationResponseBuilder {

        @Override
        public TaxesCalculationResponse build() {
            Objects.requireNonNull(super.products, "ProductData list must be not null");

            salesTaxes(super.products.stream().mapToDouble(OutputProductData::getTotalTaxes).sum());
            salesTotal(super.products.stream().mapToDouble(OutputProductData::getTotalPrice).sum());

            return super.build();
        }
    }
}
