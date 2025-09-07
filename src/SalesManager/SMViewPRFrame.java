package SalesManager;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class SMViewPRFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public SMViewPRFrame() {
        setTitle("View Purchase Requisitions");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Requisition ID", "Sales Manager ID", "Date", "Item ID", "Quantity"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultEditor(Object.class,null);
        
        JPanel buttonPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton, BorderLayout.WEST);

        add(buttonPanel, BorderLayout.SOUTH);


        add(scrollPane);
        loadData();
        setVisible(true);
    }

    private void loadData() {
        List<PurchaseRequisition> requisitions = PurchaseRequisition.loadAll();
        for (PurchaseRequisition pr : requisitions) {
            for (PurchaseRequisition.Item item : pr.getItems()) {
                tableModel.addRow(new Object[]{
                    pr.getRequisitionID(),
                    pr.getSalesManagerID(),
                    pr.getDate(),
                    item.getItemCode(),
                    item.getQuantity()
                });
            }
        }
    }
}


