package DataHandling.service;

import DataHandling.model.CategoryList;
import DataHandling.repository.impl.CategoryRepository;
import DataHandling.repository.impl.ProductRepository;
import DataHandling.repository.impl.SubcategoryRepository;
import DataHandling.service.impl.CategoryServiceImpl;

import java.io.File;

public class DemoService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    private SubcategoryRepository subcategoryRepository = new SubcategoryRepository();
    private ProductRepository productRepository = new ProductRepository();

    public void startDemo() {
        categoryRepository.setCategories(
                categoryRepository.getArrayListCategoriesFromFile("src/DataHandling/resources/categories.txt"));
        subcategoryRepository.setSubcategories(
                subcategoryRepository.getArrayListSubcategoriesFromFile("src/DataHandling/resources/subcategories.txt"
                        , categoryRepository));
        productRepository.setProducts(
                productRepository.getArrayListProductsFromFile("src/DataHandling/resources/books.txt"
                        , subcategoryRepository));

        CategoryList categoryList = new CategoryList();
        categoryList.setCategories(categoryRepository.getCategories());

        CategoryServiceImpl categoryService = new CategoryServiceImpl();

        //здесь выгружаем в xml
        categoryService.jaxbObjectToXML(categoryList, new File("src/DataHandling/resources/text.xml"));
        //здесь загружаем из xml
        //CategoryList tmp = categoryService.xmlToJaxbObject(new File("src/DataHandling/resources/text.xml"));
        //модель сделать xsd ограничение


        //сделать xsd? сделал генератором, надо дочитать по нему
        //Конвертер json <=> xml с помощью O/X mappers - JAXB and Jackson/GSon (TODO: Date formatter and JsonIgnore) ??????????????
    }

}

