package javaafs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;



public class AssignLecturer extends javax.swing.JFrame {
    
    private final String username;
    private UserFunctions func = new UserFunctions();



    public AssignLecturer(String userID) {
        this.username = userID;
        initComponents();
        loadLecturer();
    }
    

    
    private void loadLecturer() {

        DefaultTableModel academicModel =
                (DefaultTableModel) AcademicLeaderTable.getModel();

        DefaultTableModel lecturerModel =
                (DefaultTableModel) LecturerTable.getModel();

        academicModel.setRowCount(0);
        lecturerModel.setRowCount(0);

        
        Map<String, String> lecturerToLeaderMap = new HashMap<>();
        ArrayList<String[]> relationships =
                UserFunctions.readCSV("leaderLecturerRelationship.txt");

        for (String[] row : relationships) {
            if (row.length >= 2) {
                String leaderID = row[0];
                String lecturerID = row[1];

                if (!leaderID.isEmpty() && !lecturerID.isEmpty()) {
                    lecturerToLeaderMap.put(lecturerID, leaderID);
                }
            }
        }
        
        ArrayList<String[]> users = UserFunctions.readCSV("users.txt");

            for (String[] data : users) {
                if (data.length < 5) continue;

                String userID = data[0];
                String role = data[2];
                String name = data[3];
                String email = data[4];

                if (role.equalsIgnoreCase("AcademicLeader")) {
                    academicModel.addRow(new Object[]{userID, name, email});
                } 
                else if (role.equalsIgnoreCase("Lecturer")) {
                    String leaderID = lecturerToLeaderMap.get(userID);
                    String status = (leaderID != null) ? "Assigned" : "Not Assigned";

                    lecturerModel.addRow(new Object[]{
                        userID,
                        name,
                        email,
                        status,
                        (leaderID == null ? "" : leaderID)
                    });
                }
            }
        }

    
        private void assignLecturer() {

        int selectedLeaderRow = AcademicLeaderTable.getSelectedRow();
        int selectedLecturerRow = LecturerTable.getSelectedRow();

        if (selectedLeaderRow == -1 || selectedLecturerRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select both an Academic Leader and a Lecturer.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String leaderID = AcademicLeaderTable
                .getValueAt(selectedLeaderRow, 0).toString();
        String leaderName = AcademicLeaderTable
                .getValueAt(selectedLeaderRow, 1).toString();
        String lecturerID = LecturerTable
                .getValueAt(selectedLecturerRow, 0).toString();
        String lecturerName = LecturerTable
                .getValueAt(selectedLecturerRow, 1).toString();

        ArrayList<String[]> assignments =
                UserFunctions.readCSV("leaderLecturerRelationship.txt");

        boolean samePairExists = false;
        boolean lecturerAssignedElsewhere = false;
        String previousLeaderID = "";

        for (String[] row : assignments) {
            if (row.length < 2) continue;

            String existingLeaderID = row[0];
            String existingLecturerID = row[1];

            if (existingLeaderID.equals(leaderID)
                    && existingLecturerID.equals(lecturerID)) {
                samePairExists = true;
            }

            if (!existingLeaderID.equals(leaderID)
                    && existingLecturerID.equals(lecturerID)) {
                lecturerAssignedElsewhere = true;
                previousLeaderID = existingLeaderID;
            }
        }

        if (samePairExists) {
            JOptionPane.showMessageDialog(this,
                    "This Lecturer is already assigned to this Academic Leader.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (lecturerAssignedElsewhere) {
            int option = JOptionPane.showConfirmDialog(this,
                    "This Lecturer is already assigned to another Academic Leader (ID: "
                    + previousLeaderID + "). Do you want to reassign to the new Academic Leader?",
                    "Confirm Reassign",
                    JOptionPane.YES_NO_OPTION);

            if (option != JOptionPane.YES_OPTION) {
                AcademicLeaderTable.clearSelection();
                LecturerTable.clearSelection();
                return;
            }

            for (String[] row : assignments) {
                if (row[1].equals(lecturerID)) {
                    row[0] = leaderID;
//                    row[1] = lecturerID;
                    break;
                }
            }
        } 
        else {
            assignments.add(new String[]{
                leaderID,
//                leaderName,
                lecturerID,
//                lecturerName,
                ""
            });
        }

        UserFunctions.writeCSV("leaderLecturerRelationship.txt", assignments);

        JOptionPane.showMessageDialog(this,
                "Assignment saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        loadLecturer();
        AcademicLeaderTable.clearSelection();
        LecturerTable.clearSelection();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminHomepage = new javax.swing.JButton();
        AdminProfile = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        LecturerTable = new javax.swing.JTable();
        UserManagement = new javax.swing.JButton();
        AssignLecturer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AcademicLeaderTable = new javax.swing.JTable();
        GradingSystem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ClassManagement = new javax.swing.JButton();
        subHeading1 = new javax.swing.JLabel();
        assignLecturerButton = new javax.swing.JButton();
        subHeading2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AdminHomepage.setText("Homepage");
        AdminHomepage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminHomepageActionPerformed(evt);
            }
        });

        AdminProfile.setText("Profile");
        AdminProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminProfileActionPerformed(evt);
            }
        });

        LecturerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Lecturer ID", "Name", "Email", "Status", "Academic Leader ID"
            }
        ));
        jScrollPane3.setViewportView(LecturerTable);

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

        AcademicLeaderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Academic Leader ID", "Name", "Email"
            }
        ));
        jScrollPane1.setViewportView(AcademicLeaderTable);

        GradingSystem.setText("Grading System");
        GradingSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradingSystemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Assign Lecturer");

        ClassManagement.setText("Class Management");
        ClassManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClassManagementActionPerformed(evt);
            }
        });

        subHeading1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading1.setText("Academic Leader Table");

        assignLecturerButton.setText("Assign Lecturer");
        assignLecturerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignLecturerButtonActionPerformed(evt);
            }
        });

        subHeading2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading2.setText("Lecturer Table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AdminProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GradingSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssignLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClassManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AdminHomepage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subHeading2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(subHeading1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(assignLecturerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)))
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subHeading1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subHeading2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
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
                .addGap(18, 18, 18)
                .addComponent(assignLecturerButton)
                .addGap(39, 39, 39))
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

    private void assignLecturerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignLecturerButtonActionPerformed
        assignLecturer();
    }//GEN-LAST:event_assignLecturerButtonActionPerformed


//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
////                new AssignLecturer().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AcademicLeaderTable;
    private javax.swing.JButton AdminHomepage;
    private javax.swing.JButton AdminProfile;
    private javax.swing.JButton AssignLecturer;
    private javax.swing.JButton ClassManagement;
    private javax.swing.JButton GradingSystem;
    private javax.swing.JTable LecturerTable;
    private javax.swing.JButton UserManagement;
    private javax.swing.JButton assignLecturerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel subHeading1;
    private javax.swing.JLabel subHeading2;
    // End of variables declaration//GEN-END:variables
}
