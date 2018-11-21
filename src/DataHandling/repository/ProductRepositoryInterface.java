package DataHandling.repository;


import DataHandling.model.Product;
import DataHandling.model.Subcategory;
import DataHandling.repository.impl.SubcategoryRepository;

import java.util.List;

public interface ProductRepositoryInterface {
    List<Product> getArrayListProduct();

    Product getProductByName(String name);

    void addProduct(String name);

    void addProduct(Product product);

    void addProduct(String name, Subcategory subcategory);

    List<Product> getArrayListProductsFromFile(String fileName, SubcategoryRepository subcategoryRepository);
}
