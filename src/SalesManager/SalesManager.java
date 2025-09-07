package SalesManager;

import FinanceManager.PurchaseOrder;
import Interface.*;
import Interface.ViewPR;
import User.User;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class SalesManager extends User implements Serializable, ViewItem, ViewSupplier, ViewPR, ViewPO {
    private String itemID;
    private int quantitySold;
    private String date;
    private String poFile = "PO.txt";
    private String supplierFile = "Suppliers.txt";
    private String prFile = "Requisitions.txt";

    private static final String FILE_PATH = "Sales.txt";

    public SalesManager(){}
    
    public SalesManager(String itemID, int quantitySold, String date) {
        this.itemID = itemID;
        this.quantitySold = quantitySold;
        this.date = date;
    }

    public String getItemID() { return itemID; }
    public int getQuantitySold() { return quantitySold; }
    public String getDate() { return date; }

    public void setItemID(String itemID) { this.itemID = itemID; }
    public void setQuantitySold(int quantitySold) { this.quantitySold = quantitySold; }
    public void setDate(String date) { this.date = date; }

    public static List<SalesManager> loadSalesFromFile() {
        List<SalesManager> salesList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return salesList;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                salesList.add(fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    public static void saveSalesToFile(List<SalesManager> salesList) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (SalesManager sale : salesList) {
                pw.println(sale.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // === Format for File ===
    @Override
    public String toString() {
        return itemID + "," + quantitySold + "," + date;
    }

    public static SalesManager fromString(String line) {
        String[] parts = line.split(",");
        return new SalesManager(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }

  @Override
    public List<String[]> viewItems() {
        List<String[]> itemList = new ArrayList<>();
        File file = new File("Item.txt");

        if (!file.exists()) return itemList;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    itemList.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    @Override
    public List<String[]> viewSupplier() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(supplierFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                data.add(parts);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    @Override
    public List<String[]> viewPR() {
        List<String[]> requisitions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(prFile))) {
            String line;
            String requisitionID = "", salesManagerID = "", date = "";
            List<String[]> tempItems = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.startsWith("RequisitionID:")) {
                    requisitionID = line.split(":")[1].trim();
                } else if (line.startsWith("SalesManagerID:")) {
                    salesManagerID = line.split(":")[1].trim();
                } else if (line.startsWith("Date:")) {
                    date = line.split(":")[1].trim();
                } else if (line.startsWith("Item:")) {
                    String[] itemData = line.split(":")[1].trim().split(",");
                    String itemCode = itemData[0].trim();
                    String quantity = itemData[1].trim();
                    tempItems.add(new String[] {
                        requisitionID, itemCode, quantity, salesManagerID, date
                    });
                } else if (line.equals("---")) {
                    requisitions.addAll(tempItems);
                    tempItems.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requisitions;
    }


    @Override
    public List<PurchaseOrder> viewPO() {
        List<PurchaseOrder> poList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(poFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 12) {
                    String poID = parts[0].trim();
                    String itemID = parts[1].trim();
                    String itemName = parts[2].trim();
                    int quantity = Integer.parseInt(parts[3].trim());
                    String supplierID = parts[4].trim();
                    String supplierName = parts[5].trim();
                    String prID = parts[6].trim();
                    double pricePerUnit = Double.parseDouble(parts[7].trim());
                    double subtotal = Double.parseDouble(parts[8].trim());
                    String date = parts[9].trim();
                    String status = parts[10].trim();
                    String pmName = parts[11].trim();

                    PurchaseOrder po = new PurchaseOrder(poID, itemID, itemName, quantity, supplierID, supplierName,prID, pricePerUnit, subtotal, date, status, pmName);
                    poList.add(po);
                }
            }

        } catch (IOException | NumberFormatException e) {
            
        }

        return poList;    
    }

    @Override
    public void Logout() {
        JOptionPane.showMessageDialog(null,"Sales Manager logout Successful", "",JOptionPane.INFORMATION_MESSAGE);
    }
}
