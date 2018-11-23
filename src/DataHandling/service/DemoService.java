package DataHandling.service;

import DataHandling.model.Category;
import DataHandling.model.CategoryList;
import DataHandling.repository.impl.CategoryRepository;
import DataHandling.repository.impl.ProductRepository;
import DataHandling.repository.impl.SubcategoryRepository;
import DataHandling.utils.JsonConverter;
import DataHandling.utils.XmlConverter;
import DataHandling.utils.XsdValidator;

import java.io.File;

public class DemoService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    private SubcategoryRepository subcategoryRepository = new SubcategoryRepository();
    private ProductRepository productRepository = new ProductRepository();

    public void startDemo() {

//        //это чтобы не хардкодить, берем данные из текстовых файликов
//        categoryRepository.setCategories(
//                categoryRepository.getArrayListCategoriesFromFile("src/DataHandling/resources/categories.txt"));
//        subcategoryRepository.setSubcategories(
//                subcategoryRepository.getArrayListSubcategoriesFromFile("src/DataHandling/resources/subcategories.txt"
//                        , categoryRepository));
//        productRepository.setProducts(
//                productRepository.getArrayListProductsFromFile("src/DataHandling/resources/books.txt"
//                        , subcategoryRepository));
//
//        CategoryList categoryList = new CategoryList();
//        categoryList.setCategories(categoryRepository.getCategories());

        XmlConverter xmlConverter = new XmlConverter();

        File xmlFileInitial = new File("src/DataHandling/resources/initial.xml");
        File xsdFile = new File("src/DataHandling/resources/categoryList.xsd");
        File xmlFileFromObject = new File("src/DataHandling/resources/fromObject.xml");
        File jsonFileFromObject = new File("src/DataHandling/resources/fromObject.json");
        File xmlFromJson = new File("src/DataHandling/resources/fromJson.xml");

        XsdValidator xsdValidator = new XsdValidator();
        if (xsdValidator.validateXmlByXsd(xmlFileInitial, xsdFile)) {
            //вывод данных из xml
            CategoryList tmpList = xmlConverter.xmlToCategoryList(xmlFileInitial);
            for (Category category : tmpList.getListCategories()) {
                System.out.println(category);
            }
            //обратно складываем лист категорий в xml
            xmlConverter.categoryListToXML(tmpList, xmlFileFromObject);
            xsdValidator.validateXmlByXsd(xmlFileFromObject, xsdFile);
            //складываем полученный список в json
            JsonConverter jsonConverter = new JsonConverter();
            jsonConverter.categoryListToJson(tmpList, jsonFileFromObject);
            //получаем обратно список категорий из только что записанного json и кладем его в xml
            xmlConverter.categoryListToXML(jsonConverter.categoryListFromJson(jsonFileFromObject), xmlFromJson);
            xsdValidator.validateXmlByXsd(xmlFromJson, xsdFile);
        }
    }
}

