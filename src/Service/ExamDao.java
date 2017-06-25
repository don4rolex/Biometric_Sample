/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import View.Exams;

/**
 *
 * @author Andrew
 */
public interface ExamDao {
    
    public void startScanner(Exams exams);
    
    public void stopScanner();
    
    public void validate(Exams exam, String courseCode, String matricNo);
}
