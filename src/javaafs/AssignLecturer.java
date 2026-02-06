package javaafs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class AssignLecturer extends javax.swing.JFrame {


    public AssignLecturer() {
        initComponents();
        loadUserData();
        assignLecturerButton.addActionListener(e -> assignLecturer());
        backButton.addActionListener(e -> goBack()); 
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


        List<String> assignedLecturerIDs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("leaderLecturerRelationship.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                assignedLecturerIDs.add(parts[2].trim()); // lecturerID
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading leaderLecturerRelationship.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue; // Skip invalid lines

                String userID = data[0].trim();
                String role = data[2].trim();
                String name = data[3].trim();
                String email = data[4].trim();

                if (role.equalsIgnoreCase("AcademicLeader")) {
                    academicModel.addRow(new Object[]{userID, name, email});
                } else if (role.equalsIgnoreCase("Lecturer")) {
                    String status = assignedLecturerIDs.contains(userID) ? "Assigned" : "Not Assigned";
                    lecturerModel.addRow(new Object[]{userID, name, email, status});

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading users.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    
    
    
    private void assignLecturer() {
        int selectedLeaderRow = AcademicLeaderTable.getSelectedRow();
        int selectedLecturerRow = LecturerTable.getSelectedRow();

        if (selectedLeaderRow == -1 || selectedLecturerRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select both an Academic Leader and a Lecturer.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get selected data
        String leaderID = AcademicLeaderTable.getValueAt(selectedLeaderRow, 0).toString();
        String leaderName = AcademicLeaderTable.getValueAt(selectedLeaderRow, 1).toString();
        String lecturerID = LecturerTable.getValueAt(selectedLecturerRow, 0).toString();
        String lecturerName = LecturerTable.getValueAt(selectedLecturerRow, 1).toString();

        // Load existing assignments
        List<String[]> assignments = new ArrayList<>();
        boolean samePairExists = false;
        boolean lecturerAssignedElsewhere = false;
        String previousLeaderID = "";

        try (BufferedReader br = new BufferedReader(new FileReader("leaderLecturerRelationship.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String existingLeaderID = parts[0].trim();
                String existingLecturerID = parts[2].trim();

                // Check if same pair exists
                if (existingLeaderID.equals(leaderID) && existingLecturerID.equals(lecturerID)) {
                    samePairExists = true;
                }

                // Check if lecturer is assigned to another leader
                if (!existingLeaderID.equals(leaderID) && existingLecturerID.equals(lecturerID)) {
                    lecturerAssignedElsewhere = true;
                    previousLeaderID = existingLeaderID;
                }

                assignments.add(parts);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading leaderLecturerRelationship.txt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Handle same pair exists
        if (samePairExists) {
            JOptionPane.showMessageDialog(this, "This Lecturer is already assigned to this Academic Leader.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Handle lecturer assigned to another leader
        if (lecturerAssignedElsewhere) {
            int option = JOptionPane.showConfirmDialog(this,
                    "This Lecturer is already assigned to another Academic Leader (ID: " + previousLeaderID + "). Do you want to reassign to the new Academic Leader?",
                    "Confirm Reassign", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                return;
            } else {
                // Overwrite the previous assignment
                for (String[] row : assignments) {
                    if (row[2].equals(lecturerID)) {
                        row[0] = leaderID;
                        row[1] = leaderName;
                        row[2] = lecturerID;
                        row[3] = lecturerName;
                    }
                }
            }
        } else {
            // Add new assignment
            assignments.add(new String[]{leaderID, leaderName, lecturerID, lecturerName, ""}); // empty moduleID ignored
        }

        // Save back to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("leaderLecturerRelationship.txt"))) {
            for (String[] row : assignments) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Assignment saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            loadUserData();

            AcademicLeaderTable.clearSelection();
            LecturerTable.clearSelection();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving assignments.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subHeading2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        LecturerTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        AcademicLeaderTable = new javax.swing.JTable();
        subHeading1 = new javax.swing.JLabel();
        assignLecturerButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        subHeading2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading2.setText("Lecturer Table");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(subHeading1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(91, 226, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(subHeading2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_assignLecturerButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
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
