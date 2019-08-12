package test.maksim.taxes.ws.product;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.maksim.taxes.domain.constants.Endpoints;
import test.maksim.taxes.domain.dto.Product;

import java.util.List;

@RestController
@RequestMapping(Endpoints.V1_PREFIX)
@RequiredArgsConstructor
@Slf4j
@Api
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
