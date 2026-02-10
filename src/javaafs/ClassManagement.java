
package javaafs;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ClassManagement extends javax.swing.JFrame {
    
    private String userID; 
    
    private Map<String, String> moduleMap = new HashMap<>();
    
    private UserFunctions fn = new UserFunctions();




    public ClassManagement(String userID) {
        this.userID = userID;
        initComponents();
        loadModules();
        updateHistoryTable();
        
         // Row selection only
        historyTable.setRowSelectionAllowed(true);
        historyTable.setColumnSelectionAllowed(false);
        historyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // Enable Delete button only if a row is selected
        deleteButton.setEnabled(historyTable.getSelectedRow() != -1);

        // Listen for row selection changes
        historyTable.getSelectionModel().addListSelectionListener(e -> {
            deleteButton.setEnabled(historyTable.getSelectedRow() != -1);
        });
        
        
        // Deselect row when clicking empty space
        historyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = historyTable.rowAtPoint(evt.getPoint());
                if (row == -1) {  // clicked outside any row
                    historyTable.clearSelection();
                }
            }
        });

    }
    


    // ================= LOAD MODULES =================
    private final void loadModules() {
        moduleName.removeAllItems();
        moduleMap.clear();
        
//        // Placeholder
//        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
//        model.addElement("---Select---"); // first item
//        moduleName.setModel(model);
    
//        try (BufferedReader br = new BufferedReader(new FileReader("modules.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length >= 2) {
//                    String moduleID = data[0].trim();
//                    String moduleNameText = data[1].trim();
//
//                    moduleName.addItem(moduleNameText);      // UI
//                    moduleMap.put(moduleNameText, moduleID); // INTERNAL
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error loading modules: " + e.getMessage());
//        }

        ArrayList<String[]> modules = UserFunctions.readCSV("modules.txt");

        for (int i = 1; i < modules.size(); i++) { // start from 1
            String[] data = modules.get(i);
            if (data.length >= 2) {
                String moduleID = data[0].trim();
                String moduleNameText = data[1].trim();

                moduleName.addItem(moduleNameText);      // add to combo box
                moduleMap.put(moduleNameText, moduleID); // keep internal mapping
            }
        }

        
        
    }
    
    

    // ================= TIME VALIDATION =================
    private int timeToMinutes(String time) {
        try {
            String[] parts = time.split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        } catch (Exception e) {
            return -1; // invalid time
        }
    }

    private boolean isValidDuration(String start, String end) {
        int startMinutes = timeToMinutes(start);
        int endMinutes = timeToMinutes(end);

        if (startMinutes < 0 || endMinutes < 0) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Use HH:mm.");
            return false;
        }

        int duration = endMinutes - startMinutes;

        if (duration <= 0) {
            JOptionPane.showMessageDialog(this, "End time must be after start time.");
            return false;
        }

        if (duration < 60 || duration > 180) {
            JOptionPane.showMessageDialog(this, "Class duration must be between 1 and 3 hours.");
            return false;
        }

        return true;
    }
    
    
    private boolean isTimeOverlap(String s1, String e1, String s2, String e2) {
        return timeToMinutes(s1) < timeToMinutes(e2)
            && timeToMinutes(s2) < timeToMinutes(e1);
    }
    
    
    
    private boolean hasModuleTimeClash(String moduleID, String newStart, String newEnd) {
        ArrayList<String[]> classes = UserFunctions.readCSV("classes.txt");

        for (String[] data : classes) {

            if (data.length < 5) continue;

            String existingStart = data[1];
            String existingEnd = data[2];
            String existingModuleID = data[3];

            if (existingModuleID.equals(moduleID)) {
                if (isTimeOverlap(existingStart, existingEnd, newStart, newEnd)) {
                    return true;
                }
            }
        }

        return false;
    }

    
       // ================= GROUP VALIDATION =================
//        private boolean isGroupDuplicate(String groupName, String moduleID) {
//            try (BufferedReader br = new BufferedReader(new FileReader("group.txt"))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] d = line.split(",");
//                    if (d.length >= 3 &&
//                        d[1].equalsIgnoreCase(groupName) &&
//                        d[2].equals(moduleID)) {
//                        return true;
//                    }
//                }
//            } catch (Exception ignored) {}
//            return false;
//        }


    // ================= ID GENERATOR =================
//    private String generateID(String prefix, String fileName) {
//        int maxID = 0;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//
//                // skip empty or header lines
//                if (line.trim().isEmpty() || !line.startsWith(prefix)) {
//                    continue;
//                }
//
//                String[] data = line.split(",");
//                String idStr = data[0].replace(prefix, "");
//                int idNum = Integer.parseInt(idStr);
//
//                if (idNum > maxID) {
//                    maxID = idNum;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            // file doesn't exist yet → first ID
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "ID generation error: " + e.getMessage());
//        }
//
//        return prefix + String.format("%03d", maxID + 1);
//    }


    

    
    private boolean isGroupNameDuplicateInModule(String groupNameText, String moduleID) {
//        try (BufferedReader br = new BufferedReader(new FileReader("group.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length >= 3 &&
//                    data[1].equalsIgnoreCase(groupNameText.trim()) &&
//                    data[2].equals(moduleID)) {  // <-- compares moduleID
//                    return true;                 // duplicate found in same module
//                }
//            }
//        } catch (Exception ignored) {}
//        return false;  // not found → valid

        ArrayList<String[]> groups = UserFunctions.readCSV("group.txt");

        for (String[] data : groups) {
            if (data.length >= 3 &&
                data[1].equalsIgnoreCase(groupNameText.trim()) &&
                data[2].equals(moduleID)) {
                return true;
            }
        }
        return false;

    }

    
    
    // ================= SAVE GROUP =================
    private String saveGroup(String groupNameText, String moduleID) {
        if (isGroupNameDuplicateInModule(groupNameText, moduleID)) {
            JOptionPane.showMessageDialog(this,
                "This group name already exists for the selected module.\n" +
                "Please choose a different group name.");
            return null;
        }

        // Always generate a NEW group ID
        String groupID = fn.generateNextID("group.txt", "G");

        try {
            ArrayList<String[]> groups = UserFunctions.readCSV("group.txt");

            groups.add(new String[]{
                groupID,
                groupNameText.trim(),
                moduleID
            });

            UserFunctions.writeCSV("group.txt", groups);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving group: " + e.getMessage());
            return null;
        }

        return groupID;
    }    
    
    
    
    // ================= HISTORY TABLE =================
    private final void updateHistoryTable() {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.setRowCount(0); // clear existing rows

        try (BufferedReader br = new BufferedReader(new FileReader("classes.txt"))) {
            String line;
            boolean firstLine = true; // skip header if present
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; 
                    // optionally check if this is a header line like "classID,classStartTime,..."
                    if (line.toLowerCase().contains("classid")) continue; 
                }

                String[] data = line.split(",");
                if (data.length == 5) {
                    model.addRow(new Object[]{
                        data[3], // Module ID
                        data[4], // Group ID
                        data[0], // Class ID
                        data[1], // Start Time
                        data[2]  // End Time
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading classes: " + e.getMessage());
        }
    }


//    private void deleteLineFromFile(String fileName, String idToDelete, int columnIndex) {       
//        File inputFile = new File(fileName);
//        File tempFile = new File("temp_" + fileName);
//
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
//             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length > columnIndex && parts[columnIndex].equals(idToDelete)) {
//                    continue; // skip this line (delete)
//                }
//                bw.write(line);
//                bw.newLine();
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
//            return;
//        }
//
//        // Replace original file
//        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
//            JOptionPane.showMessageDialog(this, "Error updating file after deletion.");
//        }

    



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        groupNameFormat = new javax.swing.JLabel();
        moduleName = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        groupLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        moduleLabel = new javax.swing.JLabel();
        startTime = new javax.swing.JComboBox<>();
        historyLabel = new javax.swing.JLabel();
        endTime = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        groupName = new javax.swing.JTextField();
        startTimeLabel = new javax.swing.JLabel();
        endTimeLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        groupNameFormat.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        groupNameFormat.setText("Format: Group A, Group B,....");

        moduleName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moduleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Class Management");

        groupLabel.setText("Group : ");

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Group ID", "Class ID", "Start Time", "End Time"
            }
        ));
        jScrollPane2.setViewportView(historyTable);

        moduleLabel.setText("Module : ");

        startTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00", "08:15", "08:30", "09:00", "09:15", "09:30", "10:00", "10:15", "10:30", "11:00", "11:15", "11:30", "12:00", "12:15", "12:30", "13:00", "13:15", "13:30", "14:00", "14:15", "14:30", "15:00", "15:15", "15:30", "16:00", "16:15", "16:30", "17:00", "17:15", "17:30", "18:00" }));

        historyLabel.setText("Class Creation History");

        endTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00", "08:15", "08:30", "09:00", "09:15", "09:30", "10:00", "10:15", "10:30", "11:00", "11:15", "11:30", "12:00", "12:15", "12:30", "13:00", "13:15", "13:30", "14:00", "14:15", "14:30", "15:00", "15:15", "15:30", "16:00", "16:15", "16:30", "17:00", "17:15", "17:30", "18:00" }));

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        startTimeLabel.setText("Start Time :");

        endTimeLabel.setText("End Time :");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(moduleLabel)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(groupName)
                                    .addComponent(moduleName, 0, 116, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(groupLabel)
                                .addGap(34, 34, 34)
                                .addComponent(groupNameFormat))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(startTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(historyLabel)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moduleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(groupLabel)
                            .addComponent(groupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groupNameFormat)
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startTimeLabel)
                            .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(endTimeLabel)
                            .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(backButton))
                .addGap(67, 67, 67))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new AdminHomepage(userID).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void moduleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleNameActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = historyTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a class from the table to delete.");
            return;
        }

        // Get class ID from the selected row (column 2)
        String classID = (String) historyTable.getValueAt(selectedRow, 2);
        String groupID = historyTable.getValueAt(selectedRow, 1).toString();

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this class?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        // Delete class from classes.txt
        fn.deleteRow("classes.txt", historyTable, 2);
        fn.deleteRow("group.txt", historyTable, 1);

        // Optional: delete corresponding group from group.txt (if you want to remove the group as well)
        // deleteLineFromFile("group.txt", groupID, 0);

        // Refresh table
        updateHistoryTable();

        historyTable.clearSelection();
        deleteButton.setEnabled(false);

        JOptionPane.showMessageDialog(this, "Class deleted successfully.");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String selectedModuleName = (String) moduleName.getSelectedItem();
        
        if (selectedModuleName == null || selectedModuleName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a module.");
            return;
        }

        String moduleID = moduleMap.get(selectedModuleName);
        String groupText = groupName.getText().trim();
        String start = (String) startTime.getSelectedItem();
        String end = (String) endTime.getSelectedItem();

        if (groupText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter group name.");
            return;
        }

        // Validate format
        if (!groupText.matches("Group [A-Z]")) {
            JOptionPane.showMessageDialog(this,
                "Group name must strictly follow the format 'Group A'");
            return;
        }

        if (!isValidDuration(start, end)) {
            return;
        }

        if (hasModuleTimeClash(moduleID, start, end)) {
            JOptionPane.showMessageDialog(this,
                "Time clash detected!\n" +
                "Another class under this module already occupies this time range.");
            return;
        }

        String groupID = saveGroup(groupText, moduleID);
        if (groupID == null) return;

        String classID = fn.generateNextID("classes.txt", "C");

        try {
            ArrayList<String[]> classes = UserFunctions.readCSV("classes.txt");

            classes.add(new String[]{
                classID,
                start,
                end,
                moduleID,
                groupID
            });

            UserFunctions.writeCSV("classes.txt", classes);

            JOptionPane.showMessageDialog(this, "Class created successfully!");

            // Reset Input to Empty
            moduleName.setSelectedIndex(0);
            groupName.setText("");
            startTime.setSelectedIndex(0);
            endTime.setSelectedIndex(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving class: " + e.getMessage());
        }


        updateHistoryTable();
    }//GEN-LAST:event_saveButtonActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ClassManagement().setVisible(true);
            }
        }); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> endTime;
    private javax.swing.JLabel endTimeLabel;
    private javax.swing.JLabel groupLabel;
    private javax.swing.JTextField groupName;
    private javax.swing.JLabel groupNameFormat;
    private javax.swing.JLabel historyLabel;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JComboBox<String> moduleName;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> startTime;
    private javax.swing.JLabel startTimeLabel;
    // End of variables declaration//GEN-END:variables
}
