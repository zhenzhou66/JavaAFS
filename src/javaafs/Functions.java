/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaafs;
import java.io.*;
import java.util.*;
import javax.swing.JPasswordField;

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
}
    
