package DataHandling.repository.impl;

import DataHandling.model.Product;
import DataHandling.model.Subcategory;
import DataHandling.repository.ProductRepositoryInterface;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProductRepository implements ProductRepositoryInterface {
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getArrayListProduct() {
        return products;
    }

    @Override
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(String name) {
        addProduct(new Product(name));
    }

    @Override
    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        } else {
            System.out.println("Продукт уже есть в списке :" + product.getName());
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void addProduct(String name, Subcategory subcategory) {
        Product tmpProduct = new Product(name);
        tmpProduct.setSubcategory(subcategory);
        addProduct(tmpProduct);
    }

    @Override
    public List<Product> getArrayListProductsFromFile(String fileName, SubcategoryRepository subcategoryRepository) {
        ArrayList<Product> tmpProducts = new ArrayList<>();
        Path path = Paths.get(fileName);
        if (path.toFile().exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                String tmpLine;
                while ((tmpLine = bufferedReader.readLine()) != null) {
                    tmpProducts.add(parseProductsFromString(tmpLine, subcategoryRepository));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return tmpProducts;
    }

    private Product parseProductsFromString(String line, SubcategoryRepository subcategoryRepository) throws Exception {
        Pattern pattern = Pattern.compile("\\t");
        String[] tmpSplitLine = pattern.split(line);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Product tmpProduct = new Product(tmpSplitLine[0],
                subcategoryRepository.getSubcategoryByName(tmpSplitLine[1]),
                tmpSplitLine[2],
                tmpSplitLine[3],
                LocalDate.parse(tmpSplitLine[4], formatter),
                Color.decode(tmpSplitLine[5]),
                Double.valueOf(tmpSplitLine[6]),
                Integer.valueOf(tmpSplitLine[7]));
        subcategoryRepository.getSubcategoryByName(tmpSplitLine[1]).addProduct(tmpProduct);

        return tmpProduct;

    }

}
