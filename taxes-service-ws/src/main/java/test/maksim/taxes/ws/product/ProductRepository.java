package test.maksim.taxes.ws.product;

import org.springframework.stereotype.Repository;
import test.maksim.taxes.domain.dto.PackType;
import test.maksim.taxes.domain.dto.Product;
import test.maksim.taxes.domain.dto.ProductCategory;

import java.util.List;
import java.util.UUID;

import static test.maksim.taxes.domain.dto.PackType.*;
import static test.maksim.taxes.domain.dto.ProductCategory.*;

@Repository
public class ProductRepository {

    private final List<Product> sampleProducts = List.of(
            createProduct(BOOK, "book", false, null),
            createProduct(MUSIC, "music CD", false, null),
            createProduct(FOOD, "chocolate bar", false, null),
            createProduct(FOOD, "chocolates", false, BOX),
            createProduct(FOOD, "chocolates", true, BOX),
            createProduct(PERFUME, "perfume", false, BOTTLE),
            createProduct(PERFUME, "perfume", true, BOTTLE),
            createProduct(MEDICAL, "headache pills", false, PACKET),
            createProduct(MEDICAL, "headache pills", true, PACKET)
    );

    public List<Product> loadAll() {
        return sampleProducts;
    }

    private Product createProduct(ProductCategory medical,
                                  String name,
                                  boolean imported,
                                  PackType packet) {
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .category(medical)
                .name(name)
                .imported(imported)
                .packType(packet)
                .build();
    }
}
