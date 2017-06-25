/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Course;
import Model.Lecturer;
import Model.Student;
import Service.CourseDao;
import Service.CourseDaoImpl;
import Service.LecturerDao;
import Service.LecturerDaoImpl;
import Service.StudentDao;
import Service.StudentDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class Admin extends javax.swing.JFrame {

    private StudentDao studentDao = new StudentDaoImpl();
    private LecturerDao lecturerDao = new LecturerDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    
    public void getStudents(){
        List <Student>students = studentDao.getStudents();
        DefaultTableModel model = (DefaultTableModel)studentTable.getModel();
        model.setRowCount(0);
        
        for(Student student : students){
            String matricNo = student.getMatricNo();
            String surname = student.getSurname();
            String otherNames = student.getOtherNames();
            String dateOfBirth = student.getDateOfBirth();
            String gender = student.getGender();
            String email = student.getEmail();
            String phone = student.getPhone();
            
            model.addRow(new Object[]{matricNo, surname, otherNames, dateOfBirth, gender, email, phone});
        }
    }
    
    public void updateStudents(){
        List <Student> students = new ArrayList();
        for(int i = 0; i<studentTable.getRowCount(); i++){
            String matricNo = String.valueOf(studentTable.getValueAt(i, 0));
            String surname = String.valueOf(studentTable.getValueAt(i, 1));
            String otherNames = String.valueOf(studentTable.getValueAt(i, 2));
            String dateOfBirth = String.valueOf(studentTable.getValueAt(i, 3));
            String gender = String.valueOf(studentTable.getValueAt(i, 4));
            String email = String.valueOf(studentTable.getValueAt(i, 5));
            String phone = String.valueOf(studentTable.getValueAt(i, 6));
            
            Student student = new Student();
            student.setMatricNo(matricNo);
            student.setSurname(surname);
            student.setOtherNames(otherNames);
            student.setDateOfBirth(dateOfBirth);
            student.setGender(gender);
            student.setEmail(email);
            student.setPhone(phone);
            students.add(student);
        }
        int n = studentDao.updateStudents(students);
        if(n>0)
            JOptionPane.showMessageDialog(null, n+" rows updated");
    }
    
    public void deleteStudents(){
        List <Student> students = new ArrayList();
        int selectedRows[] = studentTable.getSelectedRows();
        for(int i = 0; i<selectedRows.length; i++){
            int row = selectedRows[i];
            String matricNo = String.valueOf(studentTable.getValueAt(row, 0));
            
            Student student = new Student();
            student.setMatricNo(matricNo);
            students.add(student);
        }
        int n = studentDao.deleteStudents(students);
        if(n>0){
            getStudents();
            JOptionPane.showMessageDialog(null, n+" rows deleted");
        }
    }
    
    public void getLecturers(){
        List <Lecturer>lecturers = lecturerDao.getLecturers();
        DefaultTableModel model = (DefaultTableModel)lecturerTable.getModel();
        model.setRowCount(0);
        
        for(Lecturer lecturer : lecturers){
            String lecturerId = lecturer.getLectuerId();
            String title = lecturer.getTitle();
            String surname = lecturer.getSurname();
            String otherNames = lecturer.getOtherNames();
            String email = lecturer.getEmail();
            String password = lecturer.getPassword();
            String phone = lecturer.getPhone();
            
            model.addRow(new Object[]{lecturerId, title, surname, otherNames, email, password, phone});
        }
    }
    
    public void updateLecturers(){
        List <Lecturer> lecturers = new ArrayList();
        for(int i = 0; i<lecturerTable.getRowCount(); i++){
            String lecturerId = String.valueOf(lecturerTable.getValueAt(i, 0));
            String title = String.valueOf(lecturerTable.getValueAt(i, 1));
            String surname = String.valueOf(lecturerTable.getValueAt(i, 2));
            String otherNames = String.valueOf(lecturerTable.getValueAt(i, 3));
            String email = String.valueOf(lecturerTable.getValueAt(i, 4));
            String password = String.valueOf(lecturerTable.getValueAt(i, 5));
            String phone = String.valueOf(lecturerTable.getValueAt(i, 6));
            
            Lecturer lecturer = new Lecturer();
            lecturer.setLectuerId(lecturerId);
            lecturer.setTitle(title);
            lecturer.setSurname(surname);
            lecturer.setOtherNames(otherNames);
            lecturer.setEmail(email);
            lecturer.setPassword(password);
            lecturer.setPhone(phone);
            lecturers.add(lecturer);
        }
        int n = lecturerDao.updateLecturers(lecturers);
        if(n>0)
            JOptionPane.showMessageDialog(null, n+" rows updated");
    }
    
    public void deleteLecturers(){
        List <Lecturer> lecturers = new ArrayList();
        int selectedRows[] = lecturerTable.getSelectedRows();
        for(int i = 0; i<selectedRows.length; i++){
            int row = selectedRows[i];
            String lecturerId = String.valueOf(lecturerTable.getValueAt(row, 0));
            
            Lecturer lecturer = new Lecturer();
            lecturer.setLectuerId(lecturerId);
            lecturers.add(lecturer);
        }
        int n = lecturerDao.deleteLecturers(lecturers);
        if(n>0){
            getLecturers();
            JOptionPane.showMessageDialog(null, n+" rows deleted");
        }
    }
    
    public void getCourses(){
        List <Course>courses = courseDao.getCourses();
        DefaultTableModel model = (DefaultTableModel)courseTable.getModel();
        model.setRowCount(0);
        
        for(Course course : courses){
            String courseCode = course.getCourseCode();
            String courseTitle = course.getCourseTitle();
            int unit = course.getUnit();
            String semester = course.getSemester();
            Lecturer lecturer = course.getLecturer();
            int totalClasses = course.getTotalClasses();
            double percentage = course.getPercentage();
            
            model.addRow(new Object[]{courseCode, courseTitle, unit, semester, lecturer.getLectuerId(), totalClasses, percentage});
        }
    }
    
    public void updateCourses(){
        List <Course> courses = new ArrayList();
        for(int i = 0; i<courseTable.getRowCount(); i++){
            String courseCode = String.valueOf(courseTable.getValueAt(i, 0));
            String courseTitle = String.valueOf(courseTable.getValueAt(i, 1));
            int unit = Integer.valueOf(String.valueOf(courseTable.getValueAt(i, 2)));
            String semester = String.valueOf(courseTable.getValueAt(i, 3));
            String lecturerId = String.valueOf(courseTable.getValueAt(i, 4));
            int totalClasses = Integer.valueOf(String.valueOf(courseTable.getValueAt(i, 5)));
            double percentage = Double.valueOf(String.valueOf(courseTable.getValueAt(i, 6)));
            
            Course course = new Course();
            course.setCourseCode(courseCode);
            course.setCourseTitle(courseTitle);
            course.setUnit(unit);
            course.setSemester(semester);
            Lecturer lecturer = new Lecturer();
            lecturer.setLectuerId(lecturerId);
            course.setLecturer(lecturer);
            course.setTotalClasses(totalClasses);
            course.setPercentage(percentage);
            courses.add(course);
        }
        int n = courseDao.updateCourses(courses);
        if(n>0)
            JOptionPane.showMessageDialog(null, n+" rows updated");
    }
    
    public void deleteCourses(){
        List <Course> courses = new ArrayList();
        int selectedRows[] = courseTable.getSelectedRows();
        for(int i = 0; i<selectedRows.length; i++){
            int row = selectedRows[i];
            String courseCode = String.valueOf(courseTable.getValueAt(row, 0));
            
            Course course = new Course();
            course.setCourseCode(courseCode);
            courses.add(course);
        }
        int n = courseDao.deleteCourses(courses);
        if(n>0){
            getCourses();
            JOptionPane.showMessageDialog(null, n+" rows deleted");
        }
    }
    
    
    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        new Thread(()->{
            getStudents();
            getLecturers();
            getCourses();
        }).start();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        btnUpdateStudents = new javax.swing.JButton();
        btnDeleteStudents = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lecturerTable = new javax.swing.JTable();
        btnUpdateLecturers = new javax.swing.JButton();
        btnDeleteLecturers = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        btnUpdateCourse = new javax.swing.JButton();
        btnDeleteCourses = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        mnuNewCourse = new javax.swing.JMenuItem();
        mnuNewLecturer = new javax.swing.JMenuItem();
        mnuNewStudent = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mnuLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 102, 51));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        studentTable.setForeground(new java.awt.Color(0, 102, 51));
        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATRIC NO", "SURNAME", "OTHER NAMES", "DATE OF BIRTH", "GENDER", "EMAIL", "PHONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.setFillsViewportHeight(true);
        studentTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(studentTable);
        if (studentTable.getColumnModel().getColumnCount() > 0) {
            studentTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            studentTable.getColumnModel().getColumn(1).setPreferredWidth(70);
            studentTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            studentTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            studentTable.getColumnModel().getColumn(4).setPreferredWidth(40);
            studentTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            studentTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        btnUpdateStudents.setBackground(new java.awt.Color(0, 102, 51));
        btnUpdateStudents.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateStudents.setText("Update");
        btnUpdateStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStudentsActionPerformed(evt);
            }
        });

        btnDeleteStudents.setBackground(java.awt.Color.red);
        btnDeleteStudents.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteStudents.setText("Delete");
        btnDeleteStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStudentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateStudents)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteStudents)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateStudents)
                    .addComponent(btnDeleteStudents))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Students", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lecturerTable.setForeground(new java.awt.Color(0, 102, 51));
        lecturerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LECTURER ID", "TITLE", "SURNAME", "OTHER NAMES", "EMAIL", "PASSWORD", "PHONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lecturerTable.setFillsViewportHeight(true);
        lecturerTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(lecturerTable);
        if (lecturerTable.getColumnModel().getColumnCount() > 0) {
            lecturerTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            lecturerTable.getColumnModel().getColumn(1).setPreferredWidth(20);
            lecturerTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            lecturerTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            lecturerTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            lecturerTable.getColumnModel().getColumn(5).setPreferredWidth(50);
            lecturerTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        btnUpdateLecturers.setBackground(new java.awt.Color(0, 102, 51));
        btnUpdateLecturers.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateLecturers.setText("Update");
        btnUpdateLecturers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateLecturersActionPerformed(evt);
            }
        });

        btnDeleteLecturers.setBackground(java.awt.Color.red);
        btnDeleteLecturers.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteLecturers.setText("Delete");
        btnDeleteLecturers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLecturersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateLecturers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteLecturers)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateLecturers)
                    .addComponent(btnDeleteLecturers))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lecturers", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        courseTable.setForeground(new java.awt.Color(0, 102, 51));
        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COURSE CODE", "COURSE TITLE", "UNIT", "SEMESTER", "LECTURER", "TOTAL CLASSES", "PERCENTAGE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        courseTable.setFillsViewportHeight(true);
        jScrollPane3.setViewportView(courseTable);
        if (courseTable.getColumnModel().getColumnCount() > 0) {
            courseTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            courseTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            courseTable.getColumnModel().getColumn(2).setPreferredWidth(20);
            courseTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            courseTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            courseTable.getColumnModel().getColumn(5).setPreferredWidth(20);
            courseTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        btnUpdateCourse.setBackground(new java.awt.Color(0, 102, 51));
        btnUpdateCourse.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateCourse.setText("Update");
        btnUpdateCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCourseActionPerformed(evt);
            }
        });

        btnDeleteCourses.setBackground(java.awt.Color.red);
        btnDeleteCourses.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCourses.setText("Delete");
        btnDeleteCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCoursesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateCourse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteCourses)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateCourse)
                    .addComponent(btnDeleteCourses))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Courses", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jMenu1.setText("File");

        jMenu2.setText("New");

        mnuNewCourse.setText("Course");
        mnuNewCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewCourseActionPerformed(evt);
            }
        });
        jMenu2.add(mnuNewCourse);

        mnuNewLecturer.setText("Lecturer");
        mnuNewLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewLecturerActionPerformed(evt);
            }
        });
        jMenu2.add(mnuNewLecturer);

        mnuNewStudent.setText("Student");
        mnuNewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewStudentActionPerformed(evt);
            }
        });
        jMenu2.add(mnuNewStudent);

        jMenu1.add(jMenu2);
        jMenu1.add(jSeparator5);

        mnuLogout.setText("Log Out");
        mnuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(mnuLogout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStudentsActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            updateStudents();
        }).start();
    }//GEN-LAST:event_btnUpdateStudentsActionPerformed

    private void btnDeleteStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStudentsActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            deleteStudents();
        }).start();
    }//GEN-LAST:event_btnDeleteStudentsActionPerformed

    private void btnUpdateLecturersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateLecturersActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            updateLecturers();
        }).start();
    }//GEN-LAST:event_btnUpdateLecturersActionPerformed

    private void btnDeleteLecturersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLecturersActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            deleteLecturers();
        }).start();
    }//GEN-LAST:event_btnDeleteLecturersActionPerformed

    private void btnUpdateCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCourseActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            updateCourses();
        }).start();
    }//GEN-LAST:event_btnUpdateCourseActionPerformed

    private void btnDeleteCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCoursesActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            deleteCourses();
        }).start();
    }//GEN-LAST:event_btnDeleteCoursesActionPerformed

    private void mnuNewCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewCourseActionPerformed
        // TODO add your handling code here:
        CreateCourse createCourse = new CreateCourse(null, true);
        createCourse.setVisible(true);
        getCourses();
    }//GEN-LAST:event_mnuNewCourseActionPerformed

    private void mnuNewLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewLecturerActionPerformed
        // TODO add your handling code here:
        LecturerEnrollment lecturerEnrollment = new LecturerEnrollment(null, true);
        lecturerEnrollment.setVisible(true);
        getLecturers();
    }//GEN-LAST:event_mnuNewLecturerActionPerformed

    private void mnuNewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewStudentActionPerformed
        // TODO add your handling code here:
        StudentEnrollment studentEnrollment = new StudentEnrollment(null, true);
        studentEnrollment.setVisible(true);
        getStudents();
    }//GEN-LAST:event_mnuNewStudentActionPerformed

    private void mnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLogoutActionPerformed
        // TODO add your handling code here:
        LoginPage loginPage = new LoginPage();
        dispose();
        loginPage.setVisible(true);
    }//GEN-LAST:event_mnuLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteCourses;
    private javax.swing.JButton btnDeleteLecturers;
    private javax.swing.JButton btnDeleteStudents;
    private javax.swing.JButton btnUpdateCourse;
    private javax.swing.JButton btnUpdateLecturers;
    private javax.swing.JButton btnUpdateStudents;
    private javax.swing.JTable courseTable;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable lecturerTable;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenuItem mnuNewCourse;
    private javax.swing.JMenuItem mnuNewLecturer;
    private javax.swing.JMenuItem mnuNewStudent;
    private javax.swing.JTable studentTable;
    // End of variables declaration//GEN-END:variables
}
