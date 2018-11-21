package DataHandling.repository.impl;

import DataHandling.model.Category;
import DataHandling.model.Subcategory;
import DataHandling.repository.CategoryRepositoryInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CategoryRepository implements CategoryRepositoryInterface {
    private List<Category> categories;

    public ArrayList<Category> getCategories() {
        return (ArrayList<Category>) categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public ArrayList<Category> getArrayListCategoriesFromFile(String fileName) {
        ArrayList<Category> tmpCategories = new ArrayList<>();
        Path path = Paths.get(fileName);
        if (path.toFile().exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                String tmpLine;
                while ((tmpLine = bufferedReader.readLine()) != null) {
                    tmpCategories.add(parseCategoryFromString(tmpLine));
                }
            } catch (IOException e) {
                System.out.println("Ошибка");
                return null;
            }
        }
        return tmpCategories;
    }


    @Override
    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public void addCategory(String name) {
        addCategory(new Category(name));
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    private Category parseCategoryFromString(String line) {
        return new Category(line);
    }
}