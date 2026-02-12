
package javaafs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class UserManagement extends javax.swing.JFrame {


    private final String username;
    private UserFunctions func = new UserFunctions();
    private final String filePath = "users.txt";





    public UserManagement(String username) {
        this.username = username;
        initComponents();
        loadUsers();
        initTableSelection();
        resetPassword.setEnabled(false);
        
        
        
        // ===== Attach search button & Enter key to searchUsers =====
        searchButton.addActionListener(e -> searchUsers());
        userIDFilter.addActionListener(e -> searchUsers());
        


    }
    
    
    
    
    
    private void loadUsers(JTable userTable) {
        ArrayList<String[]> users = UserFunctions.readCSV(filePath);
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);
        // Skip first row (header)
        for (int i = 1; i < users.size(); i++) { 
            String[] user = users.get(i);
            // Add only the columns you want to display
            // Example: userID, name, role, phone, email, moduleID
            if (user.length >= 7) {
                model.addRow(new Object[]{
                    user[0], // userID
                    user[3], // name
                    user[2], // role
                    user[5], // phone
                    user[4], // email
                    user[6]  // moduleID
                });
            }
        }
    }
    
    private void loadUsers() {
        loadUsers(userTable);
    }


    
    
    private void initTableSelection() {
        userTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = userTable.getSelectedRow();

                if (row != -1) {
                    // A row is selected → populate fields
                    String userID = userTable.getValueAt(row, 0).toString();
                    String name = userTable.getValueAt(row, 1).toString();
                    String role = userTable.getValueAt(row, 2).toString();
                    String phone = userTable.getValueAt(row, 3).toString();
                    String email = userTable.getValueAt(row, 4).toString();

                    usernameTextField.setText(name);
                    roleDropdown.setSelectedItem(role);
                    phNumberTextField.setText(phone);
                    emailTextField.setText(email);

                    // Disable Add button in edit mode
                    addButton.setEnabled(false);
                    resetPassword.setEnabled(true);

                } else {
                    // No row selected → enable Add button
                    addButton.setEnabled(true);
                    resetPassword.setEnabled(false);

                    // Optionally clear fields
                    usernameTextField.setText("");
                    roleDropdown.setSelectedIndex(0);
                    phNumberTextField.setText("");
                    emailTextField.setText("");
                }
            }
        });
    }
    
    

    private String generateTempPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "abcdefghijklmnopqrstuvwxyz"
                     + "0123456789"
                     + "!@#$%^&*";

        StringBuilder tempPass = new StringBuilder();
        java.util.Random rand = new java.util.Random();

        for (int i = 0; i < 8; i++) {
            tempPass.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return tempPass.toString();
    }

    
    
    private void resetPasswordAction() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user.");
            return;
        }

        String userID = userTable.getValueAt(selectedRow, 0).toString();

        // Generate temporary password
        String tempPassword = generateTempPassword();
//        String hashedPassword = UserFunctions.hashPassword(tempPassword);

        // Load users
        ArrayList<String[]> users = UserFunctions.readCSV(filePath);
        boolean userFound = false;

        for (String[] user : users) {
            if (user[0].equals(userID)) {
                user[1] = tempPassword;  // update password
                if (user.length > 7) user[7] = "true"; // mark force-change
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            JOptionPane.showMessageDialog(this, "User not found.");
            return;
        }

        // Save changes back to CSV
        UserFunctions.writeCSV(filePath, users);

        JOptionPane.showMessageDialog(this, "Temporary Password:\n" + tempPassword);
    }

//    
    private boolean validateUserInputs(String username, String phone, String email) {

        if (!username.matches("([A-Z][a-z]*)(\\s[A-Z][a-z]*)*")) {
            JOptionPane.showMessageDialog(this,
                    "Username must contain only letters and spaces, and each word must start with a capital letter.");
            return false;
        }
        
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                    "Phone number must contain exactly 10 digits.");
            return false;
        }

        if (!email.matches("[\\w\\.]+@(gmail|yahoo)\\.com") || email.length() > 20) {
            JOptionPane.showMessageDialog(this,
                    "Email must end with @gmail.com or @yahoo.com and max 20 characters.");
            return false;
        }


        return true;
    }

    
    private String formatUsername(String username) {
        // Trim spaces from both ends
        username = username.trim();

        // Split by spaces
        String[] words = username.split("\\s+");
        StringBuilder formatted = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                // Capitalize first letter, lowercase the rest
                formatted.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    formatted.append(word.substring(1).toLowerCase());
                }
                formatted.append(" "); // add space between words
            }
        }

        return formatted.toString().trim(); // remove trailing space
    }


    
    private String generateUserID(UserManage user) {

        String prefix = user.getPrefix();   // polymorphism here 🔥
        int maxNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                String existingID = parts[0];

                if (existingID.startsWith(prefix)) {

                    String numberPart = existingID.substring(prefix.length());

                    try {
                        int num = Integer.parseInt(numberPart);
                        if (num > maxNumber) {
                            maxNumber = num;
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return prefix + String.format("%03d", maxNumber + 1);
    }

    
    
    // Get role prefix from role name
//    private String getRolePrefix(String role) {
//        switch (role) {
//            case "Admin": return "A";
//            case "Academic Leader": return "AL";
//            case "Lecturer": return "L";
//            case "Student": return "S";
//            default: return "";
//        }
//    }

    // Get role prefix from an existing userID (e.g., "A003" → "A")
//    private String getRolePrefixFromID(String userID) {
//        // Extract letters at the start
//        return userID.replaceAll("\\d+", "");
//    }

    
    
    private void searchUsers() {
        String query = userIDFilter.getText().trim().toLowerCase().replaceAll("\\s+", "");
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // clear table
        
        ArrayList<String[]> matchedUsers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String userID = parts[0].toLowerCase().replaceAll("\\s+", "");
                    String role = parts[2].toLowerCase().replaceAll("\\s+", "");
                    String name = parts[3].toLowerCase().replaceAll("\\s+", "");
                    String email = parts[4].toLowerCase().replaceAll("\\s+", "");
                    String phone = parts[5].toLowerCase().replaceAll("\\s+", "");

                    // Check if query matches any field
                    if (userID.contains(query) || role.contains(query) || 
                        name.contains(query) || email.contains(query) || 
                        phone.contains(query)) {

                        matchedUsers.add(parts);
                        
//                        // Add original row to table
//                        DefaultTableModel m = (DefaultTableModel) userTable.getModel();
//                        m.addRow(new Object[]{parts[0], parts[3], parts[2], parts[5], parts[4], parts[6]});
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading users file.");
            return;
        }
        
        matchedUsers.sort((a, b) -> a[3].compareToIgnoreCase(b[3]));

        // Populate table
        for (String[] parts : matchedUsers) {
            model.addRow(new Object[]{parts[0], parts[3], parts[2], parts[5], parts[4], parts[6]});
        }

    }

    
    
    private void addUser() {
        String newName = formatUsername(usernameTextField.getText().trim());
        String newPhone = phNumberTextField.getText().trim();
        String newEmail = emailTextField.getText().trim();
        String selectedRole = roleDropdown.getSelectedItem().toString();

        // Validate inputs
        if (!validateUserInputs(newName, newPhone, newEmail)) return;

        // Get moduleID if needed
        String moduleID = "NA"; // default
//        if (selectedRole.equals("Student") || selectedRole.equals("Lecturer")) {
//            // If you have a moduleID text field, read it here
//            moduleID = moduleIDTextField != null ? moduleIDTextField.getText().trim() : "NA";
//        }

        // Generate temporary password
        String tempPassword = generateTempPassword();
//        String hashedPassword = UserFunctions.hashPassword(tempPassword);

        // Create user dynamically
        UserManage newUser;
        String userID;
        switch (selectedRole) {
            case "Admin":
                newUser = new Admin("", newName, newEmail, newPhone, moduleID);
                break;
            case "Lecturer":
                newUser = new Lecturer("", newName, newEmail, newPhone, moduleID);
                break;
            case "Student":
                newUser = new Student("", newName, newEmail, newPhone, moduleID);
                break;
            default: // AcademicLeader
                newUser = new AcademicLeader("", newName, newEmail, newPhone, moduleID);
        }
        userID = generateUserID(newUser); // dynamic userID
        String role = newUser.getRole();

        // Append to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String roleNoSpace = role.replaceAll("\\s+", ""); // remove all spaces
            bw.write(String.join(",", userID, tempPassword, roleNoSpace, newName, newEmail, newPhone, moduleID, "true"));
            bw.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding user.");
            return;
        }

        loadUsers();
        JOptionPane.showMessageDialog(this, "User added successfully!\nTemporary Password: " + tempPassword);

        // Clear fields
        usernameTextField.setText("");
        phNumberTextField.setText("");
        emailTextField.setText("");
    }
    
    
    
    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.");
            return;
        }

        String oldUserID = userTable.getValueAt(selectedRow, 0).toString();
        String newName = formatUsername(usernameTextField.getText().trim());
        String newPhone = phNumberTextField.getText().trim();
        String newEmail = emailTextField.getText().trim();
        String newRole = roleDropdown.getSelectedItem().toString();

        if (!validateUserInputs(newName, newPhone, newEmail)) return;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    sb.append(line).append("\n");
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts[0].equals(oldUserID)) {
                    String oldRole = parts[2];
                    parts[1] = parts[1]; // keep password
                    parts[2] = newRole.replaceAll("\\s+", ""); // remove spaces before saving;
                    parts[3] = newName;
                    parts[4] = newEmail;
                    parts[5] = newPhone;

                    // Update userID if role changed
                    if (!oldRole.equals(newRole)) {
                        UserManage tempUser;
                        switch (newRole) {
                            case "Admin": tempUser = new Admin("", newName, newEmail, newPhone, "NA"); break;
                            case "Lecturer": tempUser = new Lecturer("", newName, newEmail, newPhone, "NA"); break;
                            case "Student": tempUser = new Student("", newName, newEmail, newPhone, "NA"); break;
                            default: tempUser = new AcademicLeader("", newName, newEmail, newPhone, "NA");
                        }
                        parts[0] = generateUserID(tempUser);
                        parts[6] = "NA"; // moduleID reset
                    }

                    line = String.join(",", parts);
                }

                sb.append(line).append("\n");
            }
            br.close();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                bw.write(sb.toString());
            }

            loadUsers();
            JOptionPane.showMessageDialog(this, "User updated successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating user.");
        }
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminHomepage = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        AdminProfile = new javax.swing.JButton();
        UserManagement = new javax.swing.JButton();
        cancelRowTable = new javax.swing.JButton();
        AssignLecturer = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        resetPassword = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        GradingSystem = new javax.swing.JButton();
        userIDFilter = new javax.swing.JTextField();
        ClassManagement = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        phNumberTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        roleDropdown = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AdminHomepage.setText("Homepage");
        AdminHomepage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminHomepageActionPerformed(evt);
            }
        });

        jLabel5.setText("Email : ");

        jLabel6.setText("Role : ");

        AdminProfile.setText("Profile");
        AdminProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminProfileActionPerformed(evt);
            }
        });

        UserManagement.setText("Manage User");
        UserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserManagementActionPerformed(evt);
            }
        });

        cancelRowTable.setBackground(new java.awt.Color(204, 204, 204));
        cancelRowTable.setText("Cancel Row");
        cancelRowTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelRowTableActionPerformed(evt);
            }
        });

        AssignLecturer.setText("Assign Lecturer");
        AssignLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignLecturerActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(0, 51, 255));
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");

        resetPassword.setText("Reset Password");
        resetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPasswordActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel7.setText("e.g., Zhang Hao");

        GradingSystem.setText("Grading System");
        GradingSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradingSystemActionPerformed(evt);
            }
        });

        userIDFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userIDFilterMouseClicked(evt);
            }
        });
        userIDFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIDFilterActionPerformed(evt);
            }
        });

        ClassManagement.setText("Class Management");
        ClassManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClassManagementActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(51, 255, 51));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 0, 51));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(51, 255, 51));
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "User Name", "Role", "Phone Number", "Email", "Module ID"
            }
        ));
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("User Management");

        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel8.setText("e.g., 0137589286");

        roleDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Academic Leader", "Lecturer", "Student" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel9.setText("cc@gmail.com / cc@yahoo.com");

        jLabel2.setText("User Name : ");

        jLabel3.setText("Password : ");

        jLabel4.setText("Phone Number : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ClassManagement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UserManagement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AssignLecturer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GradingSystem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AdminProfile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AdminHomepage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roleDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8))
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(usernameTextField)
                                            .addComponent(jLabel4)
                                            .addComponent(phNumberTextField)
                                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(resetPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(userIDFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancelRowTable))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton)
                            .addComponent(userIDFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(editButton)
                                .addComponent(cancelRowTable))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(24, 24, 24))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(roleDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(11, 11, 11)
                                .addComponent(resetPassword)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(phNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel8))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AdminHomepage)
                                .addGap(18, 18, 18)
                                .addComponent(UserManagement)
                                .addGap(18, 18, 18)
                                .addComponent(ClassManagement)
                                .addGap(18, 18, 18)
                                .addComponent(AssignLecturer)
                                .addGap(18, 18, 18)
                                .addComponent(GradingSystem)
                                .addGap(18, 18, 18)
                                .addComponent(AdminProfile)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AdminHomepageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminHomepageActionPerformed
        new AdminHomepage(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminHomepageActionPerformed

    private void AdminProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminProfileActionPerformed
        new AdminProfile(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminProfileActionPerformed

    private void UserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserManagementActionPerformed
        new UserManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_UserManagementActionPerformed

    private void cancelRowTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelRowTableActionPerformed
        // Deselect any selected row in the table
        userTable.clearSelection();

        // Re-enable Add button
        addButton.setEnabled(true);

        // Clear all input fields
        usernameTextField.setText("");
        roleDropdown.setSelectedIndex(0);
        phNumberTextField.setText("");
        emailTextField.setText("");
    }//GEN-LAST:event_cancelRowTableActionPerformed

    private void AssignLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignLecturerActionPerformed
        new AssignLecturer(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AssignLecturerActionPerformed

    private void resetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordActionPerformed
        resetPasswordAction();
    }//GEN-LAST:event_resetPasswordActionPerformed

    private void GradingSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GradingSystemActionPerformed
        new GradingSystem(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_GradingSystemActionPerformed

    private void userIDFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userIDFilterMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_userIDFilterMouseClicked

    private void userIDFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIDFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userIDFilterActionPerformed

    private void ClassManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClassManagementActionPerformed
        new ClassManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ClassManagementActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addUser();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Get the User ID of the selected row
        String userIDToDelete = userTable.getValueAt(selectedRow, 0).toString();

        File originalFile = new File(filePath);
        File tempFile = new File("users_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    // Always keep the header
                    bw.write(line);
                    bw.newLine();
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (!parts[0].equals(userIDToDelete)) {
                    // Write all lines except the one to delete
                    bw.write(line);
                    bw.newLine();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting user.");
            return;
        }


        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(this, "Error updating user file.");
            return;
        }


        // Refresh table
        loadUsers();
        JOptionPane.showMessageDialog(this, "User deleted successfully!");

        // Clear input fields and enable Add button
        userTable.clearSelection();
        addButton.setEnabled(true);
        usernameTextField.setText("");
        roleDropdown.setSelectedIndex(0);
        phNumberTextField.setText("");
        emailTextField.setText("");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        editUser();

    }//GEN-LAST:event_editButtonActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        int row = userTable.rowAtPoint(evt.getPoint());

        if (row == -1) {
            userTable.clearSelection();
        }

    }//GEN-LAST:event_userTableMouseClicked

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed


//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UserManagement().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminHomepage;
    private javax.swing.JButton AdminProfile;
    private javax.swing.JButton AssignLecturer;
    private javax.swing.JButton ClassManagement;
    private javax.swing.JButton GradingSystem;
    private javax.swing.JButton UserManagement;
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelRowTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField phNumberTextField;
    private javax.swing.JButton resetPassword;
    private javax.swing.JComboBox<String> roleDropdown;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField userIDFilter;
    private javax.swing.JTable userTable;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
