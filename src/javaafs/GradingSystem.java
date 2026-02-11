
package javaafs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;


public class GradingSystem extends javax.swing.JFrame {
    
    private final String FILE_NAME = "gradingCriteria.txt";
    private final String username;
    private final UserFunctions uf = new UserFunctions();



    GradingSystem(String username) {
        initComponents();
        this.username = username;
        
        // ===== Set null layout to allow absolute positioning =====
        getContentPane().setLayout(null);
        // ===== Ensure jPanel1 keeps its position =====
        jPanel1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getWidth(), jPanel1.getHeight());
        // ===== Position jDialog1 exactly on top of jPanel1 and hide it =====
        jDialog1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getWidth(), jPanel1.getHeight());
        jDialog1.setVisible(false);
        
        gradingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Grade", "Min Mark Range", "Max Mark Range", "Description"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // disable all cell editing
            }
        });


        loadGradingData();
        
        
        
    }
    
    
//        public GradingSystem() {
//            this.username = null;  // satisfies final, no hardcoding
//            initComponents();
//    //        initButtonActions();
//
//        }
        
        
        // ================== LOAD DATA ==================
        private void loadGradingData() {
            DefaultTableModel model = (DefaultTableModel) gradingTable.getModel();
            model.setRowCount(0);

            ArrayList<String[]> data = uf.readCSV(FILE_NAME);

            // Skip header if exists
            boolean isFirst = true;
            for (String[] row : data) {
                if (isFirst) { isFirst = false; continue; } // skip header
                if (row.length == 4) {
                    model.addRow(row);
                }
            }
        }

    // ================== SAVE TABLE TO FILE ==================
    private void saveTableToFile() {
        DefaultTableModel model = (DefaultTableModel) gradingTable.getModel();
        ArrayList<String[]> data = new ArrayList<>();

        // Optional: include header
        data.add(new String[]{"Grade", "Min Mark Range", "Max Mark Range", "Description"});

        for (int i = 0; i < model.getRowCount(); i++) {
            String grade = model.getValueAt(i, 0).toString();
            String min = model.getValueAt(i, 1).toString();
            String max = model.getValueAt(i, 2).toString();
            String desc = model.getValueAt(i, 3).toString();
            data.add(new String[]{grade, min, max, desc});
        }

        uf.writeCSV(FILE_NAME, data);
    }
    
    
    
    private void deleteFromFile(int selectedRow) {
        uf.deleteRow(FILE_NAME, gradingTable, 0); // first column is unique grade
    }
    
    private int selectedRowForEdit = -1; // class-level variable to remember selected row



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        gradePanel = new javax.swing.JComboBox<>();
        descriptionPanel = new javax.swing.JComboBox<>();
        minMarkPanel = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        maxMarkPanel = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        editButtonPanel = new javax.swing.JButton();
        AdminHomepage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        AdminProfile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        description = new javax.swing.JComboBox<>();
        grade = new javax.swing.JComboBox<>();
        minMark = new javax.swing.JTextField();
        maxMark = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        gradingTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        UserManagement = new javax.swing.JButton();
        AssignLecturer = new javax.swing.JButton();
        GradingSystem = new javax.swing.JButton();
        ClassManagement = new javax.swing.JButton();

        jLabel10.setText("Marking Range :");

        jLabel12.setText("Description :");

        jLabel11.setText("Grade:");

        gradePanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A", "B+", "B", "C+", "C", "D", "E", "F" }));
        gradePanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradePanelActionPerformed(evt);
            }
        });

        descriptionPanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distinction", "Credit", "Pass", "Fail" }));

        minMarkPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minMarkPanelActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel26.setText("-");

        maxMarkPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxMarkPanelActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(255, 51, 51));
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        editButtonPanel.setBackground(new java.awt.Color(51, 102, 255));
        editButtonPanel.setForeground(new java.awt.Color(255, 255, 255));
        editButtonPanel.setText("Edit");
        editButtonPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(gradePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(minMarkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxMarkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gradePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minMarkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxMarkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)))
                .addGap(8, 8, 8)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButtonPanel)
                    .addComponent(cancelButton))
                .addGap(80, 80, 80))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AdminHomepage.setText("Homepage");
        AdminHomepage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminHomepageActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Grading System");

        AdminProfile.setText("Profile");
        AdminProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminProfileActionPerformed(evt);
            }
        });

        description.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distinction", "Credit", "Pass", "Fail" }));
        description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionActionPerformed(evt);
            }
        });

        grade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A", "B+", "B", "C+", "C", "D", "E", "F" }));
        grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeActionPerformed(evt);
            }
        });

        jLabel2.setText("Description : ");

        jLabel3.setText("Grade :");

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        gradingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grade", "Min Mark Range", "Max Mark Range", "Description"
            }
        ));
        jScrollPane1.setViewportView(gradingTable);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("-");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel9.setText("Mark Rnage must be 0-100");

        jLabel5.setText("Marking Range : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(minMark, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(maxMark, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(maxMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(minMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addGap(33, 33, 33))
        );

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
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(AdminProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GradingSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssignLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClassManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AdminHomepage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
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
                        .addComponent(AdminProfile))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gradePanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradePanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gradePanelActionPerformed

    private void minMarkPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minMarkPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minMarkPanelActionPerformed

    private void maxMarkPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxMarkPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxMarkPanelActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // Reset the fields
        gradePanel.setSelectedIndex(0);
        descriptionPanel.setSelectedIndex(0);
        minMarkPanel.setText("");
        maxMarkPanel.setText("");

        selectedRowForEdit = -1;

        // Close the dialog
        SwingUtilities.getWindowAncestor(jDialog1).dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void editButtonPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonPanelActionPerformed
        if (selectedRowForEdit == -1) {
            JOptionPane.showMessageDialog(this, "No row selected for editing.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) gradingTable.getModel();

        // Get updated values from the dialog fields
        String updatedGrade = gradePanel.getSelectedItem().toString();
        String updatedDescription = descriptionPanel.getSelectedItem().toString();
        String updatedMin = minMarkPanel.getText().trim();
        String updatedMax = maxMarkPanel.getText().trim();

        // ===== Validation: Empty =====
        if (updatedMin.isEmpty() || updatedMax.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both min and max marks.");
            return;
        }

        int minValue, maxValue;

        // ===== Validation: Numeric =====
        try {
            minValue = Integer.parseInt(updatedMin);
            maxValue = Integer.parseInt(updatedMax);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Marks must be numbers.");
            return;
        }

        // ===== Validation: 0-100 range =====
        if (minValue < 0 || minValue > 100 || maxValue < 0 || maxValue > 100) {
            JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.");
            return;
        }

        // ===== Validation: min ≤ max =====
        if (minValue > maxValue) {
            JOptionPane.showMessageDialog(this, "Min mark cannot be greater than Max mark.");
            return;
        }

        // ===== Validation: min difference 10, max difference 35 =====
        int diff = maxValue - minValue;
        if (diff < 10 || diff > 35) {
            JOptionPane.showMessageDialog(this, "The mark range must be between 10 and 35.");
            return;
        }

        // ===== Validation: Grade uniqueness (excluding current row) =====
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i != selectedRowForEdit && model.getValueAt(i, 0).toString().equals(updatedGrade)) {
                JOptionPane.showMessageDialog(this, "This grade already exists. Each grade can only appear once.");
                return;
            }
        }

        // ===== Validation: Description limit (max 3, excluding current row) =====
        int descriptionCount = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i != selectedRowForEdit && model.getValueAt(i, 3).toString().equals(updatedDescription)) {
                descriptionCount++;
            }
        }
        if (descriptionCount >= 3) {
            JOptionPane.showMessageDialog(this, "This description already exists 3 times. Maximum allowed is 3.");
            return;
        }

        // ===== Validation: Mark range clash (excluding current row) =====
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == selectedRowForEdit) continue;

            int existingMin = Integer.parseInt(model.getValueAt(i, 1).toString());
            int existingMax = Integer.parseInt(model.getValueAt(i, 2).toString());

            // Check for overlap
            if (!(maxValue < existingMin || minValue > existingMax)) {
                JOptionPane.showMessageDialog(this, "This mark range clashes with existing range: "
                    + model.getValueAt(i, 0) + " " + existingMin + "-" + existingMax);
                return;
            }
        }

        // ===== Update JTable =====
        model.setValueAt(updatedGrade, selectedRowForEdit, 0);
        model.setValueAt(minValue, selectedRowForEdit, 1);
        model.setValueAt(maxValue, selectedRowForEdit, 2);
        model.setValueAt(updatedDescription, selectedRowForEdit, 3);

        // ===== Save entire table to file =====
        saveTableToFile(); // reuse your existing method

        // Close dialog
        jDialog1.setVisible(false);
        selectedRowForEdit = -1;

        JOptionPane.showMessageDialog(this, "Row updated successfully!");

    }//GEN-LAST:event_editButtonPanelActionPerformed

    private void AdminHomepageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminHomepageActionPerformed
        new AdminHomepage(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminHomepageActionPerformed

    private void AdminProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminProfileActionPerformed
        new AdminProfile(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminProfileActionPerformed

    private void descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionActionPerformed

    private void gradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gradeActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed

        selectedRowForEdit = gradingTable.getSelectedRow();

        if (selectedRowForEdit == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) gradingTable.getModel();

        // Populate panel fields with selected row data
        gradePanel.setSelectedItem(model.getValueAt(selectedRowForEdit, 0).toString());
        minMarkPanel.setText(model.getValueAt(selectedRowForEdit, 1).toString());
        maxMarkPanel.setText(model.getValueAt(selectedRowForEdit, 2).toString());
        descriptionPanel.setSelectedItem(model.getValueAt(selectedRowForEdit, 3).toString());

        //        showEditPanel();

        // Show the existing dialog
        jDialog1.pack(); // optional, adjust size to fit components
        jDialog1.setLocationRelativeTo(this); // center on main frame
        jDialog1.setVisible(true); // show modal

        //        showEditPanel(jPanel1.getX() + 50, jPanel1.getY() + 50);
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        int selectedRow = gradingTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        // Pass the values BEFORE removing the row
        deleteFromFile(selectedRow);

        // Now remove row from table
        DefaultTableModel model = (DefaultTableModel) gradingTable.getModel();
        model.removeRow(selectedRow);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) gradingTable.getModel();

        String selectedGrade = grade.getSelectedItem().toString();
        String selectedDescription = description.getSelectedItem().toString();
        String min = minMark.getText().trim();
        String max = maxMark.getText().trim();

        // ===== Validation: Empty =====
        if (min.isEmpty() || max.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both min and max marks.");
            return;
        }

        int minMarkValue, maxMarkValue;

        // ===== Validation: Numeric =====
        try {
            minMarkValue = Integer.parseInt(min);
            maxMarkValue = Integer.parseInt(max);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Marks must be numbers.");
            return;
        }

        // ===== Validation: 0-100 range =====
        if (minMarkValue < 0 || minMarkValue > 100 || maxMarkValue < 0 || maxMarkValue > 100) {
            JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.");
            return;
        }

        // ===== Validation: min ≤ max =====
        if (minMarkValue > maxMarkValue) {
            JOptionPane.showMessageDialog(this, "Min mark cannot be greater than Max mark.");
            return;
        }

        // ===== Validation: min difference 10, max difference 35 =====
        int diff = maxMarkValue - minMarkValue;
        if (diff < 10 || diff > 35) {
            JOptionPane.showMessageDialog(this, "The mark range must be between 10 and 35.");
            return;
        }

        // ===== Validation: Grade uniqueness =====
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingGrade = model.getValueAt(i, 0).toString();
            if (existingGrade.equals(selectedGrade)) {
                JOptionPane.showMessageDialog(this, "This grade already exists. Each grade can only appear once.");
                return;
            }
        }

        // ===== Validation: Description limit (max 3) =====
        int descriptionCount = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingDescription = model.getValueAt(i, 3).toString();
            if (existingDescription.equals(selectedDescription)) {
                descriptionCount++;
            }
        }
        if (descriptionCount >= 3) {
            JOptionPane.showMessageDialog(this, "This description already exists 3 times. Maximum allowed is 3.");
            return;
        }

        // ===== Validation: Mark range clash =====
        for (int i = 0; i < model.getRowCount(); i++) {
            int existingMin = Integer.parseInt(model.getValueAt(i, 1).toString());
            int existingMax = Integer.parseInt(model.getValueAt(i, 2).toString());

            // Check for overlap
            if (!(maxMarkValue < existingMin || minMarkValue > existingMax)) {
                JOptionPane.showMessageDialog(this, "This mark range clashes with existing range: "
                    + model.getValueAt(i, 0) + " " + existingMin + "-" + existingMax);
                return;
            }
        }

        // ===== Add to JTable =====
        model.addRow(new Object[]{selectedGrade, minMarkValue, maxMarkValue, selectedDescription});

        // ===== Save to file =====
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(selectedGrade + "," + minMarkValue + "," + maxMarkValue + "," + selectedDescription);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file.");
        }

        // Clear input
        minMark.setText("");
        maxMark.setText("");
    }//GEN-LAST:event_addButtonActionPerformed

    private void UserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserManagementActionPerformed
        new UserManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_UserManagementActionPerformed

    private void AssignLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignLecturerActionPerformed
        new AssignLecturer(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AssignLecturerActionPerformed

    private void ClassManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClassManagementActionPerformed
        new ClassManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ClassManagementActionPerformed


//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GradingSystem().setVisible(true);
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
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> description;
    private javax.swing.JComboBox<String> descriptionPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editButtonPanel;
    private javax.swing.JComboBox<String> grade;
    private javax.swing.JComboBox<String> gradePanel;
    private javax.swing.JTable gradingTable;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maxMark;
    private javax.swing.JTextField maxMarkPanel;
    private javax.swing.JTextField minMark;
    private javax.swing.JTextField minMarkPanel;
    // End of variables declaration//GEN-END:variables
}
