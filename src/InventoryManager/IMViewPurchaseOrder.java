package InventoryManager;

import FinanceManager.PurchaseOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import java.util.List;
//import java.util.ArrayList;
//
//
public class IMViewPurchaseOrder extends javax.swing.JFrame {
    private DefaultTableModel model;
    private InventoryManager im = new InventoryManager();
//
//    /**
//     * Creates new form ViewPurchasesOrders
//     */
//    public IMViewPurchaseOrder() {
//        initComponents();
//        String[] columnNames = {
//            "PO ID", "Item ID", "Item Name", "Quantity",
//            "Supplier ID", "Supplier Name", "Unit Price",
//            "Total Price", "Date", "Status"
//        };
//
//    // Create non-editable table model
//    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return false;
//        }
//    };
//    purchaseTable.setModel(model);
//
//    // Load data from PO.txt
//    try (BufferedReader br = new BufferedReader(new FileReader("data/PO.txt"))) {
//        String line;
//        while ((line = br.readLine()) != null) {
//            String[] data = line.split(",");
//            if (data.length == 10) {
//                model.addRow(data);
//                // Add PO ID and Item ID to ComboBox
//                CmbOrder.addItem(data[0].trim() + " / " + data[1].trim()); // PO ID / Item ID
//            } else {
//                System.out.println("Invalid line: " + line);
//            }
//        }
//    } catch (IOException e) {
//        JOptionPane.showMessageDialog(this, "Error reading PO.txt: " + e.getMessage());
//    }
//
//        // ComboBox listener to highlight matching row
//        CmbOrder.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String selected = (String) CmbOrder.getSelectedItem();
//                if (selected != null && selected.contains("/")) {
//                    String selectedPOID = selected.split("/")[0].trim(); // PO ID
//                    for (int i = 0; i < purchaseTable.getRowCount(); i++) {
//                        String rowPOID = purchaseTable.getValueAt(i, 0).toString().trim(); // PO ID column
//                        if (rowPOID.equals(selectedPOID)) {
//                            purchaseTable.setRowSelectionInterval(i, i);
//                            purchaseTable.scrollRectToVisible(purchaseTable.getCellRect(i, 0, true));
//                            break;
//                        }
//                    }
//                }
//            }
//        });
//    }

public IMViewPurchaseOrder() {
    initComponents();

    String[] columnNames = {
        "PO ID", "Item ID", "Item Name", "Quantity",
        "Supplier ID", "Supplier Name", "Unit Price",
        "Total Price", "Date", "Status"
    };

    model = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    purchaseTable.setModel(model);

    // Call the OO method to load POs
    List<PurchaseOrder> purchaseOrders = im.viewPO();
    for (PurchaseOrder po : purchaseOrders) {
        model.addRow(new Object[] {
            po.getPoID(),
            po.getItemID(),
            po.getItemName(),
            po.getQuantity(),
            po.getSupplierID(),
            po.getSupplierName(),
            po.getPricePerUnit(),
            po.getTotal(),
            po.getDate(),
            po.getStatus()
        });

        CmbOrder.addItem(po.getPoID() + " / " + po.getItemID());
    }

    // ComboBox listener
    CmbOrder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String selected = (String) CmbOrder.getSelectedItem();
            if (selected != null && selected.contains("/")) {
                String selectedPOID = selected.split("/")[0].trim();
                for (int i = 0; i < purchaseTable.getRowCount(); i++) {
                    String rowPOID = purchaseTable.getValueAt(i, 0).toString().trim();
                    if (rowPOID.equals(selectedPOID)) {
                        purchaseTable.setRowSelectionInterval(i, i);
                        purchaseTable.scrollRectToVisible(purchaseTable.getCellRect(i, 0, true));
                        break;
                    }
                }
            }
        }
    });
}

    
    
   
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchaseTable = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CmbOrder = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IMViewPurchasesOrders");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 15)); // NOI18N
        jLabel1.setText("View Purchase Order");

        purchaseTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "Item ID", "Type ", "Quantity", "Supplier ID", "Supplier Name", "Unit Price", "Total Price", "Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(purchaseTable);
        if (purchaseTable.getColumnModel().getColumnCount() > 0) {
            purchaseTable.getColumnModel().getColumn(0).setResizable(false);
        }

        btnBack.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setText("Search for PO ID/Item ID:");

        CmbOrder.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CmbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jLabel1)))
                .addContainerGap(722, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CmbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnBack)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new IMMainPage().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbOrder;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable purchaseTable;
    // End of variables declaration//GEN-END:variables
}
