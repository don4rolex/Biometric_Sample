/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import View.Attendance;

/**
 *
 * @author Andrew
 */
public interface AttendanceDao {
    
    public void startScanner(Attendance attendance);
    
    public void stopScanner();
    
    public void recordAttendance(String date, String courseCode, String lecturerId, String matricNo);
}
