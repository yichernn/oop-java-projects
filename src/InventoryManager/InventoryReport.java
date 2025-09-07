package InventoryManager;

import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class InventoryReport {

    private final String[] columnNames = {"Item ID", "Item Name", "Stock", "Price Per Unit", "Threshold", "Stock Level"};
    private final List<Object[]> rowData = new ArrayList<>();

    public InventoryReport(String filePath) {
        loadFromFile(filePath);
    }

    private void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String itemId = parts[0].trim();
                    String itemName = parts[1].trim();
                    int stock = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    int threshold = Integer.parseInt(parts[4].trim());

                    String stockLevel = (stock < threshold) ? "LOW STOCK" : "HIGH STOCK";
                    rowData.add(new Object[]{itemId, itemName, stock, price, threshold, stockLevel});
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getTableModel() {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object[] row : rowData) {
            model.addRow(row);
        }
        return model;
    }
}
