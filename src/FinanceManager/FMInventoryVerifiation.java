
package FinanceManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FMInventoryVerifiation extends javax.swing.JFrame {
    
    private String uname;
    private FinanceManager fm = new FinanceManager();
    private Order order = new Order();

    public FMInventoryVerifiation(String uname) {
        initComponents();
        setTitle("OWSB | Finance Manager | Inventory Verification");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.uname = uname;
        loadOrderData();
        
    }
    
    private void loadOrderData() {
       List<Order> orderList = order.readOrderFile();
       populateTable(tblInventoryVerification, orderList);
    }
       
    private void populateTable(JTable table, List<Order> orderList) {
       DefaultTableModel model = (DefaultTableModel) table.getModel();
       model.setRowCount(0); 

        for (Order order : orderList) {
            if ("Delivered".equalsIgnoreCase(order.getStatus())) {
                Object[] row = new Object[8];
                row[0] = order.getOrderID();
                row[1] = order.getSupplierID();
                row[2] = order.getItemID();
                row[3] = order.getQuantity();
                row[4] = order.getStatus();
                row[5] = order.getIMID();
                row[6] = order.getReceivedQty();
                row[7] = order.getReceivedDate();

                model.addRow(row);
            }
        }
    }
    
    private void searchOrder(JTable table, List<Order> order, String orderid) {
        if (orderid == null || orderid.trim().isEmpty()) {
            populateTable(table, order);
            return;
        }

        List<Order> filtered = new ArrayList<>();
        for (Order or : order) {
            if (or.getOrderID().equalsIgnoreCase(orderid.trim())) {
                filtered.add(or);
            }
        }
        populateTable(table, filtered);
    }
    
    private void sortByDate(JTable table, List<Order> orderList, boolean ascending) {
        orderList.sort((po1, po2) -> {
            String d1 = po1.getReceivedDate();
            String d2 = po2.getReceivedDate();
            return ascending ? d1.compareTo(d2) : d2.compareTo(d1);
            });
        populateTable(table, orderList);
    }
    
    private void sortByOrderID(JTable table, List<Order> orderList) {
        orderList.sort(Comparator.comparing(Order::getOrderID));
        populateTable(table, orderList);
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventoryVerification = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cmbSort = new javax.swing.JComboBox<>();
        btnVerify = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Inventory Verification");

        tblInventoryVerification.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "OrderID", "SupID", "ItemID", "Quantity", "Status", "IMID", "VerifiedQty", "VerifiedDate"
            }
        ));
        jScrollPane1.setViewportView(tblInventoryVerification);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cmbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OrderID", "Date (Ascending)", "Date (Descending)" }));
        cmbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortActionPerformed(evt);
            }
        });

        btnVerify.setText("Verify");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

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
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch))
                            .addComponent(btnBack))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnVerify)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(292, 292, 292))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerify)
                .addGap(16, 16, 16)
                .addComponent(btnBack)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String orderid = txtSearch.getText();
        List<Order> orderList = order.readOrderFile();
        searchOrder(tblInventoryVerification, orderList, orderid);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortActionPerformed
        String selected = (String) cmbSort.getSelectedItem();

        List<Order> orderList = order.readOrderFile();

        if (selected.equalsIgnoreCase("OrderID")){
            sortByOrderID(tblInventoryVerification,orderList);
        } else if (selected.equalsIgnoreCase("Date (Ascending)")) {
            sortByDate(tblInventoryVerification, orderList, true);
        } else if (selected.equalsIgnoreCase("Date (Descending)")) {
            sortByDate(tblInventoryVerification, orderList, false);
        }
    }//GEN-LAST:event_cmbSortActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        int selectedRow = tblInventoryVerification.getSelectedRow();
        String date = java.time.LocalDate.now().toString();

        if (selectedRow != -1) {
            String orderID = (String) tblInventoryVerification.getValueAt(selectedRow, 0); 
            String itemID = (String) tblInventoryVerification.getValueAt(selectedRow, 2);

            boolean success = order.updateOrderStatusWithItemID(orderID, itemID, "Verified", date);

            if (success) {
                JOptionPane.showMessageDialog(this, "Order " + orderID + " status changed to Verified.", "Action Success", JOptionPane.INFORMATION_MESSAGE);
                loadOrderData();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating status", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No line been selected", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new FMHome(uname).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnVerify;
    private javax.swing.JComboBox<String> cmbSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventoryVerification;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
