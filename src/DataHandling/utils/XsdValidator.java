package DataHandling.utils;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XsdValidator {
    public boolean validateXmlByXsd(File xmlFile, File xsdFile) {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        boolean result = false;
        try {
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFile);
            validator.validate(source);
            System.out.println(xmlFile.getName() + " is valid.");
            result = true;
        } catch (SAXException ex) {
            System.out.println(xmlFile.getName() + " is not valid because ");
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
