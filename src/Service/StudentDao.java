/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Student;
import View.StudentEnrollment;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface StudentDao {
    
    public void startScanner(StudentEnrollment enrollmentView);
    
    public void stopScanner();
    
    public int enrollStudent(Student student);
    
    public List<Student> getStudents();
    
    public int updateStudents(List <Student> students);
    
    public int deleteStudents(List <Student> students);
}
