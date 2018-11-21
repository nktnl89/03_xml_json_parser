package DataHandling.model;

import DataHandling.service.ColorAdapter;
import DataHandling.service.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.*;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    private String name;
    private String manufacturer;
    private String model;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate manufactureDate;
    @XmlJavaTypeAdapter(ColorAdapter.class)
    @XmlAttribute
    private Color color;
    private double price;
    private int count;

    public Product() {
    }

    @XmlTransient
    private Subcategory subcategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Subcategory subcategory, String manufacturer, String model, LocalDate manufactureDate, Color color, double price, int count) {
        this.name = name;
        this.subcategory = subcategory;
        this.manufacturer = manufacturer;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.color = color;
        this.price = price;
        this.count = count;
    }

    public Product(String s, Subcategory subcategoryByName) {
    }
}