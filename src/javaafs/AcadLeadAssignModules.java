/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaafs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author junjun
 */
public class AcadLeadAssignModules extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AcadLeadAssignModules.class.getName());

    protected List<String[]> leaderlecturer;
    protected List<String[]> modules;

    Functions func = new Functions();
    
    public String UserID = "";
    public String Role = "";
    public String LecturerID = "";
    public String ModuleID = "";
    public String ModuleName = "";

    public AcadLeadAssignModules(String userid) {        
        leaderlecturer = func.readCSV("users.txt");
        initComponents();
        this.UserID = userid;        
        loadUserData(userid); 
        loadLecturerComboBox();
        loadModulesComboBox();
    }

private void loadUserData(String userid) {
    if (leaderlecturer == null || leaderlecturer.isEmpty()) 
        return;

    for (int i = 0; i < leaderlecturer.size(); i++) {
        String[] user = leaderlecturer.get(i);
        if (user[0].equalsIgnoreCase(userid)) {
            AcadLeadName.setText(user[3]);
            userRole.setText(user[2]);
            break;
            }
        }
    }

private void loadLecturerComboBox() {
    lectureridcbx.removeAllItems();
    
    ArrayList<String[]> lecturers = func.readCSV("leaderLecturerRelationship.txt");
    
    for (int i = 1; i < lecturers.size(); i++) {
        String[] row = lecturers.get(i);
        
        if (row.length < 2) continue;
        
        String leaderID = row[0];
        String lecturerID = row[1];
        
        if (!leaderID.equalsIgnoreCase(UserID)) continue;
        
        lectureridcbx.addItem(lecturerID);
        }
}
    
private void showLecturerName() {
    String selectedLecturerID = (String) lectureridcbx.getSelectedItem();
    if (selectedLecturerID != null) {
        for (int i = 1; i < leaderlecturer.size(); i++) {
            String[] user = leaderlecturer.get(i);
            if (user[0].equalsIgnoreCase(selectedLecturerID)) {
                lecturernametxt.setText(user[3]);
                break;
            }
        }
    }  
}

private void loadModulesComboBox() {
    moduleidcbx.removeAllItems();
    
    modules = func.readCSV("modules.txt");
    
    for (int i = 1; i < modules.size(); i++) {
        String[] row = modules.get(i);
        
        if (row.length > 1) {
            String moduleID = row[0];
            moduleidcbx.addItem(moduleID);
        }
    }
}
    
private void showModuleName() {
    String selectedModuleID = (String) moduleidcbx.getSelectedItem();
    if (selectedModuleID != null) {
        
        for (int i = 1; i < modules.size(); i++) {
            String[] module = modules.get(i);
            if (module[0].equalsIgnoreCase(selectedModuleID)) {
                modulenametxt.setText(module[1]); 
                break;
            }
        }
    }  
}

private void saveAssignedModule() {
    String selectedLecturerID = (String) lectureridcbx.getSelectedItem();
    String selectedModuleID = (String) moduleidcbx.getSelectedItem();
    String selectedModuleName = modulenametxt.getText();

    if (selectedLecturerID == null || selectedModuleID == null || selectedModuleName.isEmpty()) {
        Status.setText("Please select a lecturer and a module!");
        return;
    }
    
    modules = func.readCSV("leaderLecturerRelationship.txt");
    ArrayList<String[]> updatedList = new ArrayList<>();

    // keep header
    if (!modules.isEmpty()) {
        updatedList.add(modules.get(0));
    }

    // remove previous record with SAME lecturerID
    for (int i = 1; i < modules.size(); i++) {
        String[] row = modules.get(i);

        String lecturerID = row[1];

        if (lecturerID.equalsIgnoreCase(selectedLecturerID)) {
            continue; // delete old record
        }

        updatedList.add(row);
    }

    // add new relationship
    updatedList.add(new String[]{
        UserID,
        selectedLecturerID,
        selectedModuleID
    });

    // sort by LecturerID (column index 1)
    Collections.sort(updatedList.subList(1, updatedList.size()),
        (a, b) -> a[1].compareToIgnoreCase(b[1])
    );
    
    func.writeCSV("leaderLecturerRelationship.txt", updatedList);

    Status.setText("Module assigned succesfully!");    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lecturerid = new javax.swing.JLabel();
        lecturernamelbl = new javax.swing.JLabel();
        lectureridcbx = new javax.swing.JComboBox<>();
        lecturernametxt = new javax.swing.JTextField();
        backbtn = new javax.swing.JButton();
        userRole = new javax.swing.JLabel();
        AcadLeadName = new javax.swing.JLabel();
        assignmoduleslbl = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        moduleid = new javax.swing.JLabel();
        modulename = new javax.swing.JLabel();
        moduleidcbx = new javax.swing.JComboBox<>();
        modulenametxt = new javax.swing.JTextField();
        Status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lecturerid.setText("Lecturer ID");

        lecturernamelbl.setText("Lecturer Name");

        lectureridcbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        lectureridcbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lectureridcbxActionPerformed(evt);
            }
        });

        lecturernametxt.setEditable(false);
        lecturernametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lecturernametxtActionPerformed(evt);
            }
        });

        backbtn.setText("BACK");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        userRole.setText("jLabel2");

        AcadLeadName.setText("jLabel1");

        assignmoduleslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        assignmoduleslbl.setText("ASSIGN MODULES TO LECTURER");

        savebtn.setText("SAVE");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        moduleid.setText("Module ID");

        modulename.setText("Module Name");

        moduleidcbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moduleidcbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleidcbxActionPerformed(evt);
            }
        });

        modulenametxt.setEditable(false);
        modulenametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulenametxtActionPerformed(evt);
            }
        });

        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lecturerid)
                            .addComponent(lecturernamelbl)
                            .addComponent(moduleid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modulename)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lectureridcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moduleidcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(assignmoduleslbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(userRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AcadLeadName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(savebtn)
                            .addComponent(backbtn))
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(AcadLeadName)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(userRole))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(assignmoduleslbl)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturerid)
                    .addComponent(lectureridcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturernamelbl)
                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleid)
                    .addComponent(moduleidcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulename)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(savebtn)
                .addGap(31, 31, 31)
                .addComponent(backbtn)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        AcadLeadViewLecturerList acadleadviewlecturerlist = new AcadLeadViewLecturerList(UserID);
        acadleadviewlecturerlist.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backbtnActionPerformed

    private void lectureridcbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lectureridcbxActionPerformed
        showLecturerName();
    }//GEN-LAST:event_lectureridcbxActionPerformed

    private void lecturernametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lecturernametxtActionPerformed

    }//GEN-LAST:event_lecturernametxtActionPerformed

    private void modulenametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulenametxtActionPerformed

    }//GEN-LAST:event_modulenametxtActionPerformed

    private void moduleidcbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleidcbxActionPerformed
        showModuleName();
    }//GEN-LAST:event_moduleidcbxActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        saveAssignedModule();
    }//GEN-LAST:event_savebtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new AcadLeadAssignModules().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcadLeadName;
    private javax.swing.JTextField Status;
    private javax.swing.JLabel assignmoduleslbl;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel lecturerid;
    private javax.swing.JComboBox<String> lectureridcbx;
    private javax.swing.JLabel lecturernamelbl;
    private javax.swing.JTextField lecturernametxt;
    private javax.swing.JLabel moduleid;
    private javax.swing.JComboBox<String> moduleidcbx;
    private javax.swing.JLabel modulename;
    private javax.swing.JTextField modulenametxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel userRole;
    // End of variables declaration//GEN-END:variables
}
