package javaafs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;



public class AssignLecturer extends javax.swing.JFrame {


    public AssignLecturer() {
        initComponents();
        loadUserData();
    }
    
    private void goBack() {
    new AdminHomepage().setVisible(true);
    this.dispose();
}

    
    private void loadUserData() {

        DefaultTableModel academicModel =
                (DefaultTableModel) AcademicLeaderTable.getModel();

        DefaultTableModel lecturerModel =
                (DefaultTableModel) LecturerTable.getModel();

        academicModel.setRowCount(0);
        lecturerModel.setRowCount(0);

        
        Map<String, String> lecturerToLeaderMap = new HashMap<>();
        ArrayList<String[]> relationships =
                Functions.readCSV("leaderLecturerRelationship.txt");

        for (String[] row : relationships) {
            if (row.length >= 3) {
                String leaderID = row[0];
                String lecturerID = row[2];

                if (!leaderID.isEmpty() && !lecturerID.isEmpty()) {
                    lecturerToLeaderMap.put(lecturerID, leaderID);
                }
            }
        }
        
        ArrayList<String[]> users = Functions.readCSV("users.txt");

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
                Functions.readCSV("leaderLecturerRelationship.txt");

        boolean samePairExists = false;
        boolean lecturerAssignedElsewhere = false;
        String previousLeaderID = "";

        for (String[] row : assignments) {
            if (row.length < 4) continue;

            String existingLeaderID = row[0];
            String existingLecturerID = row[2];

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
                if (row[2].equals(lecturerID)) {
                    row[0] = leaderID;
                    row[1] = leaderName;
                    row[2] = lecturerID;
                    row[3] = lecturerName;
                    break;
                }
            }
        } 
        else {
            assignments.add(new String[]{
                leaderID,
                leaderName,
                lecturerID,
                lecturerName,
                ""
            });
        }

        Functions.writeCSV("leaderLecturerRelationship.txt", assignments);

        JOptionPane.showMessageDialog(this,
                "Assignment saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        loadUserData();
        AcademicLeaderTable.clearSelection();
        LecturerTable.clearSelection();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        LecturerTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        AcademicLeaderTable = new javax.swing.JTable();
        subHeading1 = new javax.swing.JLabel();
        assignLecturerButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        subHeading2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        subHeading1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading1.setText("Academic Leader Table");

        assignLecturerButton.setText("Assign Lecturer");
        assignLecturerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignLecturerButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        subHeading2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading2.setText("Lecturer Table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(subHeading2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(subHeading1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assignLecturerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(subHeading2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subHeading1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(assignLecturerButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void assignLecturerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignLecturerButtonActionPerformed
        assignLecturer();
    }//GEN-LAST:event_assignLecturerButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new AdminHomepage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignLecturer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AcademicLeaderTable;
    private javax.swing.JTable LecturerTable;
    private javax.swing.JButton assignLecturerButton;
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel subHeading1;
    private javax.swing.JLabel subHeading2;
    // End of variables declaration//GEN-END:variables
}
