package DataHandling.repository;

import DataHandling.model.Category;
import DataHandling.model.Subcategory;
import DataHandling.repository.impl.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public interface SubcategoryRepositoryInterface {
    ArrayList<Subcategory> getArrayListSubcategory();

    Subcategory getSubcategoryByName(String name);

    void addSubcategory(String name);

    void addSubcategory(Subcategory subcategory);

    void addSubcategory(String name, Category category);

    List<Subcategory> getArrayListSubcategoriesFromFile(String fileName, CategoryRepository categoryRepository);
}

