package DataHandling.repository.impl;

import DataHandling.model.Category;
import DataHandling.model.Subcategory;
import DataHandling.repository.SubcategoryRepositoryInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SubcategoryRepository implements SubcategoryRepositoryInterface {
    private List<Subcategory> subcategories = new ArrayList<>();

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public ArrayList<Subcategory> getArrayListSubcategory() {
        return (ArrayList<Subcategory>) subcategories;
    }

    @Override
    public Subcategory getSubcategoryByName(String name) {
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getName().equals(name)) {
                return subcategory;
            }
        }
        return null;
    }

    @Override
    public void addSubcategory(String name) {
        addSubcategory(new Subcategory(name));
    }

    @Override
    public void addSubcategory(Subcategory subcategory) {
        subcategories.add(subcategory);
    }

    @Override
    public void addSubcategory(String name, Category category) {
        Subcategory tmpSubcategory = new Subcategory(name);
        //tmpSubcategory.setCategory(category);
        category.getListSubcategories().add(tmpSubcategory);
        addSubcategory(tmpSubcategory);
    }

    @Override
    public List<Subcategory> getArrayListSubcategoriesFromFile(String fileName, CategoryRepository categoryRepository) {
        ArrayList<Subcategory> tmpSubcategories = new ArrayList<>();
        Path path = Paths.get(fileName);
        if (path.toFile().exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                String tmpLine;
                while ((tmpLine = bufferedReader.readLine()) != null) {
                    tmpSubcategories.add(parseSubcategoryFromString(tmpLine, categoryRepository));
                }
            } catch (IOException e) {
                System.out.println("Ошибка");
                return null;
            }
        }
        return tmpSubcategories;
    }

    private Subcategory parseSubcategoryFromString(String line, CategoryRepository categoryRepository) {
        Pattern pattern = Pattern.compile("\\t");
        String[] tmpSplitLine = pattern.split(line);
        Subcategory tmpSubcategory = new Subcategory(tmpSplitLine[0]);
        categoryRepository.getCategoryByName(tmpSplitLine[1]).addSubcategory(tmpSubcategory);
        return tmpSubcategory;
    }
}
