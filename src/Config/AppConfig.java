/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 */
public class AppConfig {
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private String URL = "jdbc:derby:" + getLocalDir() + "attendance_manager;create=true";
    
    public Connection con(){
        try{
            con = DriverManager.getConnection(URL);
        }catch(SQLException e){
           e.printStackTrace();
        }
        return con;
    }
    
    public void setupDerby(){
        try {
            con = DriverManager.getConnection(URL);
            
            DatabaseMetaData meta = con.getMetaData();
            rs = meta.getTables(null, null, "ATTENDANCE", null);
            if(!rs.next()){
                ps = con.prepareStatement("CREATE TABLE ATTENDANCE(\n" +
                "DATE DATE NOT NULL,\n" +
                "COURSE_CODE VARCHAR(10) NOT NULL,\n" +
                "LECTURER_ID VARCHAR(50) NOT NULL,\n" +
                "MATRIC_NO VARCHAR(20) NOT NULL\n" +
                ")");
                ps.execute();
            }
            
            rs = meta.getTables(null, null, "COURSES", null);
            if(!rs.next()){
                ps = con.prepareStatement("CREATE TABLE COURSES(\n" +
                "COURSE_CODE VARCHAR(10) NOT NULL,\n" +
                "COURSE_TITLE VARCHAR(100) NOT NULL,\n" +
                "UNIT INTEGER NOT NULL,\n" +
                "SEMESTER VARCHAR(20) NOT NULL,\n" +
                "LECTURER VARCHAR(20) NOT NULL,\n" +
                "TOTAL_CLASSES INTEGER NOT NULL,\n" +
                "PERCENTAGE DECIMAL(5,2) NOT NULL\n" +
                ")");
                ps.execute();
            }
            
            rs = meta.getTables(null, null, "LECTURERS", null);
            if(!rs.next()){
                ps = con.prepareStatement("CREATE TABLE LECTURERS (\n" +
                "LECTURER_ID VARCHAR(20) PRIMARY KEY NOT NULL,\n" +
                "TITLE VARCHAR(20) NOT NULL,\n" +
                "SURNAME VARCHAR(20) NOT NULL,\n" +
                "OTHER_NAMES VARCHAR(50) NOT NULL,\n" +
                "EMAIL VARCHAR(50) NOT NULL,\n" +
                "PASSWORD VARCHAR(20) NOT NULL,\n" +
                "PHONE VARCHAR(30) NOT NULL\n" +
                ")");
                ps.execute();
                
                ps = con.prepareStatement("INSERT INTO LECTURERS VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, "admin");
                ps.setString(2, "admin");
                ps.setString(3, "admin");
                ps.setString(4, "admin");
                ps.setString(5, "admin");
                ps.setString(6, "admin");
                ps.setString(7, "admin");
                ps.executeUpdate();
            }
            
            rs = meta.getTables(null, null, "STUDENTS", null);
            if(!rs.next()){
                ps = con.prepareStatement("CREATE TABLE STUDENTS (\n" +
                "MATRIC_NO VARCHAR(20) PRIMARY KEY NOT NULL,\n" +
                "SURNAME VARCHAR(20) NOT NULL,\n" +
                "OTHER_NAMES VARCHAR(50) NOT NULL,\n" +
                "DATE_OF_BIRTH DATE NOT NULL,\n" +
                "GENDER VARCHAR(10) NOT NULL,\n" +
                "EMAIL VARCHAR(50) NOT NULL,\n" +
                "PHONE VARCHAR(30) NOT NULL,\n" +
                "FINGER_PRINT BLOB NOT NULL\n" +
                ")");
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            try {
                if(ps!=null)
                    ps.close();
                con.close();
            } catch (SQLException e) {}
        }
    }
    
    public String getLocalDir(){
        String userDir = System.getProperty("user.home")+"/Att_Mgr/";
        return userDir;
    }
}
