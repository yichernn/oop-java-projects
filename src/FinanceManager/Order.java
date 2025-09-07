/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager;

import java.io.*;
import java.util.*;

public class Order {
    private String orderID;
    private String poID;
    private String supplierID;
    private String fmID;
    private String itemID;
    private int quantity;
    private double pricePerUnit;
    private double total;
    private double subtotal;
    private String orderedDate;
    private String status;
    private String imID;
    private int receivedQty;
    private String receivedDate;
    private String verifiedDate;
    private String orderFile = "Order.txt";
    
    public Order(){
    }

    public Order(String orderID, String poID, String supplierID, String fmID, String itemID,
                 int quantity, double pricePerUnit, double total, double subtotal, String orderedDate,
                 String status, String imID, int receivedQty, String receivedDate,
                 String verifiedDate) {
        this.orderID = orderID;
        this.poID = poID;
        this.supplierID = supplierID;
        this.fmID = fmID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.total = total;
        this.subtotal = subtotal;
        this.orderedDate = orderedDate;
        this.status = status;
        this.imID = imID;
        this.receivedQty = receivedQty;
        this.receivedDate = receivedDate;
        this.verifiedDate = verifiedDate;
    }

    public String getOrderID() { return orderID; }
    public String getPoID() { return poID; }
    public String getSupplierID() { return supplierID; }
    public String getfmID() { return fmID; }
    public String getItemID() { return itemID; }
    public int getQuantity() { return quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public double getTotal() { return total; }
    public double getSubtotal() { return subtotal; }
    public String getOrderedDate() { return orderedDate; }
    public String getStatus() { return status; }
    public String getIMID() { return imID; }
    public int getReceivedQty() { return receivedQty; }
    public String getReceivedDate() { return receivedDate; }
    public String getVerifiedDate() { return verifiedDate; }
    public void setStatus(String status) { this.status = status; }
    public void setVerifiedDate(String verifiedDate) { this.verifiedDate = verifiedDate; }

    public List<Order> readOrderFile() {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 15) {
                    Order order = new Order(
                            parts[0].trim(),
                            parts[1].trim(), 
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            Integer.parseInt(parts[5].trim()),
                            Double.parseDouble(parts[6].trim()), 
                            Double.parseDouble(parts[7].trim()), 
                            Double.parseDouble(parts[8].trim()), 
                            parts[9].trim(), 
                            parts[10].trim(), 
                            parts[11].trim(), 
                            Integer.parseInt(parts[12].trim()), 
                            parts[13].trim(), 
                            parts[14].trim()
                    );
                    orders.add(order);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return orders;
    }
        
    public boolean updateOrderStatus(String poID, String newStatus, String Date) {
        List<Order> orderList = readOrderFile(); 
        boolean statusUpdated = false;
        
        for (Order or : orderList) {
            if (or.getOrderID().equalsIgnoreCase(poID)) {
                or.setStatus(newStatus); 
                or.setVerifiedDate(Date);
                statusUpdated = true;
                break; 
            }
        }
        if (statusUpdated) {
            return saveOrderFile(orderList);
        } else {
            return false; 
        }
    }

    public boolean updateOrderStatusWithItemID(String poID, String itemID, String newStatus, String Date) {
        List<Order> orderList = readOrderFile(); 
        boolean statusUpdated = false;
        
        for (Order or : orderList) {
            if (or.getOrderID().equalsIgnoreCase(poID)&& or.getItemID().equalsIgnoreCase(itemID)) {
                System.out.println("Match found: updating status");
                or.setStatus(newStatus); 
                or.setVerifiedDate(Date);
                statusUpdated = true;
                break; 
            }
        }
        if (statusUpdated) {
            return saveOrderFile(orderList);
        } else {
            return false; 
        }
    }
    
    public boolean saveOrderFile(List<Order> orderList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFile))) {
            for (Order or : orderList) {
                writer.write(or.getOrderID() + "," + or.getPoID() + "," + or.getSupplierID() + ","
                        + or.getfmID() + "," + or.getItemID() + "," + or.getQuantity() + ","
                        + or.getPricePerUnit() + "," + or.getTotal() + "," + or.getSubtotal() + ","
                        + or.getOrderedDate() + "," + or.getStatus() + "," + or.getIMID() + ","
                        + or.getReceivedQty() + "," + or.getReceivedDate() + "," + or.getVerifiedDate());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error while saving to the file: " + e.getMessage());
            return false;
        }
    }
    
public List<String[]> getOrderVerificationSummary() {
    Map<String, List<Order>> groupedOrders = new LinkedHashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length >= 15) {
                Order order = new Order(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        Integer.parseInt(parts[5].trim()),
                        Double.parseDouble(parts[6].trim()),
                        Double.parseDouble(parts[7].trim()),
                        Double.parseDouble(parts[8].trim()),
                        parts[9].trim(),
                        parts[10].trim(),
                        parts[11].trim(),
                        Integer.parseInt(parts[12].trim()),
                        parts[13].trim(),
                        parts[14].trim()
                );

                groupedOrders.computeIfAbsent(order.getOrderID(), k -> new ArrayList<>()).add(order);
            }
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }

    List<String[]> summary = new ArrayList<>();

    for (Map.Entry<String, List<Order>> entry : groupedOrders.entrySet()) {
        String orderID = entry.getKey();
        List<Order> orders = entry.getValue();

        boolean allPaid = orders.stream().allMatch(o -> "Paid".equalsIgnoreCase(o.getStatus()));
        if (allPaid) {
            continue;
        }

        String supplierID = orders.get(0).getSupplierID();
        int total = orders.size();
        int verified = 0;

        for (Order o : orders) {
            if ("Verified".equalsIgnoreCase(o.getStatus())) {
                verified++;
            }
        }

        String status = (verified == total) ? "Ready to Pay" : "Pending";

        summary.add(new String[] {
            orderID,
            supplierID,
            verified + " / " + total,
            status
        });
    }

    return summary;
    }

    
    public boolean isOrderReadyForPayment(String orderID) {
        List<Order> orders = readOrderFile();

        int total = 0;
        int verified = 0;

        for (Order o : orders) {
            if (o.getOrderID().equalsIgnoreCase(orderID)) {
                total++;
                if ("Verified".equalsIgnoreCase(o.getStatus())) {
                    verified++;
                }
            }
        }

        return total > 0 && total == verified;
    }
    
    public List<Order> getFullOrderInfo(String orderID) {
        List<Order> allOrders = readOrderFile();
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getOrderID().equalsIgnoreCase(orderID)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

}