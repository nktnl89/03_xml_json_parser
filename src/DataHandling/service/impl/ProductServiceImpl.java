package DataHandling.service.impl;

import DataHandling.model.Category;
import DataHandling.model.Product;
import DataHandling.service.ProductServiceInterface;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProductServiceImpl implements ProductServiceInterface {
    @Override
    public void jaxbObjectToXML(Product product, File file) {
//        try (FileWriter fileWriter = new FileWriter(file)) {
//            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
//            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);
//            xmlStreamWriter.writeStartDocument();
//
//            QName rootQName = new QName("categories");
//            QName categoryQName = new QName("category");
//            JAXBElement<Category> jaxbElementCategory = new JAXBElement<>(categoryQName, Category.class, product.getSubcategory().getCategory());
//            JAXBContext jaxbContextCategory = JAXBContext.newInstance(Category.class);
//            Marshaller marshallerCategory = jaxbContextCategory.createMarshaller();
//
//
////            QName productQName = new QName("product");
////            JAXBElement<Product> jaxbElementProduct = new JAXBElement<>(productQName, Product.class, product);
////            JAXBContext jaxbContextProduct = JAXBContext.newInstance(Product.class);
////            Marshaller marshallerProduct = jaxbContextProduct.createMarshaller();
////            marshallerProduct.setProperty(Marshaller.JAXB_FRAGMENT, true);
////            marshallerProduct.marshal(jaxbElementProduct, fileWriter);
//
//            marshallerCategory.setProperty(Marshaller.JAXB_FRAGMENT, true);
//            marshallerCategory.marshal(jaxbElementCategory, fileWriter);
//
//        } catch (JAXBException | XMLStreamException | IOException e) {
//            e.printStackTrace();
//        }
    }
}