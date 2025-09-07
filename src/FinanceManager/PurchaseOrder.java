
package FinanceManager;

import java.io.*;
import java.util.*;

public class PurchaseOrder {
    private String poID;
    private String itemID;
    private String itemName;
    private int quantity;
    private String supplierID;
    private String supplierName;
    private String prID;
    private double pricePerUnit;
    private double total;
    private String date;
    private String status;
    private String pmName;
    public String orderFile = "Order.txt" ;
    public String poFile = "PO.txt" ;
    private FinanceManager fm = new FinanceManager();
    
    
    public PurchaseOrder(){
    }

    public PurchaseOrder(String poID, String itemID, String itemName, int quantity, String supplierID,
                         String supplierName, String prID, double pricePerUnit, double total, String date, String status, String pmName) {
        this.poID = poID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.prID = prID;
        this.pricePerUnit = pricePerUnit;
        this.total = total;
        this.date = date;
        this.status = status;
        this.pmName = pmName;
    }

    public String getPoID() { return poID; }
    public String getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public String getSupplierID() { return supplierID; }
    public String getprID() { return prID; }
    public String getSupplierName() { return supplierName; }
    public double getPricePerUnit() { return pricePerUnit; }
    public double getTotal() { return total; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getpmName() { return pmName; }
    public void setStatus(String status) { this.status = status; }
    
    public String generateNextOrderID() {
        String lastID = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    lastID = parts[0].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading order file: " + e.getMessage());
        }

        int nextNumber = 1;
        if (lastID != null && lastID.startsWith("ORD")) {
            try {
                nextNumber = Integer.parseInt(lastID.substring(3)) + 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Order ID format in file.");
            }
        }
        return String.format("ORD%03d", nextNumber);
    }
   
    public List<PurchaseOrder> filterPOByStatus(List<PurchaseOrder> poList, String status) {
        List<PurchaseOrder> filteredList = new ArrayList<>();
        for (PurchaseOrder po : poList) {
            if (po.getStatus().equalsIgnoreCase(status)) {
                filteredList.add(po);
            }
        }
        return filteredList;
    }

    public boolean updatePOStatus(String poID, String newStatus) {
        List<PurchaseOrder> poList = fm.viewPO(); 
        boolean statusUpdated = false;
        
        for (PurchaseOrder po : poList) {
            if (po.getPoID().equalsIgnoreCase(poID)) {
                po.setStatus(newStatus); 
                statusUpdated = true;
                break; 
            }
        }
        if (statusUpdated) {
            return savePOFile(poList);
        } else {
            return false; 
        }
    }

    public boolean savePOFile(List<PurchaseOrder> poList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(poFile))) {
            for (PurchaseOrder po : poList) {
                writer.write(po.getPoID() + "," + po.getItemID() + "," + po.getItemName() + ","
                        + po.getQuantity() + "," + po.getSupplierID() + "," + po.getSupplierName() + ","
                        + po.getprID() + "," + po.getPricePerUnit() + "," + po.getTotal() + "," + po.getDate() + ","
                        + po.getStatus() + "," + po.getpmName());
                writer.newLine(); 
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error while saving to the file: " + e.getMessage());
            return false;
        }
    }
    
    public boolean writeOrderToFile(String orderID, String supplierID, String fmID, List<PurchaseOrder> selectedPOs, String date) {
    
        List<String[]> orderLines = new ArrayList<>();
        double subtotal = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                orderLines.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Could not read order file: " + e.getMessage());
        }

        for (PurchaseOrder po : selectedPOs) {
            double total = po.getPricePerUnit() * po.getQuantity();
            subtotal += total;
            poID = po.getPoID();
            po.updatePOStatus(poID, "Ordered");
            String[] newLine = {
                orderID,
                poID,
                supplierID,
                fmID,
                po.getItemID(),
                String.valueOf(po.getQuantity()),
                String.format("%.2f", po.getPricePerUnit()),
                String.format("%.2f", total),
                String.format("%.2f", subtotal), 
                date,
                "-",
                "-",
                "-",
                "-",
                "-"
            };
            orderLines.add(newLine);
        }
        orderLines.sort(Comparator.comparing(o -> o[0]));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFile))) {
            for (String[] lineParts : orderLines) {
                writer.write(String.join(",", lineParts));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Could not write to order file: " + e.getMessage());
            return false;
        }
    }
}
