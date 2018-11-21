package DataHandling.repository;

import DataHandling.model.Category;
import DataHandling.model.Subcategory;

import java.util.ArrayList;

public interface CategoryRepositoryInterface {
    ArrayList<Category> getArrayListCategoriesFromFile(String fileName);

    Category getCategoryByName(String name);

    void addCategory(String name);

    void addCategory(Category category);
}
