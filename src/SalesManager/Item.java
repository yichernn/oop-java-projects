package SalesManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Item extends Product{
    private String itemID;
    private String name;
    private int stockLevel;
    private double price;
    private String supplierID;
    private Object tableModel;
    
    public Item(String itemID, String itemName, int stockLevel, double price, String supplierID) {
        super(itemID, itemName);
        this.itemID = itemID;
        this.itemName = itemName;
        this.stockLevel = stockLevel;
        this.price = price;
        this.supplierID = supplierID;
    }


    public void updateStock(int quantity) {
        this.stockLevel += quantity;
    }

    public String getItemID() { return itemID; }
    public String getName() { return name; }
    public int getStockLevel() { return stockLevel; }
    public double getPrice() { return price; }
    public String getSupplierID() { return supplierID; }

    public void setName(String name) { this.name = name; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
    public void setPrice(double price) { this.price = price; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }

    public static Item fromFileString(String line) {
    String[] parts = line.split(",");
        if (parts.length < 5) {
            System.out.println("Invalid line: " + line); 
            return null;
        }
        String itemID = parts[0];
        String name = parts[1];
        int stockLevel = Integer.parseInt(parts[2]);
        double price = Double.parseDouble(parts[3]);
        String supplierID = parts[4];
        return new Item(itemID, name, stockLevel, price, supplierID);
    }
    
    public static List<Item> readItemFile() {
        List<Item> itemList = new ArrayList<>();
        File file = new File("Item.txt");
        if (!file.exists()) return itemList;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String itemID = parts[0];
                    String name = parts[1];
                    int stock = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    String supplierID = parts[4];
                    itemList.add(new Item(itemID, name, stock, price, supplierID));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return itemList;
    }



    public String toFileString() {
        return itemID + "," + name + "," + stockLevel + "," + price + "," + supplierID;
    }
}