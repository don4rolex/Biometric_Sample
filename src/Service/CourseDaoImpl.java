/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Config.AppConfig;
import Model.Course;
import Model.Lecturer;
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
public class CourseDaoImpl implements CourseDao{
    
    private AppConfig appConfig = new AppConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public int createCourse(Course course){
        int n = 0;
        List <String>errorList = new ArrayList();
        
        String courseCode = course.getCourseCode();
        String courseTitle = course.getCourseTitle();
        int unit = course.getUnit();
        String semester = course.getSemester();
        Lecturer lecturer = course.getLecturer();
        int totalClasses = course.getTotalClasses();
        double percentage = course.getPercentage();
        
        
        if(courseCode.isEmpty()){
            errorList.add("Empty Course Code field");
        }
        
        if(courseTitle.isEmpty()){
            errorList.add("Empty Course Title field");
        }
        
        if(unit < 0){
            errorList.add("Unit Cannot be less than 0");
        }
        
        if(semester.equals("Please Select")){
            errorList.add("Select Semester");
        }
        
        if(lecturer.equals("Please Select")){
            errorList.add("Select Course");
        }
        
        if(semester.isEmpty()){
            errorList.add("Empty Other Names field");
        }
        
        if(totalClasses < 1){
            errorList.add("Total cannot be less than 1");
        }
        
        if(percentage < 0){
            errorList.add("Percentage cannot be less than 0");
        }
        
        if(percentage > 100){
            errorList.add("Percentage cannot be greater than 100");
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
                ps = con.prepareStatement("INSERT INTO courses VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, courseCode);
                ps.setString(2, courseTitle);
                ps.setInt(3, unit);
                ps.setString(4, semester);
                ps.setString(5, lecturer.getLectuerId());
                ps.setDouble(6, totalClasses);
                ps.setDouble(7, percentage);
                n = ps.executeUpdate();
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
        
        return n;
    }
    
    @Override
    public List <Lecturer> getLecturers(){
        List <Lecturer>list = new ArrayList();
        
        con = appConfig.con();
        try {
            ps = con.prepareStatement("SELECT LECTURER_ID, SURNAME, OTHER_NAMES FROM lecturers");
            rs = ps.executeQuery();
            while(rs.next()){
                String lecturerId = rs.getString(1);
                String surname = rs.getString(2);
                String otherNames = rs.getString(3);
                
                Lecturer lecturer = new Lecturer();
                lecturer.setLectuerId(lecturerId);
                lecturer.setSurname(surname);
                lecturer.setOtherNames(otherNames);
                list.add(lecturer);
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
        
        return list;
    }
    
    @Override
    public List<Course>getCourses(){
        List <Course>courses = new ArrayList();
        
        try {
            con = appConfig.con();
            ps = con.prepareStatement("SELECT * FROM courses");
//            ps = con.prepareStatement("SELECT a.COURSE_CODE, a.COURSE_TITLE, a.UNIT, a.SEMESTER, a.TOTAL_CLASS, a.PERCENTAGE,"
//                    + "b.LECTURER_ID, b.SURNAME, b.OTHER_NAMES FROM courses JOIN lecturers b ON b.LECTURER_ID = a.LECTURER");
            rs = ps.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCourseCode(rs.getString(1));
                course.setCourseTitle(rs.getString(2));
                course.setUnit(rs.getInt(3));
                course.setSemester(rs.getString(4));
                Lecturer lecturer = new Lecturer();
                lecturer.setLectuerId(rs.getString(5));
                course.setTotalClasses(rs.getInt(6));
                course.setPercentage(rs.getDouble(7));
                
                course.setLecturer(lecturer);
                courses.add(course);
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
        return courses;
    }
    
    @Override
    public int updateCourses(List <Course> courses){
        int n[] = {};
        List <String>errorList = new ArrayList();
        
        if(!courses.isEmpty()){
            try {
                con = appConfig.con();
                ps = con.prepareStatement("UPDATE courses SET COURSE_TITLE = ?, UNIT = ?, SEMESTER = ?, LECTURER = ?, TOTAL_CLASSES = ?, "
                        + "PERCENTAGE = ? WHERE COURSE_CODE = ?");
                int i = 1;
                for(Course course : courses){
                    
                    String courseCode = course.getCourseCode();
                    String courseTitle = course.getCourseTitle();
                    int unit = course.getUnit();
                    String semester = course.getSemester();
                    Lecturer lecturer = course.getLecturer();
                    int totalClasses = course.getTotalClasses();
                    double percentage = course.getPercentage();
                    
                    if(courseCode.isEmpty() || unit < 0 || semester.isEmpty() || courseTitle.isEmpty() || totalClasses < 0
                            || lecturer.getLectuerId().isEmpty() || percentage < 0){
                        errorList.add("Incomplete cells on row "+i);
                    }else{
                        ps.setString(1, courseTitle);
                        ps.setInt(2, unit);
                        ps.setString(3, semester);
                        ps.setString(4, lecturer.getLectuerId());
                        ps.setInt(5, totalClasses);
                        ps.setDouble(6, percentage);
                        ps.setString(7, courseCode);
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
    public int deleteCourses(List <Course> courses){
        int n[] = {};
        if(!courses.isEmpty()){
            try {
                con = appConfig.con();
                ps = con.prepareStatement("DELETE FROM courses WHERE COURSE_CODE = ?");
                for(Course course : courses){
                    String courseCode = course.getCourseCode();
                    ps.setString(1, courseCode);
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
