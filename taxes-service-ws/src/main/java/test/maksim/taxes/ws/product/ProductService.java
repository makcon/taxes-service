package test.maksim.taxes.ws.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.maksim.taxes.domain.dto.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.loadAll();
    }
}
