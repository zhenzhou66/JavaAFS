/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaafs;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author zhenz
 */
public class StudentAssessmentAnswer extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentAssessmentAnswer.class.getName());

    /**
     * Creates new form Lecturer
     */
    protected List<String[]> userArray;
    protected List<String[]> moduleArray;
    protected List<String[]> assessmentQuestion;
    protected List<String[]> assessmentAnswer;
    protected List<String[]> assessmentResult;
    UserFunctions func = new UserFunctions();

    //user information
    public String Role = "";
    public String UserID = "";
    public String ModuleID = "";
    public String ModuleName = "";
    public String AssessmentID = "";
    
    //assessment questions n answers
    public String A1 = "";
    public String A2 = "";
    public String A3 = "";
    public String A4 = "";
    public String A5 = "";

    
    public StudentAssessmentAnswer() {
        userArray = func.readCSV("users.txt");
        moduleArray = func.readCSV("modules.txt");
        assessmentQuestion = func.readCSV("assessmentQuestion.txt");
        assessmentAnswer = func.readCSV("assessmentAnswer.txt");
        assessmentResult = func.readCSV("assessmentResult.txt");;
        initComponents();
    }
    
    public StudentAssessmentAnswer(String UserID, String assessmentID) {
        this();               
        this.UserID = UserID; 
        this.AssessmentID = assessmentID;
        loadData(UserID,assessmentID); 
    }

    
    private void loadData(String userid, String assessmentid) {
        if (userArray == null || userArray.isEmpty()) 
            return;

        for (int i = 0; i < userArray.size(); i++) {
            String[] user = userArray.get(i);
            if (user[0].equalsIgnoreCase(userid)) {
                lecturerName.setText(user[3]);
                userRole.setText(user[2]);
                ModuleID = user[6];
                break;
            }
        }
        for (int i = 0; i < assessmentQuestion.size(); i++) {
            String[] quiz = assessmentQuestion.get(i);
            if (quiz[0].equalsIgnoreCase(assessmentid)) {
                asmnTitle.setText(AssessmentID.toUpperCase());
                qs1.setText(quiz[2]);
                qs2.setText(quiz[3]);
                qs3.setText(quiz[4]);
                qs4.setText(quiz[5]);
                qs5.setText(quiz[6]);
                break;
            }
        }
    }
    
    private String[] getCorrectAnswer() {
        for (String[] answer : assessmentQuestion) {
            if (answer[0].equalsIgnoreCase(AssessmentID)) {
                return answer;
            }
        }
        return null;
    }
    
    private int calculateMarks(String[] correctAnswer) {
        int marks = 0;

        String[] studentAnswer = {"","","","","","","",A1, A2, A3, A4, A5};

        for (int i = 7; i < 12; i++) {
            if (studentAnswer[i].equalsIgnoreCase(correctAnswer[i])) {
                marks++;
            }
            
        }
        return marks*20;
    }  
    
    private void saveAnswer() {
        // Answers from ComboBoxes
        this.A1 = Ans1.getSelectedItem().toString();
        this.A2 = Ans2.getSelectedItem().toString();
        this.A3 = Ans3.getSelectedItem().toString();
        this.A4 = Ans4.getSelectedItem().toString();
        this.A5 = Ans5.getSelectedItem().toString();

        // 2. generate new ID
        String AnswerID = func.generateNextID("assessmentAnswer.txt", "A"); 

        // 3. create new answer row
        String[] newRecord = {
            AnswerID, 
            this.AssessmentID, 
            this.UserID,
            this.A1, this.A2, this.A3, this.A4, this.A5
        };

        // 4. update array and write to the text file
        assessmentAnswer.add(newRecord);
        func.writeCSV("assessmentAnswer.txt", assessmentAnswer);
    }
    
    private void saveMarks() {
        String[] correctAnswer = getCorrectAnswer();
        if (correctAnswer == null) {
            JOptionPane.showMessageDialog(this, "Correct answers not found!");
            return;
        }
        int marks = calculateMarks(correctAnswer);
        String Mark = Integer.toString(marks);
        
        String ResultID = func.generateNextID("assessmentResult.txt", "R");
        String[] newResult = {ResultID, UserID, AssessmentID, Mark, ModuleID};
        assessmentResult.add(newResult);
        
        func.writeCSV("assessmentResult.txt", assessmentResult);
        
        JOptionPane.showMessageDialog(this, "Submitted successfully!");
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
        pageTitle = new javax.swing.JLabel();
        profilePage = new javax.swing.JButton();
        viewAssessments = new javax.swing.JButton();
        viewClassSchedule = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        lecturerName = new javax.swing.JLabel();
        userRole = new javax.swing.JLabel();
        viewResult = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        qs1 = new javax.swing.JLabel();
        qs2 = new javax.swing.JLabel();
        qs3 = new javax.swing.JLabel();
        qs4 = new javax.swing.JLabel();
        qs5 = new javax.swing.JLabel();
        Ans5 = new javax.swing.JComboBox<>();
        Ans4 = new javax.swing.JComboBox<>();
        Ans3 = new javax.swing.JComboBox<>();
        Ans2 = new javax.swing.JComboBox<>();
        Ans1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        asmnTitle = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        CreateFeedback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        pageTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        pageTitle.setText("MAIN MENU");

        profilePage.setText("Profile");
        profilePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilePageActionPerformed(evt);
            }
        });

        viewAssessments.setText("Assessments");
        viewAssessments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAssessmentsActionPerformed(evt);
            }
        });

        viewClassSchedule.setText("Class Schedule");
        viewClassSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewClassScheduleActionPerformed(evt);
            }
        });

        logOut.setText("Log Out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        lecturerName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lecturerName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lecturerName.setText("yourName");

        userRole.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        userRole.setText("yourRole");

        viewResult.setText("Results");
        viewResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewResultActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Questions");

        qs1.setText("Question 1");

        qs2.setText("Question 2");

        qs3.setText("Question 3");

        qs4.setText("Question 4");

        qs5.setText("Question 5");

        Ans5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "True", "False" }));
        Ans5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ans5ActionPerformed(evt);
            }
        });

        Ans4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "True", "False" }));
        Ans4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ans4ActionPerformed(evt);
            }
        });

        Ans3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "True", "False" }));
        Ans3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ans3ActionPerformed(evt);
            }
        });

        Ans2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "True", "False" }));
        Ans2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ans2ActionPerformed(evt);
            }
        });

        Ans1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "True", "False" }));
        Ans1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ans1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Answers");

        asmnTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        asmnTitle.setText("MAIN MENU");

        submitButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qs5, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asmnTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(jLabel1)
                            .addGap(381, 381, 381))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(qs2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(qs1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(qs3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(qs4, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(Ans1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Ans2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Ans3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Ans4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Ans5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(32, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(asmnTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(221, 221, 221)
                .addComponent(qs5)
                .addGap(28, 28, 28)
                .addComponent(submitButton)
                .addGap(18, 18, 18))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(qs1)
                        .addComponent(Ans1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(qs2)
                        .addComponent(Ans2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(qs3)
                        .addComponent(Ans3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(qs4)
                        .addComponent(Ans4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(Ans5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(68, Short.MAX_VALUE)))
        );

        CreateFeedback.setText("Create Feedback");
        CreateFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateFeedbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 337, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lecturerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(logOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewAssessments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewClassSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                .addComponent(profilePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(CreateFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lecturerName)
                        .addGap(1, 1, 1)
                        .addComponent(userRole))
                    .addComponent(pageTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(profilePage)
                        .addGap(18, 18, 18)
                        .addComponent(viewAssessments)
                        .addGap(18, 18, 18)
                        .addComponent(viewResult)
                        .addGap(18, 18, 18)
                        .addComponent(viewClassSchedule)
                        .addGap(18, 18, 18)
                        .addComponent(CreateFeedback)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOut))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profilePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilePageActionPerformed
        StudentProfilePage StuProfile = new StudentProfilePage(UserID);
        this.setVisible(false);
        StuProfile.setVisible(true);
    }//GEN-LAST:event_profilePageActionPerformed

    private void viewAssessmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAssessmentsActionPerformed
        StudentAssessmentList StuAsmntList = new StudentAssessmentList(UserID);
        this.setVisible(false);
        StuAsmntList.setVisible(true);
    }//GEN-LAST:event_viewAssessmentsActionPerformed

    private void viewClassScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewClassScheduleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewClassScheduleActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed

        func.logout(this, UserID);

    }//GEN-LAST:event_logOutActionPerformed

    private void viewResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewResultActionPerformed
        StudentViewResults viewResults = new StudentViewResults(UserID);
        this.setVisible(false);
        viewResults.setVisible(true);
    }//GEN-LAST:event_viewResultActionPerformed

    private void Ans5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ans5ActionPerformed

    }//GEN-LAST:event_Ans5ActionPerformed

    private void Ans4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ans4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ans4ActionPerformed

    private void Ans3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ans3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ans3ActionPerformed

    private void Ans2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ans2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ans2ActionPerformed

    private void Ans1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ans1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ans1ActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        saveAnswer();
        saveMarks();
        StudentAssessmentList StuAsmntList = new StudentAssessmentList(UserID);
        this.setVisible(false);
        StuAsmntList.setVisible(true);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void CreateFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateFeedbackActionPerformed
        StudentCreateFeedback stuCreateFB = new StudentCreateFeedback(UserID);
        this.setVisible(false);
        stuCreateFB.setVisible(true);
    }//GEN-LAST:event_CreateFeedbackActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new StudentAssessmentAnswer().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Ans1;
    private javax.swing.JComboBox<String> Ans2;
    private javax.swing.JComboBox<String> Ans3;
    private javax.swing.JComboBox<String> Ans4;
    private javax.swing.JComboBox<String> Ans5;
    private javax.swing.JButton CreateFeedback;
    private javax.swing.JLabel asmnTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lecturerName;
    private javax.swing.JButton logOut;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JButton profilePage;
    private javax.swing.JLabel qs1;
    private javax.swing.JLabel qs2;
    private javax.swing.JLabel qs3;
    private javax.swing.JLabel qs4;
    private javax.swing.JLabel qs5;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel userRole;
    private javax.swing.JButton viewAssessments;
    private javax.swing.JButton viewClassSchedule;
    private javax.swing.JButton viewResult;
    // End of variables declaration//GEN-END:variables
}
