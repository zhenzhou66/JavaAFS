/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaafs;
import java.util.ArrayList;


/**
 *
 * @author junjun
 */
public class AcadLeadAddModules extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AcadLeadAddModules.class.getName());

    private Functions func = new Functions();

    public AcadLeadAddModules() {
        initComponents();
        String nextID = func.generateNextID("modules.txt", "M"); // "M" for modules
        moduleidtxt.setText(nextID);
    }

    private void saveModule() {        
        String moduleID = moduleidtxt.getText().trim();
        String moduleName = modulenametxt.getText().trim();
        if (moduleName.isEmpty()) {
            Status.setText("Error: Please fill module name!");
            return;
        }
        
        ArrayList<String[]> modules = func.readCSV("modules.txt"); //read existing modules
        modules.add(new String[]{moduleID, moduleName}); //add new modules
        func.writeCSV("modules.txt", modules); // save to textfile
        modulenametxt.setText(""); 
        
        String nextID = func.generateNextID("modules.txt", "M");
        moduleidtxt.setText(nextID);
        
        Status.setText("Module added successfully!");        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        moduleidlbl = new javax.swing.JLabel();
        modulenamelbl = new javax.swing.JLabel();
        moduleidtxt = new javax.swing.JTextField();
        modulenametxt = new javax.swing.JTextField();
        savebtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        addmoduleslbl = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        moduleidlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moduleidlbl.setText("ModuleID");

        modulenamelbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        modulenamelbl.setText("Module Name");

        moduleidtxt.setEditable(false);
        moduleidtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleidtxtActionPerformed(evt);
            }
        });

        savebtn.setText("SAVE");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        backbtn.setText("BACK");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        addmoduleslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addmoduleslbl.setText("ADD MODULES");

        Status.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backbtn)
                            .addComponent(savebtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(addmoduleslbl)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moduleidlbl)
                    .addComponent(modulenamelbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moduleidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(addmoduleslbl)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleidlbl)
                    .addComponent(moduleidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulenamelbl)
                    .addComponent(modulenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(savebtn)
                .addGap(18, 18, 18)
                .addComponent(backbtn)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        AcadLeadModules acadleadmodules = new AcadLeadModules();
        this.setVisible(false);
        acadleadmodules.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        saveModule();    }//GEN-LAST:event_savebtnActionPerformed

    private void moduleidtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleidtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleidtxtActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new AcadLeadAddModules().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Status;
    private javax.swing.JLabel addmoduleslbl;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel moduleidlbl;
    private javax.swing.JTextField moduleidtxt;
    private javax.swing.JLabel modulenamelbl;
    private javax.swing.JTextField modulenametxt;
    private javax.swing.JButton savebtn;
    // End of variables declaration//GEN-END:variables
}
