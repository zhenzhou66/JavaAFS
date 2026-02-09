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
public class AcadLeadChangePassword extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AcadLeadChangePassword.class.getName());

    protected List<String[]> userArray;
    
    UserFunctions func = new UserFunctions();
    
    public String UserID;
    public String Role = "";

    private boolean passwordShow = false;
    
    public AcadLeadChangePassword() {
        userArray = func.readCSV("users.txt");
        initComponents();
    }
    
    public AcadLeadChangePassword(String userid) {
        this();
        UserID = userid;
        loadUserData(userid);
    }
    
private void loadUserData(String userid) {
    if (userArray == null || userArray.isEmpty()) 
        return;

    for (int i = 0; i < userArray.size(); i++) {
        String[] user = userArray.get(i);
        if (user[0].equalsIgnoreCase(userid)) {
//            AcadLeadName.setText(user[3]);
//            userRole.setText(user[2]);
            break;
            }
        }
    }

private void changePassword() {
    String oldPassword = new String(oldpasswordtxt.getPassword());
        String newPassword = new String(newpasswordtxt.getPassword());
        String confirmPassword = new String(confirmpasswordtxt.getPassword());
        
        if (oldPassword.equals("")) {
            statustxt.setText("Old Password cannot be empty.");
            return;
        }
        
        if (newPassword.equals("")) {
            statustxt.setText("New Password cannot be empty.");
            return;
        }
        
        if (confirmPassword.equals("")) {
            statustxt.setText("Confirm Password cannot be empty.");
            return;
       }
        
        boolean validOld = func.authUser(userArray, UserID, oldPassword);

        if (!validOld) {
            statustxt.setText("Old password is incorrect!");
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            statustxt.setText("New password do not match!");
            return;
        }
        
        boolean updated = func.updatePassword(userArray, UserID, newPassword);
    
        if (updated) {
            func.savePassword(userArray, "users.txt");
            statustxt.setText("Password changed successfully!");
            
            oldpasswordtxt.setText("");
            newpasswordtxt.setText("");
            confirmpasswordtxt.setText("");
        } 
        else {
            statustxt.setText("Password change failed.");
        }
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
        changepasswordlbl = new javax.swing.JLabel();
        oldpasswordlbl = new javax.swing.JLabel();
        newpasswordlbl = new javax.swing.JLabel();
        confirmpasswordlbl = new javax.swing.JLabel();
        confirmbtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        statuslbl = new javax.swing.JLabel();
        statustxt = new javax.swing.JTextField();
        confirmpasswordtxt = new javax.swing.JPasswordField();
        newpasswordtxt = new javax.swing.JPasswordField();
        oldpasswordtxt = new javax.swing.JPasswordField();
        showpassword1 = new javax.swing.JButton();
        showpassword2 = new javax.swing.JButton();
        showpassword3 = new javax.swing.JButton();

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
        acadleadmmlbl.setText("CHANGE PASSWORD");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acadleadmmlbl)
                .addGap(94, 94, 94))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(acadleadmmlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        changepasswordlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        oldpasswordlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        oldpasswordlbl.setText("Old Password");

        newpasswordlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        newpasswordlbl.setText("New Password");

        confirmpasswordlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmpasswordlbl.setText("Confirm Password");

        confirmbtn.setText("CONFIRM");
        confirmbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmbtnActionPerformed(evt);
            }
        });

        backbtn.setText("BACK");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        statuslbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        statuslbl.setText("Status");

        statustxt.setEditable(false);
        statustxt.setBorder(null);

        oldpasswordtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldpasswordtxtActionPerformed(evt);
            }
        });

        showpassword1.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        showpassword1.setText("Show Password");
        showpassword1.setBorder(null);
        showpassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassword1ActionPerformed(evt);
            }
        });

        showpassword2.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        showpassword2.setText("Show Password");
        showpassword2.setBorder(null);
        showpassword2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassword2ActionPerformed(evt);
            }
        });

        showpassword3.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        showpassword3.setText("Show Password");
        showpassword3.setBorder(null);
        showpassword3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassword3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showpassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statuslbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statustxt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(confirmbtn)
                    .addGap(18, 18, 18)
                    .addComponent(backbtn)
                    .addGap(171, 171, 171))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addComponent(changepasswordlbl)
                    .addGap(57, 57, 57))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(oldpasswordlbl)
                        .addComponent(newpasswordlbl)
                        .addComponent(confirmpasswordlbl))
                    .addGap(89, 89, 89)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(confirmpasswordtxt)
                        .addComponent(newpasswordtxt)
                        .addComponent(oldpasswordtxt)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(showpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(showpassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showpassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statuslbl)
                    .addComponent(statustxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(changepasswordlbl)
                    .addGap(41, 41, 41)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oldpasswordlbl)
                        .addComponent(oldpasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(showpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newpasswordlbl)
                        .addComponent(newpasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5)
                    .addComponent(showpassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmpasswordlbl)
                        .addComponent(confirmpasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmbtn)
                        .addComponent(backbtn))
                    .addContainerGap()))
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
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
        AcadLeadProfile acadleadprofile = new AcadLeadProfile(UserID);
        this.setVisible(false);
        acadleadprofile.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void confirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmbtnActionPerformed
        changePassword();
    }//GEN-LAST:event_confirmbtnActionPerformed

    private void oldpasswordtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldpasswordtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldpasswordtxtActionPerformed

    private void showpassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassword1ActionPerformed

        if (!passwordShow){
            oldpasswordtxt.setEchoChar((char) 0); 
            showpassword1.setText("Hide Password");
            passwordShow = true;
        }
        else {
            oldpasswordtxt.setEchoChar('•');
            showpassword1.setText("Show Password");
            passwordShow = false;
        }
    }//GEN-LAST:event_showpassword1ActionPerformed

    private void showpassword3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassword3ActionPerformed
        if (!passwordShow){
            confirmpasswordtxt.setEchoChar((char) 0);
            showpassword3.setText("Hide Password");
            passwordShow = true;
        }
        else {
            confirmpasswordtxt.setEchoChar('•');
            showpassword3.setText("Show Password");
            passwordShow = false;
        }
    }//GEN-LAST:event_showpassword3ActionPerformed

    private void showpassword2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassword2ActionPerformed
        if (!passwordShow){
            newpasswordtxt.setEchoChar((char) 0);
            showpassword2.setText("Hide Password");
            passwordShow = true;
        }
        else {
            newpasswordtxt.setEchoChar('•');
            showpassword2.setText("Show Password");
            passwordShow = false;
        }
    }//GEN-LAST:event_showpassword2ActionPerformed

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

    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new AcadLeadChangePassword().setVisible(true));
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcadLeadName;
    private javax.swing.JLabel acadleadmmlbl;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel changepasswordlbl;
    private javax.swing.JButton confirmbtn;
    private javax.swing.JLabel confirmpasswordlbl;
    private javax.swing.JPasswordField confirmpasswordtxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JLabel newpasswordlbl;
    private javax.swing.JPasswordField newpasswordtxt;
    private javax.swing.JLabel oldpasswordlbl;
    private javax.swing.JPasswordField oldpasswordtxt;
    private javax.swing.JButton showpassword1;
    private javax.swing.JButton showpassword2;
    private javax.swing.JButton showpassword3;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JTextField statustxt;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewlecturerlistbtn;
    private javax.swing.JButton viewmodulesbtn;
    private javax.swing.JButton viewprofilebtn;
    private javax.swing.JButton viewreportsbtn;
    // End of variables declaration//GEN-END:variables
}
