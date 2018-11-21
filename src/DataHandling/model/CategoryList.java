package DataHandling.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class CategoryList {
    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<Category> listCategories = new ArrayList<>();

    public void setCategories(List<Category> categories) {
        this.listCategories = categories;
    }

    public List<Category> getListCategories() {
        return listCategories;
    }
}
