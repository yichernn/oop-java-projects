/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PurchaseManager;


import FinanceManager.PurchaseOrder;
import Interface.*;
import User.User;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;





public class PurchaseManager extends User implements ViewItem, ViewPO, ViewPR, ViewSupplier {

    private String poFile = "PO.txt";
    private String prFile = "Requisitions.txt";
    private String itemFile = "Item.txt";
    private String supplierFile = "Suppliers.txt";
    
    public PurchaseManager() {}
    
    @Override
    public List<String[]> viewItems() {
            List<String[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(itemFile))) {
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
    
    public static void writeFile(String filename, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
    
    public static void appendToFile(String filename, String[] row) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(String.join(",", row));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }
    
    public static String generateNextPOID() {
        PurchaseManager pm = new PurchaseManager();
        List<PurchaseOrder> records = pm.viewPO();

        int max = 0;
        for (PurchaseOrder po : records) {
            String poID = po.getPoID();
            if (poID != null && poID.startsWith("PO")) {
                try {
                    int num = Integer.parseInt(poID.substring(2));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        return String.format("PO%03d", max + 1);
    }
    
    public static boolean isPositiveInteger(String input, String fieldName) {
        try {
            int value = Integer.parseInt(input);
            if (value > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    fieldName + " must be a positive number.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                fieldName + " must be a valid integer.",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public static boolean isPositiveDouble(String value) {
        try {
            return Double.parseDouble(value) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //check user input format function
    public static boolean isValidID(String id, String prefix, int digitCount, String fieldLabel, Component parentComponent) {
        String pattern = "^" + prefix + "\\d{" + digitCount + "}$";
        if (!id.matches(pattern)) {
            JOptionPane.showMessageDialog(parentComponent,
                "Invalid " + fieldLabel + " format. It should be like " + prefix + String.format("%0" + digitCount + "d", 1) + ".",
                "Invalid Format",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void Logout() {
        JOptionPane.showMessageDialog(null,"Purchase Manager logout Successful", "",JOptionPane.INFORMATION_MESSAGE);
    }
}

