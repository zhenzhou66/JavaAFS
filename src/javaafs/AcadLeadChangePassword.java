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
    Functions func = new Functions();
    
    public String UserID;
    private boolean passwordShow = false;
    
    public AcadLeadChangePassword() {
        userArray = func.readCSV("users.txt");
        initComponents();
    }
    
    public AcadLeadChangePassword(String userid) {
        this();
        UserID = userid;
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        changepasswordlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        changepasswordlbl.setText("Change Password");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(confirmbtn)
                .addGap(18, 18, 18)
                .addComponent(backbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(changepasswordlbl)
                .addGap(122, 122, 122))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oldpasswordlbl)
                    .addComponent(newpasswordlbl)
                    .addComponent(confirmpasswordlbl)
                    .addComponent(statuslbl))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(statustxt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(confirmpasswordtxt)
                                    .addComponent(newpasswordtxt)
                                    .addComponent(oldpasswordtxt))
                                .addGap(65, 65, 65))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(showpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showpassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showpassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(changepasswordlbl)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oldpasswordlbl)
                    .addComponent(oldpasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newpasswordlbl)
                    .addComponent(newpasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(showpassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmpasswordlbl)
                    .addComponent(confirmpasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showpassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statuslbl)
                    .addComponent(statustxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmbtn)
                    .addComponent(backbtn))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        AcadLeadProfile acadleadprofile = new AcadLeadProfile(UserID);
        this.setVisible(false);
        acadleadprofile.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void confirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmbtnActionPerformed
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

    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new AcadLeadChangePassword().setVisible(true));
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel changepasswordlbl;
    private javax.swing.JButton confirmbtn;
    private javax.swing.JLabel confirmpasswordlbl;
    private javax.swing.JPasswordField confirmpasswordtxt;
    private javax.swing.JLabel newpasswordlbl;
    private javax.swing.JPasswordField newpasswordtxt;
    private javax.swing.JLabel oldpasswordlbl;
    private javax.swing.JPasswordField oldpasswordtxt;
    private javax.swing.JButton showpassword1;
    private javax.swing.JButton showpassword2;
    private javax.swing.JButton showpassword3;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JTextField statustxt;
    // End of variables declaration//GEN-END:variables
}
