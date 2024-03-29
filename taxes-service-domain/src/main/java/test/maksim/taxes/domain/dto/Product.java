package test.maksim.taxes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String id;
    private String name;
    private ProductCategory category;
    private boolean imported;
    private PackType packType;
}
