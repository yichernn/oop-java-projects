package SalesManager;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PurchaseRequisition {
    private String requisitionID;
    private String salesManagerID;
    private String date;
    private List<Item> items;

    private static final String FILE_PATH = "Requisitions.txt";

    public static class Item {
        private String itemCode;
        private int quantity;

        public Item(String itemCode, int quantity) {
            this.itemCode = itemCode;
            this.quantity = quantity;
        }

        public String getItemCode() {
            return itemCode;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    public PurchaseRequisition(String salesManagerID) {
        this.requisitionID = generateNextRequisitionID(); // <-- changed
        this.salesManagerID = salesManagerID;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.items = new ArrayList<>();
    }

    private static String generateNextRequisitionID() {
        String prefix = "PR";
        int maxNumber = 0;

        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("RequisitionID:")) {
                        String id = line.split(":")[1].trim();  // e.g. PR002
                        if (id.startsWith(prefix)) {
                            try {
                                int num = Integer.parseInt(id.substring(prefix.length()));
                                if (num > maxNumber) {
                                    maxNumber = num;
                                }
                            } catch (NumberFormatException e) {
                                // Skip bad format
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int nextNumber = maxNumber + 1;
        return String.format("%s%03d", prefix, nextNumber); // e.g. PR001
    }


    public PurchaseRequisition(String requisitionID, String salesManagerID, String date, List<Item> items) {
        this.requisitionID = requisitionID;
        this.salesManagerID = salesManagerID;
        this.date = date;
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void saveToFile() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            out.println("RequisitionID: " + requisitionID);
            out.println("SalesManagerID: " + salesManagerID);
            out.println("Date: " + date);
            for (Item item : items) {
                out.println("Item: " + item.getItemCode() + "," + item.getQuantity());
            }
            out.println("---");
        }
    }

    public static List<PurchaseRequisition> loadAll() {
        List<PurchaseRequisition> requisitions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            String requisitionID = "", salesManagerID = "", date = "";
            List<Item> items = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.startsWith("RequisitionID:")) {
                    requisitionID = line.split(":")[1].trim();
                } else if (line.startsWith("SalesManagerID:")) {
                    salesManagerID = line.split(":")[1].trim();
                } else if (line.startsWith("Date:")) {
                    date = line.split(":")[1].trim();
                } else if (line.startsWith("Item:")) {
                    String[] parts = line.split(":")[1].trim().split(",");
                    String itemCode = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    items.add(new Item(itemCode, quantity));
                } else if (line.equals("---")) {
                    requisitions.add(new PurchaseRequisition(requisitionID, salesManagerID, date, new ArrayList<>(items)));
                    items.clear();
                }
            }

            if (!items.isEmpty()) {
                requisitions.add(new PurchaseRequisition(requisitionID, salesManagerID, date, new ArrayList<>(items)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requisitions;
    }

    public String getRequisitionID() {
        return requisitionID;
    }

    public String getSalesManagerID() {
        return salesManagerID;
    }

    public String getDate() {
        return date;
    }

    public List<Item> getItems() {
        return items;
    }
}
