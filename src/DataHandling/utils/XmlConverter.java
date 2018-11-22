package DataHandling.utils;

import DataHandling.model.Category;
import DataHandling.model.CategoryList;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlConverter {
    public void categoryListToXML(CategoryList categoryList, File file) {
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

    public CategoryList xmlToCategoryList(File file) {
        try {
            //это для чтения целиком всего CategoryList
            //JAXBContext jaxbContext = JAXBContext.newInstance(CategoryList.class);
            //Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            //return (CategoryList) unmarshaller.unmarshal(file);

            //начинаем читать, когда находим категорию, засовываем ее в лист
            List<Category> tmpCategories = new ArrayList<>();
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            StreamSource streamSource = new StreamSource(file);

            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(streamSource);
            Category tmpCategory = null;
            XMLEvent event = null;
            JAXBContext jaxbContext = JAXBContext.newInstance(Category.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CategoryList tmpCategoryList = new CategoryList();
            while ((event = xmlEventReader.peek()) != null) {
                if (event.isStartElement() && ((StartElement) event).getName().getLocalPart().equals("category")) {
                    JAXBElement<Category> jaxbElement = unmarshaller.unmarshal(xmlEventReader, Category.class);
                    tmpCategory = jaxbElement.getValue();
                    tmpCategories.add(tmpCategory);
                    System.out.println(tmpCategory.getName());
                } else {
                    xmlEventReader.next();
                }
            }
            tmpCategoryList.setCategories(tmpCategories);
            xmlEventReader.close();

            return tmpCategoryList;
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
