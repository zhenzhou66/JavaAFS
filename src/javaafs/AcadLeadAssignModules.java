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
        jPanel2 = new javax.swing.JPanel();
        viewprofilebtn = new javax.swing.JButton();
        viewlecturerlistbtn = new javax.swing.JButton();
        viewmodulesbtn = new javax.swing.JButton();
        viewreportsbtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        AcadLeadName = new javax.swing.JLabel();
        userRole = new javax.swing.JLabel();
        logoutbtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        acadleadmmlbl = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lecturerid = new javax.swing.JLabel();
        lecturernamelbl = new javax.swing.JLabel();
        lectureridcbx = new javax.swing.JComboBox<>();
        lecturernametxt = new javax.swing.JTextField();
        backbtn = new javax.swing.JButton();
        assignmoduleslbl = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        moduleid = new javax.swing.JLabel();
        modulename = new javax.swing.JLabel();
        moduleidcbx = new javax.swing.JComboBox<>();
        modulenametxt = new javax.swing.JTextField();
        Status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 350));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        viewprofilebtn.setText("View Profile");
        viewprofilebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewprofilebtnActionPerformed(evt);
            }
        });

        viewlecturerlistbtn.setText("View Lecturer List");
        viewlecturerlistbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewlecturerlistbtnActionPerformed(evt);
            }
        });

        viewmodulesbtn.setText("View Modules");
        viewmodulesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewmodulesbtnActionPerformed(evt);
            }
        });

        viewreportsbtn.setText("View Reports");
        viewreportsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewreportsbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewmodulesbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewlecturerlistbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
            .addComponent(viewprofilebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewreportsbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewprofilebtn)
                .addGap(18, 18, 18)
                .addComponent(viewlecturerlistbtn)
                .addGap(18, 18, 18)
                .addComponent(viewmodulesbtn)
                .addGap(18, 18, 18)
                .addComponent(viewreportsbtn)
                .addGap(156, 156, 156))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        AcadLeadName.setText("AcadLeadName");

        userRole.setText("userRole");

        logoutbtn.setText("LOG OUT");
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AcadLeadName, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(userRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(logoutbtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(AcadLeadName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userRole)
                .addGap(12, 12, 12)
                .addComponent(logoutbtn)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        acadleadmmlbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        acadleadmmlbl.setText("ASSIGN MODULES TO LECTURER");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(acadleadmmlbl)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(acadleadmmlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        assignmoduleslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

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

        Status.setBorder(null);
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lecturerid)
                                    .addComponent(lecturernamelbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lectureridcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(moduleid)
                                    .addComponent(modulename))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(moduleidcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(savebtn)
                .addGap(63, 63, 63)
                .addComponent(backbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap(321, Short.MAX_VALUE)
                    .addComponent(assignmoduleslbl)
                    .addGap(112, 112, 112)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturerid)
                    .addComponent(lectureridcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturernamelbl)
                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleid)
                    .addComponent(moduleidcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulename)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savebtn)
                    .addComponent(backbtn))
                .addGap(24, 24, 24))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(assignmoduleslbl)
                    .addContainerGap(259, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void viewprofilebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewprofilebtnActionPerformed
        AcadLeadProfile acadleadprofile = new AcadLeadProfile(UserID);
        this.setVisible(false);
        acadleadprofile.setVisible(true);
    }//GEN-LAST:event_viewprofilebtnActionPerformed

    private void viewlecturerlistbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewlecturerlistbtnActionPerformed
        AcadLeadViewLecturerList acadleadviewlecturerlist = new AcadLeadViewLecturerList(UserID);
        this.setVisible(false);
        acadleadviewlecturerlist.setVisible(true);
    }//GEN-LAST:event_viewlecturerlistbtnActionPerformed

    private void viewmodulesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewmodulesbtnActionPerformed
        AcadLeadModules acadleadmodules = new AcadLeadModules(UserID);
        this.setVisible(false);
        acadleadmodules.setVisible(true);
    }//GEN-LAST:event_viewmodulesbtnActionPerformed

    private void viewreportsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewreportsbtnActionPerformed
        AcadLeadReport acadleadreport = new AcadLeadReport(UserID);
        this.setVisible(false);
        acadleadreport.setVisible(true);
    }//GEN-LAST:event_viewreportsbtnActionPerformed

    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
        UserLogin loginScreen = new UserLogin();
        loginScreen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutbtnActionPerformed

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
    private javax.swing.JLabel acadleadmmlbl;
    private javax.swing.JLabel assignmoduleslbl;
    private javax.swing.JButton backbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lecturerid;
    private javax.swing.JComboBox<String> lectureridcbx;
    private javax.swing.JLabel lecturernamelbl;
    private javax.swing.JTextField lecturernametxt;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JLabel moduleid;
    private javax.swing.JComboBox<String> moduleidcbx;
    private javax.swing.JLabel modulename;
    private javax.swing.JTextField modulenametxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewlecturerlistbtn;
    private javax.swing.JButton viewmodulesbtn;
    private javax.swing.JButton viewprofilebtn;
    private javax.swing.JButton viewreportsbtn;
    // End of variables declaration//GEN-END:variables
}
