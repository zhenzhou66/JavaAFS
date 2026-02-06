package javaafs;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class AssignLecturer extends javax.swing.JFrame {


    public AssignLecturer() {
        initComponents();
        loadUserData();
    }
    
    private void loadUserData() {

        DefaultTableModel academicModel =
                (DefaultTableModel) AcademicLeaderTable.getModel();

        DefaultTableModel lecturerModel =
                (DefaultTableModel) LecturerTable.getModel();

        academicModel.setRowCount(0);
        lecturerModel.setRowCount(0);


        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue; // Skip invalid lines

                String userID = data[0].trim();
                String role = data[2].trim();
                String name = data[3].trim();
                String email = data[4].trim();

                if (role.equalsIgnoreCase("Academic Leader")) {
                    academicModel.addRow(new Object[]{userID, name, email});
                } else if (role.equalsIgnoreCase("Lecturer")) {
                    lecturerModel.addRow(new Object[]{userID, name, email});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading users.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subHeading2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        LecturerTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        AcademicLeaderTable = new javax.swing.JTable();
        subHeading1 = new javax.swing.JLabel();
        assignLecturerButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        subHeading2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading2.setText("Lecturer Table");

        LecturerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Lecturer ID", "Name", "Email", "Status", "Academic Leader ID"
            }
        ));
        jScrollPane3.setViewportView(LecturerTable);

        AcademicLeaderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Academic Leader ID", "Name", "Email"
            }
        ));
        jScrollPane1.setViewportView(AcademicLeaderTable);

        subHeading1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeading1.setText("Academic Leader Table");

        assignLecturerButton.setText("Assign Lecturer");
        assignLecturerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignLecturerButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(subHeading1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(91, 226, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(subHeading2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assignLecturerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(subHeading2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subHeading1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(assignLecturerButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void assignLecturerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignLecturerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assignLecturerButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignLecturer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AcademicLeaderTable;
    private javax.swing.JTable LecturerTable;
    private javax.swing.JButton assignLecturerButton;
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel subHeading1;
    private javax.swing.JLabel subHeading2;
    // End of variables declaration//GEN-END:variables
}
