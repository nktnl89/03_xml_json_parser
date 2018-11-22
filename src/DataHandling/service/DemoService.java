package DataHandling.service;

import DataHandling.model.CategoryList;
import DataHandling.repository.impl.CategoryRepository;
import DataHandling.repository.impl.ProductRepository;
import DataHandling.repository.impl.SubcategoryRepository;
import DataHandling.service.impl.CategoryServiceImpl;
import DataHandling.utils.JsonConverter;
import DataHandling.utils.XmlConverter;
import DataHandling.utils.XsdValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class DemoService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    private SubcategoryRepository subcategoryRepository = new SubcategoryRepository();
    private ProductRepository productRepository = new ProductRepository();

    public void startDemo() {

        //это чтобы не хардкодить, берем данные из текстовых файликов
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

        XmlConverter xmlConverter = new XmlConverter();

        File xmlFile = new File("src/DataHandling/resources/text.xml");
        File xsdFile = new File("src/DataHandling/resources/categoryList.xsd");

        //здесь выгружаем в xml
        //xmlConverter.categoryListToXML(categoryList, xmlFile);
        //здесь загружаем из xml
        //CategoryList tmpList = xmlConverter.xmlToCategoryList(xmlFile);
//        for (Category category : tmpList.getListCategories()) {
//            System.out.println(category);
//        }
        //модель сделать xsd ограничение
//        XsdValidator xsdValidator = new XsdValidator();
//        xsdValidator.validateXmlByXsd(xmlFile, xsdFile);
//        //json
       // JsonConverter jsonConverter = new JsonConverter();
        //jsonConverter.categoryListToJson(categoryList);
//        String tmp = jsonConverter.productToJson(categoryList.getListCategories().get(0).getListSubcategories().get(0).getProducts().get(0));
//        System.out.println(jsonConverter.productFromJson(tmp));
//        String tmp = jsonConverter.subcategoryToJson(categoryList.getListCategories().get(0).getListSubcategories().get(0));
//        System.out.println(jsonConverter.subcategoryFromJson(tmp));

//        String tmp = jsonConverter.categoryToJson(categoryList.getListCategories().get(0));
//        System.out.println(jsonConverter.categoryFromJson(tmp));
//        String tmp = jsonConverter.categoryListToJson(categoryList);
//        System.out.println(jsonConverter.categoryListFromJson(tmp));

        //jsonConverter.categoryToJson(categoryList.getListCategories().get(0));

    }

}

