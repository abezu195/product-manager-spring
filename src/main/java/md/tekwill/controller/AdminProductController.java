package md.tekwill.controller;

import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/food")
    public ResponseEntity<Void> createFood(@RequestBody Food food,
                                           @RequestParam("vendorId") Long vendorId) {
        productService.create(food.getName(), food.getPrice(), food.getBestBefore(), food.getCategory(), vendorId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/admin/drink")
    public ResponseEntity<Void> createDrink(@RequestBody Drink drink,
                                            @RequestParam("vendorId") Long vendorId) {
        productService.create(drink.getName(), drink.getPrice(), drink.getBestBefore(), drink.getVolume(), vendorId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
