/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Course;
import Model.Lecturer;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface CourseDao {
    
    public int createCourse(Course course);
    
    public List <Lecturer> getLecturers();
    
    public List<Course> getCourses();
    
    public int updateCourses(List <Course> courses);
    
    public int deleteCourses(List <Course> courses);
}
