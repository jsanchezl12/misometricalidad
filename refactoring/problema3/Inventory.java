package refactoring.problema3;

import refactoring.problema3.model.Order;
import refactoring.problema3.model.Product;
import refactoring.problema3.model.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public static void main(String[] args) {
        String csvFileProducts = "./refactoring/problema3/data/products.csv";
        String csvFileSales = "./refactoring/problema3/data/sales.csv";
        String csvFileOrders = "./refactoring/problema3/data/orders.csv";

        String csvSplitBy = ",";

        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        try {
            CSVReader.readCSVFile(csvFileProducts, csvSplitBy, products, "product");
            CSVReader.readCSVFile(csvFileSales, csvSplitBy, sales, "sale");
            CSVReader.readCSVFile(csvFileOrders, csvSplitBy, orders, "order");

            updateProductQuantities(products, sales, orders);
            printProductQuantities(products);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateProductQuantities(
            ArrayList<Product> products,
            ArrayList<Sale> sales,
            ArrayList<Order> orders) {
        for (Order order : orders) {
            Product item = products.get(order.getItemId());
            item.setQuantity(item.getQuantity() + order.getQuantity());
        }

        for (Sale sale : sales) {
            Product item = products.get(sale.getItemId());
            item.setQuantity(item.getQuantity() - sale.getQuantity());
        }
    }

    private static void printProductQuantities(ArrayList<Product> products) {
        for (Product product : products) {
            System.out.println(product.getItem() + " " + product.getQuantity());
        }
    }
}
