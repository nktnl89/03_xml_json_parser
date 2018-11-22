package DataHandling.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", listSubcategories=" + listSubcategories +
                '}';
    }

    @XmlElementWrapper(name = "subcategories")
    @XmlElement(name = "subcategory")
    private List<Subcategory> listSubcategories = new ArrayList<>();

    public List<Subcategory> getListSubcategories() {
        return listSubcategories;
    }

    public void setSubcategories(ArrayList<Subcategory> subcategories) {
        this.listSubcategories = subcategories;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubcategory(Subcategory subcategory){
        listSubcategories.add(subcategory);
    }

    public Category() {
    }
}
