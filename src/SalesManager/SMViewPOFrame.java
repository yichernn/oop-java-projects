package SalesManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class SMViewPOFrame extends JFrame {

    private JTable poTable;
    private DefaultTableModel tableModel;

    public SMViewPOFrame() {
        setTitle("View Purchase Orders");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"PO ID", "Item ID", "Product Name", "Quantity", "Supplier ID", "Supplier Name", "Requisition ID",
                            "Price/each", "Total Price", "Purchase Date", "Status", "PM Name"};
        tableModel = new DefaultTableModel(columns, 0);
        poTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(poTable);
        poTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        poTable.getTableHeader().setReorderingAllowed(false);
        poTable.setDefaultEditor(Object.class,null);

        add(scrollPane, BorderLayout.CENTER);
        loadPurchaseOrders();
        
        JPanel buttonPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton, BorderLayout.WEST);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadPurchaseOrders() {
        File file = new File("PO.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No purchase orders found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading purchase orders: " + e.getMessage());
        }
    }
}

