package DataHandling.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subcategory {
    private String name;

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", listProducts=" + listProducts +
                '}';
    }

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private ArrayList<Product> listProducts = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return listProducts;
    }

    public void setProducts(ArrayList<Product> products) {
        this.listProducts = products;
    }

    public Subcategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subcategory() {
    }

    public Subcategory(String s, Category categoryByName) {
    }

    public void addProduct(Product product){
        listProducts.add(product);
    }
}
