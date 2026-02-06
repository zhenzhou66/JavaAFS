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
public class AcadLeadLecturer extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AcadLeadLecturer.class.getName());

    protected List<String[]> userArray;
    Functions func = new Functions();
    
    public String UserID = "";
    public String Role = "";
    
    public AcadLeadLecturer() {
        userArray = func.readCSV("users.txt");
        initComponents();
    }
    
    public AcadLeadLecturer(String userid) {
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

        backbtn = new javax.swing.JButton();
        assignlecturerlbl = new javax.swing.JLabel();
        AcadLeadName = new javax.swing.JLabel();
        userRole = new javax.swing.JLabel();
        moduleidlbl = new javax.swing.JLabel();
        modulenamelbl = new javax.swing.JLabel();
        lecturerid = new javax.swing.JLabel();
        lecturernamelbl = new javax.swing.JLabel();
        moduleidcbx = new javax.swing.JComboBox<>();
        savebtn = new javax.swing.JButton();
        modulenametxt = new javax.swing.JTextField();
        lectureridcbx = new javax.swing.JComboBox<>();
        lecturernametxt = new javax.swing.JTextField();
        Status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backbtn.setText("BACK");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        assignlecturerlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        assignlecturerlbl.setText("ASSIGN LECTURER");

        AcadLeadName.setText("jLabel1");

        userRole.setText("jLabel2");

        moduleidlbl.setText("Module ID");

        modulenamelbl.setText("Module Name");

        lecturerid.setText("Lecturer ID");

        lecturernamelbl.setText("Lecturer Name");

        moduleidcbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moduleidcbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleidcbxActionPerformed(evt);
            }
        });

        savebtn.setText("SAVE");

        modulenametxt.setEditable(false);

        lectureridcbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lecturernametxt.setEditable(false);

        Status.setEditable(false);
        Status.setBorder(null);
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
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(assignlecturerlbl)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(userRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AcadLeadName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(savebtn)
                            .addComponent(backbtn))
                        .addGap(176, 176, 176))))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moduleidlbl)
                    .addComponent(modulenamelbl)
                    .addComponent(lecturerid)
                    .addComponent(lecturernamelbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(moduleidcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modulenametxt)
                    .addComponent(lectureridcbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(assignlecturerlbl))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AcadLeadName)
                        .addGap(18, 18, 18)
                        .addComponent(userRole)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleidlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moduleidcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulenamelbl)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturerid)
                    .addComponent(lectureridcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturernamelbl)
                    .addComponent(lecturernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(savebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backbtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        AcadLeaderMenu acadleadermenu = new AcadLeaderMenu(UserID);
        acadleadermenu.setVisible(true);
        this.setVisible(false);    }//GEN-LAST:event_backbtnActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

    private void moduleidcbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleidcbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleidcbxActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new AcadLeadLecturer().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcadLeadName;
    private javax.swing.JTextField Status;
    private javax.swing.JLabel assignlecturerlbl;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel lecturerid;
    private javax.swing.JComboBox<String> lectureridcbx;
    private javax.swing.JLabel lecturernamelbl;
    private javax.swing.JTextField lecturernametxt;
    private javax.swing.JComboBox<String> moduleidcbx;
    private javax.swing.JLabel moduleidlbl;
    private javax.swing.JLabel modulenamelbl;
    private javax.swing.JTextField modulenametxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel userRole;
    // End of variables declaration//GEN-END:variables
}
