/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaafs;

import java.util.ArrayList;



/**
 *
 * @author zhenz
 */
public class JavaAFS {

    
    
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String[]> userArray = Functions.readCSV("users.txt");
        for (String[] user : userArray) {
            System.out.println(user[1]); // username
        }
    }
    
}
