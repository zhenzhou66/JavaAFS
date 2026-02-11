
package javaafs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AdminHomepage extends javax.swing.JFrame {
    
    private String username;
    private String forceChange;
    private UserFunctions func = new UserFunctions();
    
    
    
    
    public AdminHomepage(String username) {
        this.username = username;
        initComponents();
        userID.setText(username);

//        this.setVisible(true);

    // Read users to check if forceChange is true
        List<String[]> users = UserFunctions.readCSV("users.txt");
        for (String[] row : users) {
            if (row[0].equals(username)) {
                forceChange = row[7]; // store "true" or "false"
                break;
            }
        }

        // If forceChange = true, show reminder dialog first
        if ("true".equalsIgnoreCase(forceChange)) {
            int result = JOptionPane.showConfirmDialog(
                null, // no parent yet, so dialog appears centered
                "You must change your password.",
                "Notice",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                // Open profile page only
                new AdminProfile(username).setVisible(true);
                return; // exit constructor, homepage never shows
            }
            // If user closes dialog → continue to show homepage
        }

        // Show homepage normally
        this.setVisible(true);
        
        

//        ArrayList<String[]> users = UserFunctions.readCSV("users.txt");
//
//            if (UserFunctions.isForceChangeRequired(username, users)) {
//
//                JOptionPane.showMessageDialog(
//                    this,
//                    "You must change your password.",
//                    "Notice",
//                    JOptionPane.WARNING_MESSAGE
//                );
//
//                new AdminProfile(username).setVisible(true);
//                this.dispose();
//            }
    }



//    public AdminHomepage(String username) {
//        this.username = username;
//        initComponents();
//        initButtonActions();
//        
//        // display username in the text field
////        if (username != null) {
////            userID.setText(username);
////        }
//    }
//    
//    public AdminHomepage() {
//        this.username = null;  // satisfies final, no hardcoding
//        initComponents();
//        initButtonActions();
//
//    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        AdminProfile = new javax.swing.JButton();
        userID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        UserManagement = new javax.swing.JButton();
        AssignLecturer = new javax.swing.JButton();
        GradingSystem = new javax.swing.JButton();
        ClassManagement = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        AdminProfile.setText("Profile");
        AdminProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminProfileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Admin Homepage");

        UserManagement.setText("Manage User");
        UserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserManagementActionPerformed(evt);
            }
        });

        AssignLecturer.setText("Assign Lecturer");
        AssignLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignLecturerActionPerformed(evt);
            }
        });

        GradingSystem.setText("Grading System");
        GradingSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradingSystemActionPerformed(evt);
            }
        });

        ClassManagement.setText("Class Management");
        ClassManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClassManagementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(userID, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoutButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ClassManagement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UserManagement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AssignLecturer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GradingSystem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AdminProfile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(431, 431, 431))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserManagement))
                .addGap(18, 18, 18)
                .addComponent(ClassManagement)
                .addGap(18, 18, 18)
                .addComponent(AssignLecturer)
                .addGap(18, 18, 18)
                .addComponent(GradingSystem)
                .addGap(18, 18, 18)
                .addComponent(AdminProfile)
                .addGap(18, 18, 18)
                .addComponent(logoutButton)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed

        if ("true".equals(forceChange)) {

            JOptionPane optionPane =
            new JOptionPane(
                "You must change your password before logging out.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

            JDialog dialog = optionPane.createDialog("Notice");
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialog.setVisible(true);

            new AdminProfile(username).setVisible(true);
            this.dispose();
            return;
        }

        // Normal logout
        new UserLogin().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_logoutButtonActionPerformed

    private void AdminProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminProfileActionPerformed
        new AdminProfile(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminProfileActionPerformed

    private void UserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserManagementActionPerformed
        new UserManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_UserManagementActionPerformed

    private void AssignLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignLecturerActionPerformed
        new AssignLecturer(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AssignLecturerActionPerformed

    private void GradingSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GradingSystemActionPerformed
        new GradingSystem(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_GradingSystemActionPerformed

    private void ClassManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClassManagementActionPerformed
        new ClassManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ClassManagementActionPerformed


//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(() -> new AdminHomepage().setVisible(true));
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminProfile;
    private javax.swing.JButton AssignLecturer;
    private javax.swing.JButton ClassManagement;
    private javax.swing.JButton GradingSystem;
    private javax.swing.JButton UserManagement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField userID;
    // End of variables declaration//GEN-END:variables
}
