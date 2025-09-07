package SalesManager;


import User.LoginPage;
import javax.swing.JButton;
import javax.swing.JFrame;


public class SMHomePage extends javax.swing.JFrame {

    SalesManager sm = new SalesManager();
    
    public SMHomePage(String salesManager001) {
        initComponents();
        
        setTitle("Sales Manager Dashboard - " + salesManager001);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        // Item Management Button
        JButton itemBtn = new JButton("Item Management");
        itemBtn.addActionListener(e -> new SMItemManagementFrame().setVisible(true));
       
        btnItemManagementFrame.addActionListener(e -> new SMItemManagementFrame().setVisible(true));
        btnSupplierManagementFrame.addActionListener(e -> new SMSupplierManagerFrame().setVisible(true));
        btnSalesEntryFrame.addActionListener(e -> new SMSalesEntryFrame().setVisible(true));
        btnCreatePRFrame.addActionListener(e -> new SMCreatePRFrame().setVisible(true));
        btnViewPRFrame.addActionListener(e -> new SMViewPRFrame().setVisible(true));
        btnViewPOFrame.addActionListener(e -> new SMViewPOFrame().setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose(); 
        });

        setVisible(true); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnItemManagementFrame = new javax.swing.JButton();
        btnSupplierManagementFrame = new javax.swing.JButton();
        btnSalesEntryFrame = new javax.swing.JButton();
        btnCreatePRFrame = new javax.swing.JButton();
        btnViewPRFrame = new javax.swing.JButton();
        btnViewPOFrame = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sales Manager");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnItemManagementFrame.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnItemManagementFrame.setText("Item Mangement");
        btnItemManagementFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemManagementFrameActionPerformed(evt);
            }
        });

        btnSupplierManagementFrame.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSupplierManagementFrame.setText("Supplier Management");
        btnSupplierManagementFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierManagementFrameActionPerformed(evt);
            }
        });

        btnSalesEntryFrame.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSalesEntryFrame.setText("Sales Entry");

        btnCreatePRFrame.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCreatePRFrame.setText("Create Purchase Requisition");

        btnViewPRFrame.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnViewPRFrame.setText("View Requisitions");

        btnViewPOFrame.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnViewPOFrame.setText("View Purchase Order");

        btnLogout.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setPreferredSize(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("CZ | Sales Manager");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnItemManagementFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplierManagementFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalesEntryFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreatePRFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewPRFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewPOFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnItemManagementFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSupplierManagementFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalesEntryFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreatePRFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewPRFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewPOFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnItemManagementFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemManagementFrameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnItemManagementFrameActionPerformed

    private void btnSupplierManagementFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierManagementFrameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupplierManagementFrameActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new LoginPage().setVisible(true);
        sm.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreatePRFrame;
    private javax.swing.JButton btnItemManagementFrame;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSalesEntryFrame;
    private javax.swing.JButton btnSupplierManagementFrame;
    private javax.swing.JButton btnViewPOFrame;
    private javax.swing.JButton btnViewPRFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
