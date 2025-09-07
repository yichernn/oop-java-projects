package Admin;

import User.User;
import javax.swing.JOptionPane;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Admin extends User {
    private String userID;
    private String name;
    private String email;
    private Role role;
    private String password;

    public static final String LOGINFILE = "LoginLog.txt";
    private static final DateTimeFormatter SIMPLE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static enum Role {
        SALES_MANAGER,
        PURCHASE_MANAGER,
        INVENTORY_MANAGER,
        FINANCE_MANAGER,
        ADMIN;

        @Override
        public String toString() {
            String s = name().replace('_', ' ').toLowerCase();
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
    }
    
    public Admin(){}

    public Admin(String userID, String name, String email, Role role, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Admin(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 5) {
            throw new IllegalArgumentException("Malformed CSV line: " + csvLine);
        }
        this.userID = parts[0].trim();
        this.name = parts[1].trim();
        this.email = parts[2].trim();
        this.role = Role.valueOf(parts[3].trim().toUpperCase());
        this.password = parts[4].trim();
    }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public void Logout() {
        JOptionPane.showMessageDialog(null, "Admin logout Successful", "", JOptionPane.INFORMATION_MESSAGE);
    }

    public String toCSVLine() {
        return String.join(",", userID, name, email, role.name(), password);
    }

    @Override
    public String toString() {
        return String.format("Admin[%s, %s, %s, %s]", userID, name, email, role.name());
    }

    public static String getNextID(String filename, String defaultPrefix) {
        File f = new File(filename);
        if (!f.exists()) {
            return defaultPrefix + "001";
        }

        int maxSuffix = 0;
        String prefix = "";

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 1) continue;

                String id = parts[0].trim();
                if (id.length() < 4) continue;

                int idx = 0;
                while (idx < id.length() && !Character.isDigit(id.charAt(idx))) {
                    idx++;
                }
                if (idx == 0 || idx >= id.length()) continue;

                String idPrefix = id.substring(0, idx);
                String numericPart = id.substring(idx);
                if (!numericPart.matches("\\d{3}")) continue;

                int val = Integer.parseInt(numericPart);
                if (maxSuffix == 0) {
                    prefix = idPrefix;
                }
                if (idPrefix.equalsIgnoreCase(prefix) && val > maxSuffix) {
                    maxSuffix = val;
                }
            }
        } catch (IOException ex) {
            return defaultPrefix + "001";
        }

        if (maxSuffix == 0 || prefix.isEmpty()) {
            prefix = defaultPrefix;
        }
        int nextVal = maxSuffix + 1;
        return prefix.toUpperCase() + String.format("%03d", nextVal);
    }

    public static void logLogin(String username, String role) {
        String timestamp = LocalDateTime.now().format(SIMPLE_FORMATTER);
        String line = username + "," + role + "," + timestamp;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGINFILE, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException ioe) {
            System.err.println("Unable to write to login log: " + ioe.getMessage());
        }
    }

    public static List<Map<String, String>> readAllLogins() {
        List<Map<String, String>> result = new ArrayList<>();
        File logFile = new File(LOGINFILE);

        if (!logFile.exists()) {
            return result;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length < 3) continue;
                Map<String, String> entry = new HashMap<>();
                entry.put("username", parts[0].trim());
                entry.put("role", parts[1].trim());
                entry.put("timestamp", parts[2].trim());
                result.add(entry);
            }
        } catch (IOException ioe) {
            System.err.println("Error reading login log: " + ioe.getMessage());
        }

        return result;
    }
}
