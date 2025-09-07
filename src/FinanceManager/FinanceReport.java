/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager;

import java.io.*;
import javax.swing.*;
import javax.swing.table.TableModel;

/**
 *
 * @author yichern
 */

public class FinanceReport {
    
    private String paymentFile = "Payment.txt";
    
    public String generateNextPaymentID() {
        String lastID = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(paymentFile))) {
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
                System.out.println("Invalid Payment ID format in file.");
            }
        }
        return String.format("PAY%03d", nextNumber);
    }
            
    public void exportTableToCSV(JTable table, File file) throws Exception {
        TableModel model = table.getModel();
        try (FileWriter fw = new FileWriter(file)) {
            
            for (int i = 0; i < model.getColumnCount(); i++) {
                fw.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) fw.write(",");
            }
            fw.write("\n");
            
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object cellValue = model.getValueAt(i, j);
                    fw.write(cellValue != null ? cellValue.toString() : "");
                    if (j < model.getColumnCount() - 1) fw.write(",");
                }
                fw.write("\n");
            }

            fw.flush();
        }
    }
    
    public void writePaymentRecord(String paymentID, String orderID, String supplierID,
                                   String fmName, String fmID, String date, String subtotal) {
        try (FileWriter writer = new FileWriter(paymentFile, true)) {
            String record = String.join(" | ", paymentID, orderID, supplierID, fmName, fmID, date, subtotal);
            writer.write(record + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}