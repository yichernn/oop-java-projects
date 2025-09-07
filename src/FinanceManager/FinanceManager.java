package FinanceManager;

import Interface.*;
import User.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class FinanceManager extends User implements ViewPR, ViewPO {
    
    private String userFile = "User.txt";
    private String poFile = "PO.txt";
    private String prFile = "requisitions.txt";


    public FinanceManager(){ }
    
    public FinanceManager(String id, String name) {
        super(id, name);
    }
    
    public String getFMIDByName(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String fmName = parts[1].trim();
                    String role = parts[3].trim();
                    String id = parts[0].trim();

                    if (fmName.equalsIgnoreCase(name.trim())) {
                        return id;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null; 
    }
    
    public void exportInvoice(String paymentID, String orderID, String supplierID, 
                              String fmName, String fmID, String date, 
                              TableModel tableModel, String subtotal) {
        try {
            String filename = "Invoice_" + orderID + ".csv";
            PrintWriter writer = new PrintWriter(filename);

            writer.println("Invoice ID," + paymentID);
            writer.println("Order ID," + orderID);
            writer.println("Supplier ID," + supplierID);
            writer.println("Finance Manager," + fmName + " (" + fmID + ")");
            writer.println("Date," + date);
            writer.println();
            writer.println("Item ID,Quantity,Price/Unit,Total");

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String itemId = tableModel.getValueAt(i, 0).toString();
                String quantity = tableModel.getValueAt(i, 1).toString();
                String price = tableModel.getValueAt(i, 2).toString();
                String total = tableModel.getValueAt(i, 3).toString();
                writer.println(itemId + "," + quantity + "," + price + "," + total);
            }

            writer.println();
            writer.println("Subtotal," + subtotal);
            writer.close();

            System.out.println("Invoice exported to " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void Logout(){
        JOptionPane.showMessageDialog(null,"Finance Manager logout Successful", "",JOptionPane.INFORMATION_MESSAGE);
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
}
