
package javaafs;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class UserFunctions {

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
    
    public static String generateNextID(String filePath, String type) {
        ArrayList<String[]> data = readCSV(filePath);
        int maxID = 0;
        for (String[] row : data) {
            if (row.length > 0 && row[0].startsWith(type)) {
                try {
                    int num = Integer.parseInt(row[0].substring(type.length()));
                    if (num > maxID) maxID = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("%s%03d", type, maxID + 1);
    }

    //Login page authenticate user
    public boolean authUser(List<String[]> userList, String userID, String password) {
        String hashedPassword = hashPassword(password);
        for (String[] user : userList) {
            if (user[0].equals(userID) && user[1].equals(hashedPassword)) {
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
    
//    public static String generateNextID(String filePath, String type) {
//        ArrayList<String[]> modules = readCSV(filePath);
//        int maxID = 0;
//
//        for (String[] row : modules) {
//            if (row.length > 0) {
//                String id = row[0]; 
//                if (id.startsWith(type)) {
//                    try {
//                        int num = Integer.parseInt(id.substring(type.length())); // extract numeric part
//                        if (num > maxID) {
//                        maxID = num;
//                        }
//                    } catch (NumberFormatException e) {
//                    
//                    }
//                }
//            }
//        }
//        int nextID = maxID + 1;
//        return String.format("%s%03d", type, nextID);
//    }
    
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
    
    public static String calculateGrade(int mark, List<String[]> grdCriteria) {
        if (grdCriteria == null) return "N/A";

        for (String[] gradeRow : grdCriteria) {
            try {
                int min = Integer.parseInt(gradeRow[1].trim());
                int max = Integer.parseInt(gradeRow[2].trim());

                if (mark >= min && mark <= max) {
                    return gradeRow[0]; // Returns 'A', 'B', etc.
                }
            } catch (NumberFormatException e) {
                // Skips rows that aren't numbers (like headers)
                continue;
            }
        }
        return "N/A";
    }
    
    
    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }
    
    
    // ================== Profile Input Validations ==================
    public static boolean isValidUserName(String userName) {
        if (userName == null || userName.trim().isEmpty() || userName.length() > 50) return false;
        for (char c : userName.toCharArray()) if (!Character.isLetter(c) && c != ' ') return false;
        return true;
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10) return false;
        for (char c : phone.toCharArray()) if (!Character.isDigit(c)) return false;
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.length() > 20) return false;
        return email.endsWith("@gmail.com") || email.endsWith("@yahoo.com");
    }

    public static boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty() && !password.equals("********");
    }

    // ================== Profile Field Setup ==================
    public static void setReadOnly(JTextField field) {
        field.setEditable(false);
        field.setFocusable(false);
    }

    public static void setupUserNameField(JTextField userName) {
        userName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ') evt.consume();
                if (userName.getText().length() >= 50) evt.consume();
            }
        });
    }

    public static void setupPhoneField(JTextField phNumber) {
        phNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) evt.consume();
                if (phNumber.getText().length() >= 10) evt.consume();
            }
        });
    }

    public static void setupPasswordField(JPasswordField passwordField) {
        passwordField.setText("********");
        passwordField.setColumns(20);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordField.setText("");
            }
        });
    }
    
    
    public String getForceChange(List<String[]> users, String userID) {

        for (String[] row : users) {
            if (row[0].equals(userID)) {
                return row[7]; // forceChange column
            }
        }

        return "false"; 
    }
    
    

    public static boolean isForceChangeRequired(String username, List<String[]> users) {
        for (String[] row : users) {
            if (row[0].equals(username)) {
                return "true".equalsIgnoreCase(row[7]);
            }
        }
        return false;
    }


    
    
    
    public void loadProfile(JTextField userIDField, JPasswordField passwordField, JTextField roleField,
                        JTextField nameField, JTextField emailField, JTextField phoneField, String userID) {

    ArrayList<String[]> users = readCSV("users.txt");

    for (String[] user : users) {
        if (user[0].equals(userID)) {
            userIDField.setText(user[0]);
            passwordField.setText("********");  // Always mask
            roleField.setText(user[2]);
            nameField.setText(user[3]);
            emailField.setText(user[4]);
            phoneField.setText(user[5]);
            break;
        }
    }
}
    
    public void saveProfileChanges(JTextField userIDField, JPasswordField passwordField,JTextField nameField, JTextField emailField, JTextField phoneField) {

        String userID = userIDField.getText();
        String rawPassword = new String(passwordField.getPassword()).trim();
        String name = nameField.getText().trim();
        String mail = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        // Check for empty fields
        if (userID.isEmpty() || name.isEmpty() || mail.isEmpty() || phone.isEmpty() || rawPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "All fields must be filled out.",
                    "Empty Field Detected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Load CSV
        ArrayList<String[]> users = readCSV("users.txt");

        for (String[] user : users) {
            if (user[0].equals(userID)) {

                // Check forceChange
                boolean forceChange = user.length > 7 && "true".equalsIgnoreCase(user[7]);

                // ===== PASSWORD VALIDATION =====
                if (forceChange) {
                    if (rawPassword.isEmpty() || rawPassword.equals("********")) {
                        JOptionPane.showMessageDialog(null,
                                "You must change your password before proceeding.",
                                "Password Required",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    user[1] = hashPassword(rawPassword);
                    user[7] = "false"; // forceChange done
                } else {
                    if (!rawPassword.isEmpty() && !rawPassword.equals("********")) {
                        user[1] = hashPassword(rawPassword);
                    }
                }

                // Name validation
                if (!name.isEmpty() && isValidUserName(name)) {
                    user[3] = name;
                }

                // Email validation
                if (isValidEmail(mail)) user[4] = mail;
                else {
                    JOptionPane.showMessageDialog(null,
                            "Email must end with @gmail.com or @yahoo.com (max 20 chars).",
                            "Invalid Email",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Phone validation
                if (isValidPhone(phone)) user[5] = phone;
                else {
                    JOptionPane.showMessageDialog(null,
                            "Phone number must be exactly 10 digits.",
                            "Invalid Phone",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                break;
            }
        }

        // Save CSV
        writeCSV("users.txt", users);
        JOptionPane.showMessageDialog(null, "Profile updated successfully!");
    }
    
    
    
    
    // ================== Delete Row From CSV ==================
    public static boolean deleteLineFromFile(String fileName, String idToDelete, int columnIndex) {
        File inputFile = new File(fileName);
        File tempFile = new File("temp_" + fileName);

        if (!inputFile.exists()) {
            System.out.println("File not found: " + fileName);
            return false;
        }

        boolean deleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > columnIndex && parts[columnIndex].equals(idToDelete)) {
                    deleted = true; // mark that a row is deleted
                    continue;       // skip this line
                }
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Replace original file with temp file
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error replacing original file after deletion: " + fileName);
            return false;
        }

        return deleted;
    }
    

    
}

    
