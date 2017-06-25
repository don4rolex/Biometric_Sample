/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Lecturer;
import View.LoginPage;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface LecturerDao {
    
    public int enrollLecturer(Lecturer lecturer);
    
    public void login(LoginPage loginPage, String lecturerId, String password);
    
    public List<Lecturer> getLecturers();
    
    public int updateLecturers(List <Lecturer> lecturers);
    
    public int deleteLecturers(List <Lecturer> lecturers);
}
