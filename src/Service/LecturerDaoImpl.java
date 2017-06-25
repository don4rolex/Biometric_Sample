/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Config.AppConfig;
import Model.Lecturer;
import View.Admin;
import View.Attendance;
import View.LoginPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 */
public class LecturerDaoImpl implements LecturerDao{
    
    private AppConfig appConfig = new AppConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    
    @Override
    public int enrollLecturer(Lecturer lecturer){
        int n = 0;
        List <String>errorList = new ArrayList();
        
        String lecturerId = lecturer.getLectuerId();
        String title = lecturer.getTitle();
        String surname = lecturer.getSurname();
        String otherNames = lecturer.getOtherNames();
        String email = lecturer.getEmail();
        String password = lecturer.getPassword();
        String phone = lecturer.getPhone();
        
        if(lecturerId.isEmpty()){
            errorList.add("Empty Lecturer ID field");
        }
        
        if(title.equals("Please Select")){
            errorList.add("Select Title");
        }
        
        if(surname.isEmpty()){
            errorList.add("Empty Surname field");
        }
        
        if(otherNames.isEmpty()){
            errorList.add("Empty Other Names field");
        }
        
        if(email.isEmpty()){
            errorList.add("Empty Email field");
        }
        
        if(password.isEmpty()){
            errorList.add("Empty Password field");
        }
        
        if(phone.isEmpty()){
            errorList.add("Empty Phone field");
        }
        
        if(!errorList.isEmpty()){
            String error = "<html>"
                + "<font color='red'>Please correct the following errors:<br>"
                + "<ul>";
            for (String message : errorList) {
                error += "<li>" + message + "</li>";
            }
            error += "</ul></font></html>";
            JOptionPane.showMessageDialog(null, error);
        }else{
            con = appConfig.con();
            try {
                ps = con.prepareStatement("INSERT INTO lecturers VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, lecturerId);
                ps.setString(2, title);
                ps.setString(3, surname);
                ps.setString(4, otherNames);
                ps.setString(5, email);
                ps.setString(6, password);
                ps.setString(7, phone);
                n = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                if(e.getMessage().contains("Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"Matric No has already been registered");
                }
            }finally{
                try {
                    if(ps!=null)
                        ps.close();
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return n;
    }
    
    @Override
    public void login(LoginPage loginPage, String lecturerId, String password){
        if(lecturerId.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Incomplete fields");
        }else{
            con = appConfig.con();
            try {
                if(lecturerId.equalsIgnoreCase("admin")){
                    ps = con.prepareStatement("SELECT LECTURER_ID, PASSWORD FROM lecturers WHERE LECTURER_ID = ? AND PASSWORD = ?");
                }else{
                    ps = con.prepareStatement("SELECT a.SURNAME || ' ' || a.OTHER_NAMES AS FULL_NAME, b.COURSE_CODE, b.COURSE_TITLE "
                            + "FROM lecturers a JOIN courses b ON b.LECTURER = a.LECTURER_ID "
                            + "WHERE a.LECTURER_ID = ? AND a.PASSWORD = ?");
    //                ps = con.prepareStatement("SELECT CONCAT(a.SURNAME, ' ', a.OTHER_NAMES) AS FULL_NAME, b.COURSE_CODE, b.COURSE_TITLE "
    //                        + "FROM lecturers a JOIN courses b ON b.LECTURER = a.LECTURER_ID "
    //                        + "WHERE a.LECTURER_ID = ? AND BINARY a.PASSWORD = ?");
                }
                ps.setString(1, lecturerId);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    if(lecturerId.equals("admin")){
                        Admin admin = new Admin();
                        loginPage.setVisible(false);
                        admin.setVisible(true);
                    }else{
                        String fullName = rs.getString(1);
                        String courseCode = rs.getString(2);
                        String courseTitle = rs.getString(3);
                        
                        Attendance attendance = new Attendance(null, true);
                        attendance.setCourseCode(courseCode);
                        attendance.setCourseTitle(courseTitle);
                        attendance.setLecturerId(lecturerId);
                        attendance.setLecturerName(fullName);
                        loginPage.setVisible(false);
                        attendance.setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login details");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(ps!=null)
                        ps.close();
                    con.close();
                } catch (SQLException e) {}
            }
        }
    }
    
    @Override
    public List<Lecturer>getLecturers(){
        List <Lecturer>lecturers = new ArrayList();
        
        try {
            con = appConfig.con();
            ps = con.prepareStatement("SELECT * FROM lecturers");
            rs = ps.executeQuery();
            while(rs.next()){
                Lecturer lecturer = new Lecturer();
                lecturer.setLectuerId(rs.getString(1));
                lecturer.setTitle(rs.getString(2));
                lecturer.setSurname(rs.getString(3));
                lecturer.setOtherNames(rs.getString(4));
                lecturer.setEmail(rs.getString(5));
                lecturer.setPassword(rs.getString(6));
                lecturer.setPhone(rs.getString(7));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(ps!=null)
                    ps.close();
                con.close();
            } catch (SQLException e) {}
        }
        return lecturers;
    }
    
    @Override
    public int updateLecturers(List <Lecturer> lecturers){
        int n[] = {};
        List <String>errorList = new ArrayList();
        
        if(!lecturers.isEmpty()){
            try {
                con = appConfig.con();
                ps = con.prepareStatement("UPDATE lecturers SET TITLE = ?, SURNAME = ?, OTHER_NAMES = ?, EMAIL = ?, PASSWORD = ?, "
                        + "PHONE = ? WHERE LECTURER_ID = ?");
                int i = 1;
                for(Lecturer lecturer : lecturers){
                    
                    String lecturerId = lecturer.getLectuerId();
                    String title = lecturer.getTitle();
                    String surname = lecturer.getSurname();
                    String otherNames = lecturer.getOtherNames();
                    String email = lecturer.getEmail();
                    String password = lecturer.getPassword();
                    String phone = lecturer.getPhone();
                    
                    if(lecturerId.isEmpty() || surname.isEmpty() || otherNames.isEmpty() || title.isEmpty() || password.isEmpty()
                            || email.isEmpty() || phone.isEmpty()){
                        errorList.add("Incomplete cells on row "+i);
                    }else{
                        ps.setString(1, title);
                        ps.setString(2, surname);
                        ps.setString(3, otherNames);
                        ps.setString(4, email);
                        ps.setString(5, password);
                        ps.setString(6, phone);
                        ps.setString(7, lecturerId);
                        ps.addBatch();
                    }
                    i++;
                }
                if(!errorList.isEmpty()){
                    String error = "<html>"
                        + "<font color='red'>Please correct the following errors:<br>"
                        + "<ul>";
                    for (String message : errorList) {
                        error += "<li>" + message + "</li>";
                    }
                    error += "</ul></font></html>";
                    JOptionPane.showMessageDialog(null, error);
                }else{
                    n = ps.executeBatch();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(ps!=null)
                        ps.close();
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return n.length;
    }
    
    @Override
    public int deleteLecturers(List <Lecturer> lecturers){
        int n[] = {};
        if(!lecturers.isEmpty()){
            try {
                con = appConfig.con();
                ps = con.prepareStatement("DELETE FROM lecturers WHERE LECTURER_ID = ?");
                for(Lecturer lecturer : lecturers){
                    String lecturerId = lecturer.getLectuerId();
                    ps.setString(1, lecturerId);
                    ps.addBatch();
                }
                
                n = ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(ps!=null)
                        ps.close();
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return n.length;
    }
}