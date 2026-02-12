/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaafs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author junjun
 */
public class ModuleReport extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ModuleReport.class.getName());

    protected List<String[]> modules;    
    protected List<String[]> assessmentResult;
    protected List<String[]> gradingCriteria;

    private UserFunctions func = new UserFunctions();
    
    private String UserID;
    private String moduleID;
    private String moduleName;
    private String studentCount;
    
    public ModuleReport() {
        
    };
    
    public ModuleReport(String moduleID) {
        modules = func.readCSV("modules.txt");
        assessmentResult = func.readCSV("assessmentResult.txt");
        gradingCriteria = func.readCSV("gradingcriteria.txt");
        this.moduleID = moduleID;
        initComponents();
        loadModulesName();
        calculatePassRate();
        calculateAverageGrade();
        studentCount = String.valueOf(getStudentCount(moduleID));
        loadModuleReport();
    }
    
    private void loadModulesName() {
        moduleName = "";
        for (String[] module : modules) {
            if (module[0].equalsIgnoreCase(moduleID)) {
                moduleName = module[1];
                break;
            }
        }
    }

    private int getStudentCount(String moduleID) {
        List<String> studentIDs = new ArrayList<>();

        for (int i = 1; i < assessmentResult.size(); i++) {
            String[] result = assessmentResult.get(i);
            if (result[4].equalsIgnoreCase(moduleID)) {
                String studentID = result[1];
                if (!studentIDs.contains(studentID)) {  // only add if not already in list
                    studentIDs.add(studentID);
                }
            }
        }

        int count = studentIDs.size();
        return count;
    }

    
    private void calculateAverageGrade() {
        int sum = 0;
        int count = 0;

        // Loop through all assessment results
        for (String[] result : assessmentResult) {
            // Check if this entry is for the desired module
            if (result[3].equalsIgnoreCase("mark")) {
            continue; 
            }
            if (result[4].equalsIgnoreCase(moduleID)) {
                try {
                    int mark = Integer.parseInt(result[3]); // assuming mark is at index 2
                    sum += mark;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid mark for student " + result[0]);
                }
            }
        }

        // Calculate average
        if (count > 0) {
            double average = (double) sum / count;
            
            String grade = "N/A"; // default

            for (String[] g : gradingCriteria) {
                if (g[0].equalsIgnoreCase("Grade")) continue; // skip header

                int min = Integer.parseInt(g[1]);
                int max = Integer.parseInt(g[2]);

                if (average >= min && average <= max) {
                    grade = g[0]; // assign grade
                    break;
                }
            }

            averagegradetxt.setText(grade);
        } else {
            averagegradetxt.setText("N/A");
        }
    }
    
    private void calculatePassRate(){
        int passCount = 0;
        int totalCount = 0;

        for (int i = 1; i < assessmentResult.size(); i++) {
            String[] result = assessmentResult.get(i);
            if (result[4].equalsIgnoreCase(moduleID)) { // column 4 = moduleID
                double mark = Double.parseDouble(result[3]); // column 3 = mark
                totalCount++;
                if (mark >= 40) { // passing mark
                    passCount++;
                }

                String grade = "F"; // default
                for (String[] g : gradingCriteria) {
                    if (g[0].equalsIgnoreCase("Grade")) continue; // skip header
                    int min = Integer.parseInt(g[1]);
                    int max = Integer.parseInt(g[2]);
                    if (mark >= min && mark <= max) {
                        grade = g[0];
                        break;
                        }
                    }

                    // Count as pass if grade is not F
                    if (!grade.equalsIgnoreCase("F")) {
                        passCount++;
                    }
                }
            }   
        if (totalCount == 0) {
            double passRate = (double) passCount / totalCount;
            passratetxt.setText(String.format("%.2f%%", passRate));
        }
        else {
            passratetxt.setText("0%");
        }
    }
    
    private void loadModuleReport() {
        moduleidtxt.setText(moduleID);
        modulenametxt.setText(moduleName);
        numberstudenttxt.setText(studentCount);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        moduleidlbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        moduleidtxt = new javax.swing.JTextField();
        modulenametxt = new javax.swing.JTextField();
        averagegradelbl = new javax.swing.JLabel();
        averagegradetxt = new javax.swing.JTextField();
        passratelbl = new javax.swing.JLabel();
        passratetxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numberstudenttxt = new javax.swing.JTextField();
        moduleReport = new javax.swing.JLabel();
        backbtn = new javax.swing.JButton();

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
        pagetitile.setText("ACADEMIC LEADER REPORTS");

        moduleidlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moduleidlbl.setText("Module ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Module Name");

        moduleidtxt.setEditable(false);

        modulenametxt.setEditable(false);

        averagegradelbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        averagegradelbl.setText("Average Grade");

        averagegradetxt.setEditable(false);

        passratelbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passratelbl.setText("Pass Rate");

        passratetxt.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Number of Student ");

        numberstudenttxt.setEditable(false);

        moduleReport.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        moduleReport.setText("MODULE REPORT");

        backbtn.setText("BACK");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(moduleReport)
                .addGap(120, 120, 120)
                .addComponent(backbtn)
                .addGap(25, 25, 25))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(passratelbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passratetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(averagegradelbl)
                            .addComponent(moduleidlbl)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberstudenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(averagegradetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moduleidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(86, 86, 86))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backbtn)
                    .addComponent(moduleReport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moduleidlbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(moduleidtxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(numberstudenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(averagegradelbl)
                    .addComponent(averagegradetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passratelbl)
                    .addComponent(passratetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(logOut))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        AcadLeadReport acadleadreport = new AcadLeadReport();
        this.setVisible(false);
        acadleadreport.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ModuleReport().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcadLeadName;
    private javax.swing.JLabel averagegradelbl;
    private javax.swing.JTextField averagegradetxt;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logOut;
    private javax.swing.JLabel moduleReport;
    private javax.swing.JLabel moduleidlbl;
    private javax.swing.JTextField moduleidtxt;
    private javax.swing.JTextField modulenametxt;
    private javax.swing.JButton modulesbtn;
    private javax.swing.JTextField numberstudenttxt;
    private javax.swing.JLabel pagetitile;
    private javax.swing.JLabel passratelbl;
    private javax.swing.JTextField passratetxt;
    private javax.swing.JButton profilebtn;
    private javax.swing.JButton reportsbtn;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewlecturerlistbtn1;
    // End of variables declaration//GEN-END:variables
}
