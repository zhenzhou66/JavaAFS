/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package javaafs;

import java.util.List;

/**
 *
 * @author junjun
 */
public class AcadLeadViewReport extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AcadLeadViewReport.class.getName());

    protected List<String[]> userArray;

    private UserFunctions func = new UserFunctions();
    
    public String UserID = "";
    public String Role = "";
    
    public AcadLeadViewReport() {
        userArray = func.readCSV("users.txt");
        initComponents();
    }
    
    public AcadLeadViewReport(String userid) {
        this();
        this.UserID = userid;
        loadUserData(userid);
    }
    
private void loadUserData(String userid) {
    if (userArray == null || userArray.isEmpty()) 
        return;

    for (int i = 0; i < userArray.size(); i++) {
        String[] user = userArray.get(i);
        if (user[0].equalsIgnoreCase(userid)) {
            AcadLeadName.setText(user[3]);
            userRole.setText(user[2]);
            break;
        }
    }
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
        ReportTitle1 = new javax.swing.JLabel();
        ID1 = new javax.swing.JLabel();
        Name1 = new javax.swing.JLabel();
        ReportInfo3 = new javax.swing.JLabel();
        ReportInfo4 = new javax.swing.JLabel();
        printbtn1 = new javax.swing.JButton();

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
        pagetitile.setText("ACADEMIC LEADER PROFILE");

        ReportTitle1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReportTitle1.setText("Report Title");

        ID1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ID1.setText("ID");

        Name1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Name1.setText("Name");

        ReportInfo3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReportInfo3.setText("ReportInfo1");

        ReportInfo4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReportInfo4.setText("ReportInfo2");

        printbtn1.setText("PRINT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReportTitle1)
                    .addComponent(ID1)
                    .addComponent(Name1)
                    .addComponent(ReportInfo3)
                    .addComponent(ReportInfo4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(printbtn1)
                .addGap(170, 170, 170))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(ReportTitle1)
                .addGap(24, 24, 24)
                .addComponent(ID1)
                .addGap(24, 24, 24)
                .addComponent(Name1)
                .addGap(24, 24, 24)
                .addComponent(ReportInfo3)
                .addGap(24, 24, 24)
                .addComponent(ReportInfo4)
                .addGap(18, 18, 18)
                .addComponent(printbtn1)
                .addContainerGap(73, Short.MAX_VALUE))
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
                        .addGap(546, 546, 546))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pagetitile, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AcadLeadName, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(userRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(146, 146, 146)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
                .addComponent(profilebtn)
                .addGap(18, 18, 18)
                .addComponent(viewlecturerlistbtn1)
                .addGap(18, 18, 18)
                .addComponent(modulesbtn)
                .addGap(18, 18, 18)
                .addComponent(reportsbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(logOut)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
        java.awt.EventQueue.invokeLater(() -> new AcadLeadViewReport().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcadLeadName;
    private javax.swing.JLabel ID1;
    private javax.swing.JLabel Name1;
    private javax.swing.JLabel ReportInfo3;
    private javax.swing.JLabel ReportInfo4;
    private javax.swing.JLabel ReportTitle1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logOut;
    private javax.swing.JButton modulesbtn;
    private javax.swing.JLabel pagetitile;
    private javax.swing.JButton printbtn1;
    private javax.swing.JButton profilebtn;
    private javax.swing.JButton reportsbtn;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewlecturerlistbtn1;
    // End of variables declaration//GEN-END:variables

}
