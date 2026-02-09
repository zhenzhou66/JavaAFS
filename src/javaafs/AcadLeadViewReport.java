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
        jPanel5 = new javax.swing.JPanel();
        ReportTitle = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        ReportInfo1 = new javax.swing.JLabel();
        ReportInfo2 = new javax.swing.JLabel();
        printbtn = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 350));

        ReportTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReportTitle.setText("Report Title");

        ID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ID.setText("ID");

        Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Name.setText("Name");

        ReportInfo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReportInfo1.setText("ReportInfo1");

        ReportInfo2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReportInfo2.setText("ReportInfo2");

        printbtn.setText("PRINT");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReportTitle)
                    .addComponent(ID)
                    .addComponent(Name)
                    .addComponent(ReportInfo1)
                    .addComponent(ReportInfo2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printbtn)
                .addGap(170, 170, 170))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(ReportTitle)
                .addGap(24, 24, 24)
                .addComponent(ID)
                .addGap(24, 24, 24)
                .addComponent(Name)
                .addGap(24, 24, 24)
                .addComponent(ReportInfo1)
                .addGap(24, 24, 24)
                .addComponent(ReportInfo2)
                .addGap(18, 18, 18)
                .addComponent(printbtn)
                .addContainerGap(22, Short.MAX_VALUE))
        );

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
            .addComponent(viewlecturerlistbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
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
                .addGap(178, 178, 178))
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
        acadleadmmlbl.setText("View Report");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(acadleadmmlbl)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(acadleadmmlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        Login loginScreen = new Login();
        loginScreen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutbtnActionPerformed

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
    private javax.swing.JLabel ID;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel ReportInfo1;
    private javax.swing.JLabel ReportInfo2;
    private javax.swing.JLabel ReportTitle;
    private javax.swing.JLabel acadleadmmlbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JButton printbtn;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewlecturerlistbtn;
    private javax.swing.JButton viewmodulesbtn;
    private javax.swing.JButton viewprofilebtn;
    private javax.swing.JButton viewreportsbtn;
    // End of variables declaration//GEN-END:variables

}
