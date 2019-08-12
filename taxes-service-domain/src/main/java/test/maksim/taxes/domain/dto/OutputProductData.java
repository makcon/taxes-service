package test.maksim.taxes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.maksim.taxes.domain.utils.NumberUtils;

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

            itemPrice(NumberUtils.scale(super.itemTaxes + super.productData.getPrice()));
            totalTaxes(NumberUtils.scale(super.itemTaxes * super.productData.getQuantity()));
            totalPrice(NumberUtils.scale(super.itemPrice * super.productData.getQuantity()));

            return super.build();
        }
    }
}
