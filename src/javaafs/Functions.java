/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaafs;
import java.io.*;
import java.util.*;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author zhenz
 */
public class Functions {

    // Reusable Txt file reader
    public static ArrayList<String[]> readCSV(String filePath) {
        ArrayList<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] values = line.split(",");
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    data.add(values);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        return data;
    }
    
    //Reusable txt file writer
    public static void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                String line = String.join(",", row);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + filePath);
            e.printStackTrace();
        }
    }

    //Login page authenticate user
    public boolean authUser(List<String[]> userList, String userID, String password) {
        for (String[] user : userList) {
            if (user[0].equals(userID) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    //Login page determine user type
    public String userType(List<String[]> userList, String userID){
        String userType = null;
        for (String[] user : userList) {
            if (user[0].equals(userID)) {
                userType = user[2].toString();
            }
        }
        return userType;
    }
    
    //Profile component update password
    public boolean updatePassword(List<String[]> userList, String userID, String newPassword) {

        for (String[] user : userList) {
            if (user[0].equals(userID)) {
                user[1] = newPassword;   
                return true;             
            }
        }
        return false;
    }
    
    //profile component save password
    public void savePassword(List<String[]> userList, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] user : userList) {
                bw.write(String.join(",", user));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //profile component save profile changes
    public void saveProfile(List<String[]> userList, String userID, String newEmail, String newPhoneNumber) {
        for (String[] user : userList) {
            if (user[0].equalsIgnoreCase(userID)) {
                user[4] = newEmail;   
                user[5] = newPhoneNumber;
                break;
            }
        }
    }
    
    public String generateNextID(String filePath, String type) {
        ArrayList<String[]> modules = readCSV(filePath);
        int maxID = 0;

        for (String[] row : modules) {
            if (row.length > 0) {
                String id = row[0]; 
                if (id.startsWith(type)) {
                    try {
                        int num = Integer.parseInt(id.substring(type.length())); // extract numeric part
                        if (num > maxID) {
                        maxID = num;
                        }
                    } catch (NumberFormatException e) {
                    
                    }
                }
            }
        }
        int nextID = maxID + 1;
        return String.format("%s%03d", type, nextID);
    }
    
//    public void addRow(String filePath, JTable table, String[] rowData) {
//        ArrayList<String[]> data = readCSV(filePath);
//        data.add(rowData);
//        writeCSV(filePath, data);
//
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.addRow(rowData);
//    }
    
    public void editRow(String filePath, JTable table, int idColumnIndex, int editColumnIndex) {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        return;
    }

    String idValue = table.getValueAt(selectedRow, idColumnIndex).toString();
    String newValue = table.getValueAt(selectedRow, editColumnIndex).toString().trim();

    if (newValue.isEmpty()) {
        return;
    }

    // Update CSV
    ArrayList<String[]> data = readCSV(filePath);
    for (String[] row : data) {
        if (row[idColumnIndex].equalsIgnoreCase(idValue)) {
            row[editColumnIndex] = newValue;
            break;
        }
    }
    writeCSV(filePath, data);
}
    
    public void deleteRow(String filePath, JTable table, int idColumnIndex) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return; 

        String idValue = table.getValueAt(selectedRow, idColumnIndex).toString();

        // Remove from CSV
        ArrayList<String[]> data = readCSV(filePath);
        data.removeIf(row -> row[idColumnIndex].equalsIgnoreCase(idValue));
        writeCSV(filePath, data);

        // Remove from table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

    public void saveTableChanges(javax.swing.JTable table, String filePath) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<String[]> data = new ArrayList<>();
        
        for (int i = 0; i < model.getRowCount(); i++) {
            int colCount = model.getColumnCount();
            String[] row = new String[colCount];
            
            for (int j = 0; j < colCount; j++) {
                Object cellValue = model.getValueAt(i, j);
                row[j] = (cellValue != null) ? cellValue.toString() : "";
            }

            data.add(row);
        }
    
        writeCSV(filePath, data);
    }
}

    
