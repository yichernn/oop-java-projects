/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManager;

import FinanceManager.PurchaseOrder;
import Interface.*;
import User.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author yichern
 */
public class InventoryManager extends User implements ViewItem, ViewPO{

    private String itemFile = "Item.txt";
    private String poFile = "PO.txt";

    
    @Override
    public void Logout() {
        JOptionPane.showMessageDialog(null,"Inventory Manager logout Successful", "",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public List<String[]> viewItems() {
        List<String[]> itemList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(itemFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 5) {
                    String[] item = {data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),data[4].trim()};
                    itemList.add(item);
                } else {
                    System.out.println("Invalid line (found " + data.length + " fields): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading item file: " + e.getMessage());
        }
        return itemList;
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
