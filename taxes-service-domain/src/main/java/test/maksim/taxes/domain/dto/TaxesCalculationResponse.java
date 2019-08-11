package test.maksim.taxes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxesCalculationResponse {

    private List<OutputProductData> products;
    private double totalTaxes;
    private double totalPrice;
}
