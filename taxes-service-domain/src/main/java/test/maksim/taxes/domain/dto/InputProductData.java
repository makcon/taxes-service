package test.maksim.taxes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputProductData {

    private Product product;
    private PackType packType;
    private double price;
    private int quantity;
}
