package md.tekwill.controller;

import md.tekwill.entity.product.Product;
import md.tekwill.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<List<Product>> getAllByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}
