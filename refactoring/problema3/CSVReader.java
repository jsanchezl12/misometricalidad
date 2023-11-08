package refactoring.problema3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import refactoring.problema3.model.Order;
import refactoring.problema3.model.Product;
import refactoring.problema3.model.Sale;


public class CSVReader {
    public static <T> void readCSVFile(String csvFile, String csvSplitBy, List<T> list, String type) 
    throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                T item = (T) createItem(data, type);
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object createItem(String[] data, String type) {
        switch (type) {
            case "product":
                return createProduct(data);
            case "sale":
                return createSale(data);
            case "order":
                return createOrder(data);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    private static Product createProduct(String[] data) {
        return new Product(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2])
        );
    }

    private static Sale createSale(String[] data) {
        return new Sale(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2]),
                Integer.parseInt(data[3])
        );
    }

    private static Order createOrder(String[] data) {
        return new Order(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2]),
                Integer.parseInt(data[3])
        );
    }
}
