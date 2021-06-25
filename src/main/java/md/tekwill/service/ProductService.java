package md.tekwill.service;

import md.tekwill.entity.product.FoodCategory;
import md.tekwill.entity.product.Product;
import md.tekwill.exceptions.ProductUpdateUnknownPropertyException;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    void create(String name, double price, LocalDate bestBefore, double volume, long vendorId);

    void create(String name, double price, LocalDate bestBefore, FoodCategory category, long vendorId);

    List<Product> getAll();

    List<Product> getAllNonExpired();

    List<Product> getAllExpired();

    Product getById(int id);

    void update(int id, double volume) throws ProductUpdateUnknownPropertyException;

    void update(int id, FoodCategory category) throws ProductUpdateUnknownPropertyException;

    void delete(int id);

    List<Product> getByName(String name);

    void save(Product product);
}
