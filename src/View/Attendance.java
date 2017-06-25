/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.AttendanceDao;
import Service.AttendanceDaoImpl;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrew
 */
public class Attendance extends javax.swing.JDialog {

    private AttendanceDao attendanceDao = new AttendanceDaoImpl();
    
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
    
    public String getDate(){
        return ((JTextField) txtDate.getDateEditor().getUiComponent()).getText();
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
    
    public String getLecturerId(){
        return lblLecturerId.getText();
    }
    
    public void setLecturerId(String lecturerId){
        lblLecturerId.setText(lecturerId);
    }
    
    public String getLecturerName(){
        return lblName.getText();
    }
    
    public void setLecturerName(String lecturerName){
        lblName.setText(lecturerName);
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
        attendanceDao.startScanner(this);
    }
    
    private void stop(){
        attendanceDao.stopScanner();
    }
    
    /**
     * Creates new form Attendance
     */
    public Attendance(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(new Color(0, 102, 51)));
        ((JTextField) txtDate.getDateEditor().getUiComponent()).setEditable(false);
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
        txtDate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        lblCourseCode = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblCourseTitle = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        lblLecturerId = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnExams = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attendance");

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

        txtDate.setBackground(new java.awt.Color(255, 255, 255));
        txtDate.setDate(new java.util.Date());
        txtDate.setDateFormatString("yyyy-MM-dd");

        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Date:");

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
        jLabel15.setText("Name:");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(0, 102, 51));
        lblName.setText("Name");

        jLabel16.setForeground(new java.awt.Color(0, 102, 51));
        jLabel16.setText("Lecturer ID:");

        lblLecturerId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLecturerId.setForeground(new java.awt.Color(0, 102, 51));
        lblLecturerId.setText("Lecturer ID");

        btnExams.setBackground(new java.awt.Color(0, 102, 51));
        btnExams.setForeground(new java.awt.Color(255, 255, 255));
        btnExams.setText("Exams");
        btnExams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExamsActionPerformed(evt);
            }
        });

        btnLogout.setBackground(java.awt.Color.red);
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Log out");
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
                    .addComponent(jSeparator5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addComponent(txtPrompt, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCourseTitle)
                                    .addComponent(lblCourseCode)
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblName)
                                    .addComponent(lblLecturerId)))
                            .addComponent(jSeparator4))
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
                        .addComponent(btnExams)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogout)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCourseCode)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCourseTitle)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLecturerId)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lblName))
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExams)
                    .addComponent(btnLogout))
                .addGap(6, 6, 6))
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

    private void btnExamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExamsActionPerformed
        // TODO add your handling code here:
        stop();
        Exams exams = new Exams(null, true);
        dispose();
        exams.setCourseCode(getCourseCode());
        exams.setCourseTitle(getCourseTitle());
        exams.setVisible(true);
    }//GEN-LAST:event_btnExamsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        LoginPage loginPage = new LoginPage();
        dispose();
        loginPage.setVisible(true);
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
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Attendance dialog = new Attendance(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnExams;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblCourseCode;
    private javax.swing.JLabel lblCourseTitle;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblLecturerId;
    private javax.swing.JLabel lblMatricNo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOtherNames;
    private javax.swing.JLabel lblPassport;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JPanel studentDetailsPanel;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtPrompt;
    // End of variables declaration//GEN-END:variables
}