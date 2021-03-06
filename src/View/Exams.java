/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.ExamDao;
import Service.ExamDaoImpl;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Andrew
 */
public class Exams extends javax.swing.JDialog {

    private ExamDao examDao = new ExamDaoImpl();
    
    public JLabel getPictureLabel() {
        return lblPicture;
    }

    public void makeReport(String string) {
//        txtLog.append(string + "\n");
    }

    public void setPrompt(String string) {
        txtPrompt.setText(string);
    }
    
    public void showDetails(boolean value){
        studentDetailsPanel.setVisible(value);
    }
    
    public String getCourseCode(){
        return lblCourseCode.getText();
    }
    
    public void setCourseCode(String courseCode){
        lblCourseCode.setText(courseCode);
    }
    
    public String getCourseTitle(){
        return lblCourseTitle.getText();
    }
    
    public void setCourseTitle(String courseTile){
        lblCourseTitle.setText(courseTile);
    }
    
    public void setTotalClasses(String totalClasses){
        lblTotalClasses.setText(totalClasses);
    }
    
    public void setPercentageRequired(String percentageRequired){
        lblPercentageRequired.setText(percentageRequired);
    }
    
    public void setTotalAttendance(String totalAttendance){
        lblTotalAttendance.setText(totalAttendance);
    }
    
    public void setPercentage(String percentage){
        lblPercentage.setText(percentage);
    }
    
    public void setImage(String path){
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(lblPassport.getWidth(), lblPassport.getHeight(), Image.SCALE_SMOOTH);
        lblPassport.setIcon(new ImageIcon(image));
    }
    
    public String getMatricNo(){
        return lblMatricNo.getText();
    }
    
    public void setMatricNo(String matricNo){
        lblMatricNo.setText(matricNo);
    }
    
    public void setSurname(String surname){
        lblSurname.setText(surname);
    }
    
    public void setOtherNames(String otherNames){
        lblOtherNames.setText(otherNames);
    }
    
    public void setGender(String gender){
        lblGender.setText(gender);
    }
    
    private void start(){
        examDao.startScanner(this);
    }
    
    private void stop(){
        examDao.stopScanner();
    }
    
    /**
     * Creates new form Attendance
     */
    public Exams(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        showDetails(false);
        addComponentListener(new ComponentAdapter() {
            @Override 
            public void componentShown(ComponentEvent e) {
                start();
            }
            
            @Override 
            public void componentHidden(ComponentEvent e) {
                stop();
            }
        });
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
        studentDetailsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblMatricNo = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblOtherNames = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblPassport = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPicture = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtPrompt = new javax.swing.JTextField();
        lblCourseCode = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblCourseTitle = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblPercentageRequired = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        lblTotalClasses = new javax.swing.JLabel();
        lblTotalAttendance = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblPercentage = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Exams");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        studentDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        studentDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)), "Student Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 51))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("Matric No:");

        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jLabel3.setText("Surname:");

        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("Other Names:");

        jLabel6.setForeground(new java.awt.Color(0, 102, 51));
        jLabel6.setText("Gender:");

        lblMatricNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMatricNo.setForeground(new java.awt.Color(0, 102, 51));
        lblMatricNo.setText("Matric No");

        lblSurname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSurname.setForeground(new java.awt.Color(0, 102, 51));
        lblSurname.setText("Surname");

        lblOtherNames.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOtherNames.setForeground(new java.awt.Color(0, 102, 51));
        lblOtherNames.setText("Other Names");

        lblGender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGender.setForeground(new java.awt.Color(0, 102, 51));
        lblGender.setText("Gender");

        javax.swing.GroupLayout studentDetailsPanelLayout = new javax.swing.GroupLayout(studentDetailsPanel);
        studentDetailsPanel.setLayout(studentDetailsPanelLayout);
        studentDetailsPanelLayout.setHorizontalGroup(
            studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                        .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassport, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMatricNo)
                                    .addComponent(lblOtherNames)
                                    .addComponent(lblSurname)
                                    .addComponent(lblGender))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        studentDetailsPanelLayout.setVerticalGroup(
            studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPassport, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblMatricNo))
                .addGap(7, 7, 7)
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblSurname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblOtherNames))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblGender)))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ustLogo.jpg"))); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblPicture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPicture.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)));
        lblPicture.setIconTextGap(0);

        txtPrompt.setEditable(false);
        txtPrompt.setText("Scan your fingerprint");
        txtPrompt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)), "Prompt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 51))); // NOI18N

        lblCourseCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCourseCode.setForeground(new java.awt.Color(0, 102, 51));
        lblCourseCode.setText("Course Code");

        jLabel11.setForeground(new java.awt.Color(0, 102, 51));
        jLabel11.setText("Code:");

        jLabel13.setForeground(new java.awt.Color(0, 102, 51));
        jLabel13.setText("Title:");

        lblCourseTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCourseTitle.setForeground(new java.awt.Color(0, 102, 51));
        lblCourseTitle.setText("Course Title");

        jLabel15.setForeground(new java.awt.Color(0, 102, 51));
        jLabel15.setText("Percentage required:");

        lblPercentageRequired.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPercentageRequired.setForeground(new java.awt.Color(0, 102, 51));
        lblPercentageRequired.setText("%0.00");

        jLabel16.setForeground(new java.awt.Color(0, 102, 51));
        jLabel16.setText("Total Classes:");

        lblTotalClasses.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalClasses.setForeground(new java.awt.Color(0, 102, 51));
        lblTotalClasses.setText("0");

        lblTotalAttendance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalAttendance.setForeground(new java.awt.Color(0, 102, 51));
        lblTotalAttendance.setText("Total Attendance");

        jLabel17.setForeground(new java.awt.Color(0, 102, 51));
        jLabel17.setText("Total Attendance");

        jLabel18.setForeground(new java.awt.Color(0, 102, 51));
        jLabel18.setText("Percentage:");

        lblPercentage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPercentage.setForeground(new java.awt.Color(0, 102, 51));
        lblPercentage.setText("Percentage");

        btnLogout.setBackground(java.awt.Color.red);
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Back");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(txtPrompt, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(jSeparator4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotalClasses)
                                    .addComponent(lblPercentageRequired)
                                    .addComponent(lblCourseTitle)
                                    .addComponent(lblCourseCode)
                                    .addComponent(lblTotalAttendance)
                                    .addComponent(lblPercentage)))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCourseCode)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCourseTitle)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalClasses)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lblPercentageRequired))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalAttendance)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lblPercentage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnLogout)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        stop();
        Attendance attendance = new Attendance(null, true);
        dispose();
        attendance.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Exams dialog = new Exams(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblCourseCode;
    private javax.swing.JLabel lblCourseTitle;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblMatricNo;
    private javax.swing.JLabel lblOtherNames;
    private javax.swing.JLabel lblPassport;
    private javax.swing.JLabel lblPercentage;
    private javax.swing.JLabel lblPercentageRequired;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblTotalAttendance;
    private javax.swing.JLabel lblTotalClasses;
    private javax.swing.JPanel studentDetailsPanel;
    private javax.swing.JTextField txtPrompt;
    // End of variables declaration//GEN-END:variables
}
