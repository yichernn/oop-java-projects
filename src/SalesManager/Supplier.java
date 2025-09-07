package SalesManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private String supplierID;
    private String name;
    private String contactDetails;
    private String itemID;

    private static final String FILE_PATH = "Suppliers.txt";

    public Supplier(String supplierID, String name, String contactDetails, String itemID) {
        this.supplierID = supplierID;
        this.name = name;
        this.contactDetails = contactDetails;
        this.itemID = itemID;
    }

    public Supplier() {
    }

    public String getSupplierID() { return supplierID; }
    public String getName() { return name; }
    public String getContactDetails() { return contactDetails; }
    public String getItemID() { return itemID; }

    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }
    public void setName(String name) { this.name = name; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
    public void setItemID(String itemID) { this.itemID = itemID; }

    public String toFileString() {
        return supplierID + "," + name + "," + contactDetails + "," + itemID;
    }

    @Override
    public String toString() {
        return supplierID + " | " + name + " | " + contactDetails + " | " + itemID;
    }

    public static List<Supplier> loadSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return suppliers;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    suppliers.add(new Supplier(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public static void saveSuppliers(List<Supplier> suppliers) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Supplier s : suppliers) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
