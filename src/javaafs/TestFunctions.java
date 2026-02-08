/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaafs;
import java.util.List;

/**
 *
 * @author junjun
 */
public class TestFunctions {
    public static void main(String[] args) {
        // Create a Functions object
        Functions func = new Functions();

        // Read the CSV file
        List<String[]> users = func.readCSV("leaderLecturerRelationship.txt"); // Make sure the path is correct

        // Check if file is loaded
        if (users == null || users.isEmpty()) {
            System.out.println("File is empty or not found!");
        } else {
            System.out.println("CSV loaded successfully:");
            for (String[] user : users) {
                for (String field : user) {
                    System.out.print(field + " | ");
                }
                System.out.println();
            }
        }

        // Test authentication
        boolean auth = func.authUser(users, "lijun", "lijun");
        System.out.println("Auth for 'lijun' with password 'lijun'hjgjhgjhgjkhg: " + auth);
    }
}
