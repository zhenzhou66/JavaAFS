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

    // Optional: for comma-separated (CSV-style) txt files
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
    public boolean authUser(List<String[]> userList, String userID, String password) {
        for (String[] user : userList) {
            if (user[0].equals(userID) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public String userType(List<String[]> userList, String userID){
        String userType = null;
        for (String[] user : userList) {
            if (user[0].equals(userID)) {
                userType = user[2].toString();
            }
        }
        return userType;
    }
    
    public boolean updatePassword(List<String[]> userList, String userID, String newPassword) {

        for (String[] user : userList) {
            if (user[0].equals(userID)) {
                user[1] = newPassword;   
                return true;             
            }
        }
        return false;
    }
    
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
}
    
