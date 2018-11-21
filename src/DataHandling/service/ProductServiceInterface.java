package DataHandling.service;

import DataHandling.model.Product;

import java.io.File;

public interface ProductServiceInterface {
    void jaxbObjectToXML(Product product, File file);
}
