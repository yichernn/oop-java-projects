package SalesManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class SMItemManagementFrame extends JFrame {
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, stockField, priceField, supplierIDField;
    private JButton addButton, editButton, deleteButton, backButton;
   

    public SMItemManagementFrame() {
        setTitle("Item Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        String[] columnNames = {"Item ID", "Name", "Stock", "Price(RM)", "Supplier ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        add(scrollPane, BorderLayout.CENTER);
        itemTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        itemTable.getTableHeader().setReorderingAllowed(false);
        itemTable.setDefaultEditor(Object.class,null);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Item Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        nameField = new JTextField(15);
        stockField = new JTextField(15);
        priceField = new JTextField(15);
        supplierIDField = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 3;
        formPanel.add(stockField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Price(RM):"), gbc);
        gbc.gridx = 1;
        formPanel.add(priceField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Supplier ID:"), gbc);
        gbc.gridx = 3;
        formPanel.add(supplierIDField, gbc);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        backButton = new JButton("Back");
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        leftButtonPanel.add(backButton);
        rightButtonPanel.add(addButton);
        rightButtonPanel.add(editButton);
        rightButtonPanel.add(deleteButton);

        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);
        buttonPanel.add(rightButtonPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        loadItemsData();

        itemTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && itemTable.getSelectedRow() != -1) {
                int row = itemTable.getSelectedRow();
                Object nameObj = tableModel.getValueAt(row, 1);
                Object stockObj = tableModel.getValueAt(row, 2);
                Object priceObj = tableModel.getValueAt(row, 3);
                Object supplierObj = tableModel.getValueAt(row, 4);

                nameField.setText(nameObj != null ? nameObj.toString() : "");
                stockField.setText(stockObj != null ? stockObj.toString() : "");
                priceField.setText(priceObj != null ? priceObj.toString() : "");
                supplierIDField.setText(supplierObj != null ? supplierObj.toString() : "");
            }
        });

        addButton.addActionListener(e -> addItem());
        editButton.addActionListener(e -> editItem());
        deleteButton.addActionListener(e -> deleteItem());
        backButton.addActionListener(e -> goBack());

        setVisible(true);
    }

    private void loadItemsData() {
        List<Item> itemList = Item.readItemFile();
        populateTable(itemTable, itemList);
    }

    private void populateTable(JTable table, List<Item> itemList) {
        tableModel.setRowCount(0);
        for (Item i : itemList) {
            Object[] row = {i.getItemID(), i.getName(), i.getStockLevel(), i.getPrice(), i.getSupplierID()};
            tableModel.addRow(row);
        }
    }

    private void saveItems() {
        try (PrintWriter writer = new PrintWriter("Item.txt")) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String itemID = tableModel.getValueAt(i, 0).toString();
                String name = tableModel.getValueAt(i, 1).toString();
                int stock = Integer.parseInt(tableModel.getValueAt(i, 2).toString());
                double price = Double.parseDouble(tableModel.getValueAt(i, 3).toString());
                String supplierID = tableModel.getValueAt(i, 4).toString();
                writer.println(itemID + "," + name + "," + stock + "," + price + "," + supplierID);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addItem() {
        try {
            String name = nameField.getText().trim();
            String supplierID = supplierIDField.getText().trim();
            int stock = Integer.parseInt(stockField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (name.isEmpty() || supplierID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int itemNumber = tableModel.getRowCount() + 1;
            String itemID = String.format("ITEM%03d", itemNumber);
            Object[] row = {itemID, name, stock, price, supplierID};
            tableModel.addRow(row);
            saveItems();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid stock or price format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editItem() {
        int row = itemTable.getSelectedRow();
        if (row != -1) {
            try {
                String name = nameField.getText().trim();
                String supplierID = supplierIDField.getText().trim();
                int stock = Integer.parseInt(stockField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                if (name.isEmpty() || supplierID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tableModel.setValueAt(name, row, 1);
                tableModel.setValueAt(stock, row, 2);
                tableModel.setValueAt(price, row, 3);
                tableModel.setValueAt(supplierID, row, 4);
                saveItems();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid stock or price format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteItem() {
        int row = itemTable.getSelectedRow();
        if (row != -1) {
            tableModel.removeRow(row);
            saveItems();
            clearFields();
        }
    }

    private void clearFields() {
        nameField.setText("");
        stockField.setText("");
        priceField.setText("");
        supplierIDField.setText("");
    }

    private void goBack() {
        dispose();
    }
}
