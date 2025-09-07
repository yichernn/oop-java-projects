
package FinanceManager;

import User.LoginPage;

public class FMHome extends javax.swing.JFrame {

    private String loggedInUser;
    private FinanceManager fm = new FinanceManager();
    
    public FMHome(String uname) {
        initComponents();
        setTitle("OWSB | Finance Manager | Home Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
                
        this.loggedInUser = uname;
        lblUname.setText(uname + " | Financial Manager");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        lblUname = new javax.swing.JLabel();
        btnPO = new javax.swing.JButton();
        btnInventory = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnFReport = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnViewPR = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Finance Manager Dashboard");

        lblUname.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblUname.setText("jLabel2");

        btnPO.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnPO.setText("Purchase Order");
        btnPO.setPreferredSize(new java.awt.Dimension(10, 26));
        btnPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPOActionPerformed(evt);
            }
        });

        btnInventory.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnInventory.setText("Inventory Verification");
        btnInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventoryActionPerformed(evt);
            }
        });

        btnPayment.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnPayment.setText("Process Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        btnFReport.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnFReport.setText("Financial Report");
        btnFReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFReportActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnViewPR.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnViewPR.setText("View Purchase Requisition");
        btnViewPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(375, Short.MAX_VALUE)
                .addComponent(lblUname)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViewPR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUname)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(btnPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInventory)
                .addGap(18, 18, 18)
                .addComponent(btnPayment)
                .addGap(18, 18, 18)
                .addComponent(btnFReport)
                .addGap(18, 18, 18)
                .addComponent(btnViewPR)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPOActionPerformed
        new FMPO(loggedInUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPOActionPerformed

    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryActionPerformed
        new FMInventoryVerifiation(loggedInUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInventoryActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        new FMProcessPayment(loggedInUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnFReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFReportActionPerformed
        new FMFinancialReport(loggedInUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFReportActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new LoginPage().setVisible(true);
        fm.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPRActionPerformed
        new FMViewPR(loggedInUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewPRActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFReport;
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPO;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnViewPR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblUname;
    // End of variables declaration//GEN-END:variables
}
