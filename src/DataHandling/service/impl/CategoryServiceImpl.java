package DataHandling.service.impl;

import DataHandling.model.Category;
import DataHandling.model.CategoryList;
import DataHandling.service.CategoryServiceInterface;

import javax.xml.bind.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CategoryServiceImpl implements CategoryServiceInterface {
    @Override
    public void jaxbObjectToXML(Category category, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("catalogue");
            xmlStreamWriter.writeStartElement("categories");


            QName root = new QName("category");
            JAXBElement<Category> jaxbElement = new JAXBElement<>(root, Category.class, category);
            JAXBContext jaxbContext = JAXBContext.newInstance(Category.class);


            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(jaxbElement, xmlStreamWriter);

        } catch (JAXBException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jaxbObjectToXML(ArrayList<Category> categories, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("catalogue");
            xmlStreamWriter.writeStartElement("categories");

            for (Category category : categories) {
                QName root = new QName("category");
                JAXBElement<Category> jaxbElement = new JAXBElement<>(root, Category.class, category);
                JAXBContext jaxbContext = JAXBContext.newInstance(Category.class);

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
                marshaller.marshal(jaxbElement, xmlStreamWriter);
            }
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

//            for (Category category : categories) {
//                QName root = new QName("category");
//                JAXBElement<Category> jaxbElement = new JAXBElement<>(root, Category.class, category);
//                JAXBContext jaxbContext = JAXBContext.newInstance(Category.class);
//
//                Marshaller marshaller = jaxbContext.createMarshaller();
//                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
//                marshaller.marshal(jaxbElement, xmlStreamWriter);
//            }
//            xmlStreamWriter.writeEndDocument();
//            xmlStreamWriter.close();

        } catch (JAXBException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jaxbObjectToXML(CategoryList categoryList, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);

            xmlStreamWriter.writeStartDocument();

            JAXBContext jaxbContext = JAXBContext.newInstance(CategoryList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(categoryList, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

        } catch (JAXBException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CategoryList xmlToJaxbObject(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoryList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CategoryList categoryList = (CategoryList) jaxbUnmarshaller.unmarshal(file);

            return categoryList;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
