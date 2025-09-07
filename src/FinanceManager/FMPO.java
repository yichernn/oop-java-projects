/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FinanceManager;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yichern
 */
public class FMPO extends javax.swing.JFrame {

    private String uname;
    private PurchaseOrder po = new PurchaseOrder();
    private FinanceManager fm = new FinanceManager();

    public FMPO(String uname) {
        initComponents();
        setTitle("OWSB | Finance Manager | Purchase Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.uname = uname;
        loadPOData();
    }
    
    public void loadPOData() {
        List<PurchaseOrder> poList = fm.viewPO();

        List<PurchaseOrder> pendingPOs = po.filterPOByStatus(poList, "Pending");
        List<PurchaseOrder> approvedPOs = po.filterPOByStatus(poList, "Approved");
        List<PurchaseOrder> orderedPOs = po.filterPOByStatus(poList, "Ordered");

        populateTable(tblPending, pendingPOs);
        populateTable(tblApproved, approvedPOs);
        populateTable(tblOrdered, orderedPOs);
    }
    
    private void populateTable(JTable table, List<PurchaseOrder> poList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (PurchaseOrder po : poList) {
            Object[] row = new Object[12];
            row[0] = po.getPoID();
            row[1] = po.getItemID();
            row[2] = po.getItemName();
            row[3] = po.getQuantity();
            row[4] = po.getSupplierID();
            row[5] = po.getSupplierName();
            row[6] = po.getprID();
            row[7] = po.getPricePerUnit();
            row[8] = po.getTotal();
            row[9] = po.getDate();
            row[10] = po.getStatus();
            row[11] = po.getpmName();

            model.addRow(row);
        }
    }
    
    private void searchPO(JTable table, List<PurchaseOrder> allPOs, String poid) {
        if (poid == null || poid.trim().isEmpty()) {
            populateTable(table, allPOs);
            return;
        }

        List<PurchaseOrder> filtered = new ArrayList<>();
        for (PurchaseOrder po : allPOs) {
            if (po.getPoID().equalsIgnoreCase(poid.trim())) {
                filtered.add(po);
            }
        }
        populateTable(table, filtered);
    }

    
    private void sortByDate(JTable table, List<PurchaseOrder> poList, boolean ascending) {
        poList.sort((po1, po2) -> {
            String d1 = po1.getDate();
            String d2 = po2.getDate();
            return ascending ? d1.compareTo(d2) : d2.compareTo(d1);
            });
        populateTable(table, poList);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel1 = new java.awt.Panel();
        txtPendingSearch = new javax.swing.JTextField();
        cmbPendingSort = new javax.swing.JComboBox<>();
        btnPendingSearch = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPending = new javax.swing.JTable();
        panel2 = new java.awt.Panel();
        txtApprovedSearch = new javax.swing.JTextField();
        btnApprovedSearch = new javax.swing.JButton();
        cmbApprovedSort = new javax.swing.JComboBox<>();
        btnDisaprove = new javax.swing.JButton();
        btnPlaceOrder = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblApproved = new javax.swing.JTable();
        panel3 = new java.awt.Panel();
        txtOrderedSearch = new javax.swing.JTextField();
        btnOrderedSearch = new javax.swing.JButton();
        cmbOrderedSort = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOrdered = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Order");
        jLabel1.setToolTipText("");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbPendingSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date (Ascending)", "Date (Descending)" }));
        cmbPendingSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPendingSortActionPerformed(evt);
            }
        });

        btnPendingSearch.setText("Search");
        btnPendingSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendingSearchActionPerformed(evt);
            }
        });

        btnApprove.setText("Approve");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        btnReject.setText("Reject");
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        tblPending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "Item ID", "Item Name", "Quantity", "Supplier ID", "Supplier", "PR ID", "Price/unit", "Subtotal", "Date", "Status", "PM Name"
            }
        ));
        jScrollPane5.setViewportView(tblPending);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(txtPendingSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPendingSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                        .addComponent(cmbPendingSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApprove)))
                .addContainerGap())
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPendingSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPendingSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPendingSearch)))
                .addGap(382, 382, 382)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApprove)
                    .addComponent(btnReject))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Pending", panel1);

        btnApprovedSearch.setText("Search");
        btnApprovedSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovedSearchActionPerformed(evt);
            }
        });

        cmbApprovedSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date (Ascending)", "Date (Descending)" }));
        cmbApprovedSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbApprovedSortActionPerformed(evt);
            }
        });

        btnDisaprove.setText("Disapprove");

        btnPlaceOrder.setText("Place Order");
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });

        tblApproved.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "Item ID", "Item Name", "Quantity", "Supplier ID", "Supplier", "PR ID", "Price/unit", "Subtotal", "Date", "Status", "PM Name"
            }
        ));
        tblApproved.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(tblApproved);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(txtApprovedSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApprovedSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                        .addComponent(cmbApprovedSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDisaprove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlaceOrder)))
                .addContainerGap())
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbApprovedSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtApprovedSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnApprovedSearch)))
                .addGap(382, 382, 382)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlaceOrder)
                    .addComponent(btnDisaprove))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Approved", panel2);

        btnOrderedSearch.setText("Search");
        btnOrderedSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderedSearchActionPerformed(evt);
            }
        });

        cmbOrderedSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date (Ascending)", "Date (Descending)" }));
        cmbOrderedSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrderedSortActionPerformed(evt);
            }
        });

        tblOrdered.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "Item ID", "Item Name", "Quantity", "Supplier ID", "Supplier", "PR ID", "Price/unit", "Subtotal", "Date", "Status", "PM Name"
            }
        ));
        jScrollPane3.setViewportView(tblOrdered);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(txtOrderedSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrderedSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbOrderedSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbOrderedSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOrderedSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOrderedSearch)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Ordered", panel3);

        btnBack.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(360, 360, 360)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPendingSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendingSearchActionPerformed
        String poid = txtPendingSearch.getText();
        List<PurchaseOrder> poList = po.filterPOByStatus(fm.viewPO(), "Pending");
        searchPO(tblPending, poList, poid);
    }//GEN-LAST:event_btnPendingSearchActionPerformed

    private void btnApprovedSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovedSearchActionPerformed
        String poid = txtApprovedSearch.getText();
        List<PurchaseOrder> poList = po.filterPOByStatus(fm.viewPO(), "Approved");
        searchPO(tblApproved, poList, poid); 
    }//GEN-LAST:event_btnApprovedSearchActionPerformed

    private void btnOrderedSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderedSearchActionPerformed
        String poid = txtOrderedSearch.getText();
        List<PurchaseOrder> poList = po.filterPOByStatus(fm.viewPO(), "Ordered");
        searchPO(tblOrdered, poList, poid);
    }//GEN-LAST:event_btnOrderedSearchActionPerformed

    
    private void cmbOrderedSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrderedSortActionPerformed
        String selected = (String) cmbOrderedSort.getSelectedItem();

        List<PurchaseOrder> poList = po.filterPOByStatus(fm.viewPO(), "Ordered");

        if (selected.equalsIgnoreCase("Date (Ascending)")) {
            sortByDate(tblOrdered, poList, true);
        } else if (selected.equalsIgnoreCase("Date (Descending)")) {
            sortByDate(tblOrdered, poList, false);
        }
    }//GEN-LAST:event_cmbOrderedSortActionPerformed

    private void cmbPendingSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPendingSortActionPerformed
        String selected = (String) cmbPendingSort.getSelectedItem();
        List<PurchaseOrder> poList = po.filterPOByStatus(fm.viewPO(), "Pending");
        
        if (selected.equalsIgnoreCase("Date (Ascending)")) {
            sortByDate(tblPending, poList, true);
        } else if (selected.equalsIgnoreCase("Date (Descending)")) {
            sortByDate(tblPending, poList, false);
        }
    }//GEN-LAST:event_cmbPendingSortActionPerformed

    private void cmbApprovedSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbApprovedSortActionPerformed
        String selected = (String) cmbApprovedSort.getSelectedItem();
        List<PurchaseOrder> poList = po.filterPOByStatus(fm.viewPO(), "Approved");
        
        if (selected.equalsIgnoreCase("Date (Ascending)")) {
            sortByDate(tblApproved, poList, true);
        } else if (selected.equalsIgnoreCase("Date (Descending)")) {
            sortByDate(tblApproved, poList, false);
        }    }//GEN-LAST:event_cmbApprovedSortActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        int selectedRow = tblPending.getSelectedRow();

        if (selectedRow != -1) {
            String poID = (String) tblPending.getValueAt(selectedRow, 0);

            boolean success = po.updatePOStatus(poID, "Approved");

            if (success) {
                JOptionPane.showMessageDialog(this, "PO " + poID + " status changed to Approved.", "Action Success", JOptionPane.INFORMATION_MESSAGE);
                loadPOData();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating", "Action Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No line been selected", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new FMHome(uname).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        int selectedRow = tblPending.getSelectedRow();

        if (selectedRow != -1) {
            String poID = (String) tblPending.getValueAt(selectedRow, 0);

            boolean success = po.updatePOStatus(poID, "Rejected");

            if (success) {
                JOptionPane.showMessageDialog(this, "PO " + poID + " status changed to Rejected.", "Action Success", JOptionPane.INFORMATION_MESSAGE);
                loadPOData();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating", "Action Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No line been selected", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        int[] selectedRows = tblApproved.getSelectedRows();
        
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "No line been selected", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String supplierID = tblApproved.getValueAt(selectedRows[0], 4).toString();
        for (int row : selectedRows) {
            String currentSupplierID = tblApproved.getValueAt(row, 4).toString();
            if (!currentSupplierID.equalsIgnoreCase(supplierID)) {
                JOptionPane.showMessageDialog(this, "All selected items must be from the same supplier.","Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        List<PurchaseOrder> selectedPOs = new ArrayList<>();
        for (int row : selectedRows) {
            String poID = tblApproved.getValueAt(row, 0).toString();
            String itemID = tblApproved.getValueAt(row, 1).toString();
            String itemName = tblApproved.getValueAt(row, 2).toString();
            int quantity = Integer.parseInt(tblApproved.getValueAt(row, 3).toString());
            String supID = tblApproved.getValueAt(row, 4).toString();
            String supName = tblApproved.getValueAt(row, 5).toString();
            String prID = tblApproved.getValueAt(row, 6).toString();
            double pricePerUnit = Double.parseDouble(tblApproved.getValueAt(row, 7).toString());
            double subtotal = Double.parseDouble(tblApproved.getValueAt(row, 8).toString());
            String date = tblApproved.getValueAt(row, 9).toString();
            String status = tblApproved.getValueAt(row, 10).toString();
            String pmName = tblApproved.getValueAt(row, 11).toString();

            PurchaseOrder po = new PurchaseOrder(poID, itemID, itemName, quantity, supID, supName, prID, pricePerUnit, subtotal, date, status, pmName);
            selectedPOs.add(po);
        }
        new FMPOConfirmation(uname, selectedPOs,this).setVisible(true);
    }//GEN-LAST:event_btnPlaceOrderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnApprovedSearch;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDisaprove;
    private javax.swing.JButton btnOrderedSearch;
    private javax.swing.JButton btnPendingSearch;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnReject;
    private javax.swing.JComboBox<String> cmbApprovedSort;
    private javax.swing.JComboBox<String> cmbOrderedSort;
    private javax.swing.JComboBox<String> cmbPendingSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private javax.swing.JTable tblApproved;
    private javax.swing.JTable tblOrdered;
    private javax.swing.JTable tblPending;
    private javax.swing.JTextField txtApprovedSearch;
    private javax.swing.JTextField txtOrderedSearch;
    private javax.swing.JTextField txtPendingSearch;
    // End of variables declaration//GEN-END:variables
}
