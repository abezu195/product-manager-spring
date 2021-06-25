package md.tekwill.service;

import md.tekwill.dao.ProductRepository;
import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.entity.product.FoodCategory;
import md.tekwill.entity.product.Product;
import md.tekwill.entity.product.Vendor;
import md.tekwill.exceptions.ProductExistsException;
import md.tekwill.exceptions.ProductNotFoundException;
import md.tekwill.exceptions.ProductUpdateUnknownPropertyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final VendorService vendorService;

    public ProductServiceImpl(ProductRepository productRepository, VendorService vendorService) {
        this.productRepository = productRepository;
        this.vendorService = vendorService;
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, double volume, long vendorId) {
        Vendor vendor = vendorService.getById(vendorId);
        productRepository.save(new Drink(name, price, bestBefore, volume, vendor));
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, FoodCategory category, long vendorId) {
        if (productRepository.findByName(name) != null) {
            throw new ProductExistsException("Product with name " + name + " already exists!");
        }
        Vendor vendor = vendorService.getById(vendorId);
        productRepository.save(new Food(name, price, bestBefore, category, vendor));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllNonExpired() {
        List<Product> products = productRepository.findAll();
        Predicate<Product> nonExpiredPredicate = p -> LocalDate.now().isBefore(p.getBestBefore()) || LocalDate.now().isEqual(p.getBestBefore());
        return filter(products, nonExpiredPredicate);
    }

    @Override
    public List<Product> getAllExpired() {
        List<Product> products = productRepository.findAll();
        Predicate<Product> expiredPredicate = p -> LocalDate.now().isAfter(p.getBestBefore());
        return filter(products, expiredPredicate);
    }

    private List<Product> filter(List<Product> products, Predicate<Product> predicate) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (predicate.test(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @Override
    public Product getById(int id) {
//        Product product = productRepositoryJpa.findById(id)
//               .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!"));
        // same as above
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }
    }

    @Override
    public void update(int id, double volume) throws ProductUpdateUnknownPropertyException {
        Product product = getById(id);
        if (product instanceof Drink) {
            ((Drink) product).setVolume(volume);
            productRepository.save(product);
            return;
        }
        throw new ProductUpdateUnknownPropertyException("Product with id " + id + " is not drink!");
    }

    @Override
    public void update(int id, FoodCategory category) throws ProductUpdateUnknownPropertyException {
        Product product = getById(id);
        if (product instanceof Food) {
            ((Food) product).setCategory(category);
            productRepository.save(product);
            return;
        }
        throw new ProductUpdateUnknownPropertyException("Product with id " + id + " is not food!");
    }

    @Override
    public void delete(int id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
