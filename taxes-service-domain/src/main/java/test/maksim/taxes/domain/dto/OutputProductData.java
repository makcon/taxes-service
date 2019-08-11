package test.maksim.taxes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputProductData {

    private InputProductData productData;
    private double itemTaxes;
    private double itemPrice;
    private double totalTaxes;
    private double totalPrice;

    public static OutputProductDataBuilder builder() {
        return new CustomBuilder();
    }

    private static class CustomBuilder extends OutputProductDataBuilder {

        @Override
        public OutputProductData build() {
            Objects.requireNonNull(super.productData, "ProductData must be not null");

            itemPrice(super.itemTaxes + super.productData.getPrice());
            totalTaxes(super.itemTaxes * super.productData.getQuantity());
            totalPrice(super.itemPrice * super.productData.getQuantity());

            return super.build();
        }
    }
}
