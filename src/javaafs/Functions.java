/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaafs;
import java.io.*;
import java.util.*;
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
                    data.add(line.split(","));
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
}
