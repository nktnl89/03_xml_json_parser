package DataHandling.utils;

import DataHandling.model.Category;
import DataHandling.model.CategoryList;
import DataHandling.model.Product;
import DataHandling.model.Subcategory;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;

public class JsonConverter {
    public void categoryListToJson(CategoryList categoryList, File file) {
        try (FileWriter fileWriter = new FileWriter(file);) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(CategoryList.class, new CategoryListSerializer())
                    .registerTypeAdapter(Category.class, new CategorySerializer())
                    .registerTypeAdapter(Subcategory.class, new SubcategorySerializer())
                    .registerTypeAdapter(Product.class, new ProductSerializer())
                    .create();
            gson.toJson(categoryList, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String categoryListToJson(CategoryList categoryList) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(CategoryList.class, new CategoryListSerializer())
                .registerTypeAdapter(Category.class, new CategorySerializer())
                .registerTypeAdapter(Subcategory.class, new SubcategorySerializer())
                .registerTypeAdapter(Product.class, new ProductSerializer())
                .create();
        return gson.toJson(categoryList);

    }

    public CategoryList categoryListFromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(CategoryList.class, new CategoryListDeserializer())
                .registerTypeAdapter(Category.class, new CategoryDeserializer())
                .registerTypeAdapter(Subcategory.class, new SubcategoryDeserializer())
                .registerTypeAdapter(Product.class, new ProductDeserializer())
                .create();
        return gson.fromJson(json, CategoryList.class);
    }

    public CategoryList categoryListFromJson(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            JsonReader reader = new JsonReader(fileReader);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(CategoryList.class, new CategoryListDeserializer())
                    .registerTypeAdapter(Category.class, new CategoryDeserializer())
                    .registerTypeAdapter(Subcategory.class, new SubcategoryDeserializer())
                    .registerTypeAdapter(Product.class, new ProductDeserializer())
                    .create();
            return gson.fromJson(reader, CategoryList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String categoryToJson(Category category) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Category.class, new CategorySerializer())
                .registerTypeAdapter(Subcategory.class, new SubcategorySerializer())
                .registerTypeAdapter(Product.class, new ProductSerializer())
                .create();
        return gson.toJson(category);
    }

    public Category categoryFromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Category.class, new CategoryDeserializer())
                .registerTypeAdapter(Subcategory.class, new SubcategorySerializer())
                .registerTypeAdapter(Product.class, new ProductDeserializer())
                .create();
        return gson.fromJson(json, Category.class);
    }

    public Category categoryFromJson(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            JsonReader reader = new JsonReader(fileReader);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Category.class, new CategoryDeserializer())
                    .registerTypeAdapter(Subcategory.class, new SubcategorySerializer())
                    .registerTypeAdapter(Product.class, new ProductDeserializer())
                    .create();
            return gson.fromJson(reader, Category.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String productToJson(Product product) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Product.class, new ProductSerializer())
                .create();
        return gson.toJson(product);
    }

    public String subcategoryToJson(Subcategory subcategory) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Subcategory.class, new SubcategorySerializer())
                .registerTypeAdapter(Product.class, new ProductSerializer())
                .create();
        return gson.toJson(subcategory);
    }

    public Product productFromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Product.class, new ProductDeserializer())
                .create();
        return gson.fromJson(json, Product.class);
    }

    public Product productFromJson(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            JsonReader reader = new JsonReader(fileReader);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Product.class, new ProductDeserializer())
                    .create();
            return gson.fromJson(reader, Product.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Subcategory subcategoryFromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Product.class, new ProductDeserializer())
                .registerTypeAdapter(Subcategory.class, new SubcategoryDeserializer())
                .create();
        return gson.fromJson(json, Subcategory.class);
    }

    public Subcategory subcategoryFromJson(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            JsonReader reader = new JsonReader(fileReader);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Product.class, new ProductDeserializer())
                    .registerTypeAdapter(Subcategory.class, new SubcategoryDeserializer())
                    .create();
            return gson.fromJson(reader, Subcategory.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class CategoryListSerializer implements JsonSerializer<CategoryList> {

        @Override
        public JsonElement serialize(CategoryList categoryList, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject result = new JsonObject();
            JsonArray categories = new JsonArray();
            result.add("categories", categories);
            for (Category category : categoryList.getListCategories()) {
                categories.add(jsonSerializationContext.serialize(category));
            }
            return result;
        }
    }

    private class CategoryListDeserializer implements JsonDeserializer<CategoryList> {

        @Override
        public CategoryList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            CategoryList categoryList = new CategoryList();

            JsonArray categories = jsonObject.getAsJsonArray("categories");
            for (JsonElement category : categories) {
                categoryList.addCategory(jsonDeserializationContext.deserialize(category, Category.class));
            }
            return categoryList;
        }
    }

    private class CategorySerializer implements JsonSerializer<Category> {

        @Override
        public JsonElement serialize(Category category, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject result = new JsonObject();
            result.addProperty("name", category.getName());
            JsonArray subcategories = new JsonArray();
            result.add("subcategories", subcategories);
            for (Subcategory subcategory : category.getListSubcategories()) {
                subcategories.add(jsonSerializationContext.serialize(subcategory));
            }
            return result;
        }
    }

    private class CategoryDeserializer implements JsonDeserializer<Category> {

        @Override
        public Category deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Category category = new Category();
            category.setName(jsonObject.get("name").getAsString());
            JsonArray subcategories = jsonObject.getAsJsonArray("subcategories");
            for (JsonElement subcategory : subcategories) {
                category.addSubcategory(jsonDeserializationContext.deserialize(subcategory, Subcategory.class));
            }
            return category;
        }
    }

    private class SubcategorySerializer implements JsonSerializer<Subcategory> {

        @Override
        public JsonElement serialize(Subcategory subcategory, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject result = new JsonObject();
            result.addProperty("name", subcategory.getName());
            JsonArray products = new JsonArray();
            result.add("products", products);
            for (Product product : subcategory.getProducts()) {
                products.add(jsonSerializationContext.serialize(product));
            }
            return result;
        }
    }

    private class SubcategoryDeserializer implements JsonDeserializer<Subcategory> {

        @Override
        public Subcategory deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Subcategory subcategory = new Subcategory();
            subcategory.setName(jsonObject.get("name").getAsString());
            JsonArray products = jsonObject.getAsJsonArray("products");
            for (JsonElement product : products) {
                subcategory.addProduct(jsonDeserializationContext.deserialize(product, Product.class));
            }
            return subcategory;
        }
    }

    private class ProductSerializer implements JsonSerializer<Product> {

        @Override
        public JsonElement serialize(Product product, Type type, JsonSerializationContext jsonSerializationContext) {
            try {
                JsonObject result = new JsonObject();
                result.addProperty("name", product.getName());
                result.addProperty("manufacturer", product.getManufacturer());
                result.addProperty("model", product.getModel());
                result.addProperty("manufactureDate", new LocalDateAdapter().marshal(product.getManufactureDate()));
                result.addProperty("price", product.getPrice());
                result.addProperty("count", product.getCount());
                result.addProperty("color", new ColorAdapter().marshal(product.getColor()));

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class ProductDeserializer implements JsonDeserializer<Product> {

        @Override
        public Product deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Product product = new Product();
            try {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                product.setColor(new ColorAdapter().unmarshal(jsonObject.get("color").getAsString()));
                product.setCount(jsonObject.get("count").getAsByte());
                product.setManufacturer(jsonObject.get("manufacturer").getAsString());
                product.setModel(jsonObject.get("model").getAsString());
                product.setName(jsonObject.get("name").getAsString());
                product.setPrice(jsonObject.get("price").getAsDouble());
                product.setManufactureDate(new LocalDateAdapter().unmarshal(jsonObject.get("manufactureDate").getAsString()));

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return product;
            }
        }
    }

}
