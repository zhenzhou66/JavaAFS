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
    protected List<String[]> relationships;

    UserFunctions func = new UserFunctions();
    
    public String UserID = "";
    public String Role = "";
    public String LecturerID = "";
    public String ModuleID = "";
    public String ModuleName = "";

    public AcadLeadAssignModules(String userid) {        
        leaderlecturer = func.readCSV("users.txt");
        modules = func.readCSV("modules.txt");
        relationships = func.readCSV("leaderLecturerRelationship.txt");

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
    
    relationships = func.readCSV("leaderLecturerRelationship.txt");

    boolean hasLecturerToAssign = false;

    for (int i = 1; i < relationships.size(); i++) {
        String[] row = relationships.get(i);
        if (row.length < 3) continue;
        String leaderID = row[0];
        String lecturerID = row[1];
        String moduleID = row[2];
        
        if (leaderID.equalsIgnoreCase(UserID) && (moduleID == null || moduleID.isEmpty())) {
            lectureridcbx.addItem(lecturerID);
            hasLecturerToAssign = true;
        }
    }
    if (!hasLecturerToAssign) {
        lectureridcbx.addItem("None");
        Status.setText("All lecturers under you already have modules assigned.");
        //JOptionPane.showMessageDialog(this, "All lecturers under you already have modules assigned.");
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
    relationships = func.readCSV("leaderLecturerRelationship.txt");
    List<String> assignedModules = new ArrayList<>();
   
    for (int i = 1; i < relationships.size(); i++) {
        String[] row = relationships.get(i);
        if (row.length < 3) continue;
            String moduleID = row[2].trim();
            if (!moduleID.isEmpty()) {
            assignedModules.add(moduleID);
        }
    }
    boolean hasModuleToAssign = false;
    for (int i = 1; i < modules.size(); i++) {
        String[] module = modules.get(i);
        String moduleID = module[0].trim();

        if (!assignedModules.contains(moduleID)) {
            moduleidcbx.addItem(moduleID);
            hasModuleToAssign = true;
        }
    }
    if (!hasModuleToAssign) {
        moduleidcbx.addItem("None");
        Status.setText("All modules already assigned.");
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

    if (selectedLecturerID == null || selectedModuleID == null) {
        Status.setText("Please select a lecturer and a module!");
        return;
    }
    
    ArrayList<String[]> updatedList = new ArrayList<>();

    // keep header
    if (!relationships.isEmpty()) {
        updatedList.add(relationships.get(0));
    }

    // remove previous record with SAME lecturerID
    for (int i = 1; i < relationships.size(); i++) {
        String[] row = relationships.get(i);

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
    
    loadLecturerComboBox();
    loadModulesComboBox();
    lecturernametxt.setText("");
    modulenametxt.setText("");
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logOut = new javax.swing.JButton();
        profilebtn = new javax.swing.JButton();
        viewlecturerlistbtn1 = new javax.swing.JButton();
        modulesbtn = new javax.swing.JButton();
        reportsbtn = new javax.swing.JButton();
        AcadLeadName = new javax.swing.JLabel();
        userRole = new javax.swing.JLabel();
        pagetitile = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lecturerid = new javax.swing.JLabel();
        lecturernamelbl = new javax.swing.JLabel();
        lectureridcbx = new javax.swing.JComboBox<>();
        lecturernametxt = new javax.swing.JTextField();
        backbtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        moduleid = new javax.swing.JLabel();
        modulename = new javax.swing.JLabel();
        moduleidcbx = new javax.swing.JComboBox<>();
        modulenametxt = new javax.swing.JTextField();
        Status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        logOut.setText("Log Out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        profilebtn.setText("Profile");
        profilebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilebtnActionPerformed(evt);
            }
        });

        viewlecturerlistbtn1.setText("View Lecturer List");
        viewlecturerlistbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewlecturerlistbtn1ActionPerformed(evt);
            }
        });

        modulesbtn.setText("Modules");
        modulesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulesbtnActionPerformed(evt);
            }
        });

        reportsbtn.setText("Reports");
        reportsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsbtnActionPerformed(evt);
            }
        });

        AcadLeadName.setText("AcadLeadName");

        userRole.setText("userRole");

        pagetitile.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        pagetitile.setText("ACADEMIC LEADER ASSIGN MODULES");

        lecturerid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lecturerid.setText("Lecturer ID");

        lecturernamelbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        savebtn.setText("SAVE");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        moduleid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moduleid.setText("Module ID");

        modulename.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        Status.setBorder(null);
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lecturerid)
                                    .addComponent(lecturernamelbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lectureridcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(moduleid)
                                    .addComponent(modulename))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(moduleidcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 166, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(savebtn)
                .addGap(63, 63, 63)
                .addComponent(backbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturerid)
                    .addComponent(lectureridcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturernamelbl)
                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleid)
                    .addComponent(moduleidcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulename)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savebtn)
                    .addComponent(backbtn))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logOut, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(modulesbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewlecturerlistbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(profilebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reportsbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pagetitile, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AcadLeadName, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(userRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AcadLeadName)
                        .addGap(12, 12, 12)
                        .addComponent(userRole)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(pagetitile)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(profilebtn)
                        .addGap(18, 18, 18)
                        .addComponent(viewlecturerlistbtn1)
                        .addGap(18, 18, 18)
                        .addComponent(modulesbtn)
                        .addGap(18, 18, 18)
                        .addComponent(reportsbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(logOut))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed

        UserLogin loginScreen = new UserLogin();
        loginScreen.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_logOutActionPerformed

    private void profilebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilebtnActionPerformed
        AcadLeadProfile acadleadprofile = new AcadLeadProfile(UserID);
        this.setVisible(false);
        acadleadprofile.setVisible(true);
    }//GEN-LAST:event_profilebtnActionPerformed

    private void viewlecturerlistbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewlecturerlistbtn1ActionPerformed
        AcadLeadViewLecturerList acadleadviewlecturerlist = new AcadLeadViewLecturerList(UserID);
        this.setVisible(false);
        acadleadviewlecturerlist.setVisible(true);
    }//GEN-LAST:event_viewlecturerlistbtn1ActionPerformed

    private void modulesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulesbtnActionPerformed
        AcadLeadModules acadleadmodules = new AcadLeadModules(UserID);
        this.setVisible(false);
        acadleadmodules.setVisible(true);
    }//GEN-LAST:event_modulesbtnActionPerformed

    private void reportsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsbtnActionPerformed
        AcadLeadReport acadleadreport = new AcadLeadReport(UserID);
        this.setVisible(false);
        acadleadreport.setVisible(true);
    }//GEN-LAST:event_reportsbtnActionPerformed

    private void lectureridcbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lectureridcbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lectureridcbxActionPerformed

    private void lecturernametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lecturernametxtActionPerformed
        showLecturerName();
    }//GEN-LAST:event_lecturernametxtActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        AcadLeadViewLecturerList acadleadviewlecturerlist = new AcadLeadViewLecturerList(UserID);
        this.setVisible(false);
        acadleadviewlecturerlist.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        saveAssignedModule();
    }//GEN-LAST:event_savebtnActionPerformed

    private void moduleidcbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleidcbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleidcbxActionPerformed

    private void modulenametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulenametxtActionPerformed
        showModuleName();
    }//GEN-LAST:event_modulenametxtActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

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
    private javax.swing.JButton backbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lecturerid;
    private javax.swing.JComboBox<String> lectureridcbx;
    private javax.swing.JLabel lecturernamelbl;
    private javax.swing.JTextField lecturernametxt;
    private javax.swing.JButton logOut;
    private javax.swing.JLabel moduleid;
    private javax.swing.JComboBox<String> moduleidcbx;
    private javax.swing.JLabel modulename;
    private javax.swing.JTextField modulenametxt;
    private javax.swing.JButton modulesbtn;
    private javax.swing.JLabel pagetitile;
    private javax.swing.JButton profilebtn;
    private javax.swing.JButton reportsbtn;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewlecturerlistbtn1;
    // End of variables declaration//GEN-END:variables
}
