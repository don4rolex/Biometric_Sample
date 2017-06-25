/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Config.AppConfig;
import Model.Lecturer;
import Model.Student;
import View.Attendance;
import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Andrew
 */
public class AttendanceDaoImpl implements AttendanceDao{
    
    private AppConfig appConfig = new AppConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    
    private List <Student>list = new ArrayList();
    
    public AttendanceDaoImpl(){
        con = appConfig.con();
        try {
            ps = con.prepareStatement("SELECT MATRIC_NO, SURNAME, OTHER_NAMES, GENDER, FINGER_PRINT FROM students");
            rs = ps.executeQuery();
            while(rs.next()){
                String matricNo = rs.getString(1);
                String surname = rs.getString(2);
                String otherNames = rs.getString(3);
                String gender = rs.getString(4);
                Blob blob = rs.getBlob(5);
                int blobLength = (int)blob.length();  
                byte [] fingerPrint = blob.getBytes(1, blobLength);
                
                Student student = new Student();
                student.setMatricNo(matricNo);
                student.setSurname(surname);
                student.setOtherNames(otherNames);
                student.setGender(gender);
                student.setFingerPrintData(fingerPrint);
                list.add(student);
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
    
    @Override
    public void startScanner(Attendance attendance) {
        init(attendance);
        capturer.startCapture();
    }

    @Override
    public void stopScanner() {
        capturer.stopCapture();
    }
    
    @Override
    public void recordAttendance(String date, String courseCode, String lecturerId, String matricNo){
        con = appConfig.con();
        try {
            ps = con.prepareStatement("SELECT * FROM attendance WHERE DATE = ? AND COURSE_CODE = ? AND LECTURER_ID = ? AND MATRIC_NO = ?");
            ps.setString(1, date);
            ps.setString(2, courseCode);
            ps.setString(3,lecturerId );
            ps.setString(4, matricNo);
            rs = ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Attendance already recorded");
            }else{
                ps = con.prepareStatement("INSERT INTO attendance VALUES (?,?,?,?)");
                ps.setString(1, date);
                ps.setString(2, courseCode);
                ps.setString(3, lecturerId);
                ps.setString(4, matricNo);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Attendance recorded");
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
    
    private void init(Attendance attendance) {
        
        capturer.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent event) {
                SwingUtilities.invokeLater(() -> {
                    
                        attendance.makeReport("Fingerprint sample was captured.");
                        process(attendance.getPictureLabel(), event.getSample());
                        
                        DPFPFeatureSet features = extractFeatures(event.getSample(), DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
                        DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
                        matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
                        
                        boolean verified = false;
                        for(Student student : list){
                            DPFPTemplate template = DPFPGlobal.getTemplateFactory().createTemplate(student.getFingerPrintData());
                            DPFPVerificationResult result = matcher.verify(features, template);
                            verified = result.isVerified();
                            if(verified){
                                attendance.setMatricNo(student.getMatricNo());
                                attendance.setSurname(student.getSurname());
                                attendance.setOtherNames(student.getOtherNames());
                                attendance.setGender(student.getGender());
                                attendance.setImage(appConfig.getLocalDir()+"/Images/"+student.getMatricNo().replaceAll("\\.", "").replaceAll("/", "")+".png");
                                attendance.showDetails(verified);
                                recordAttendance(attendance.getDate(), attendance.getCourseCode(), attendance.getLecturerId(), attendance.getMatricNo());
                                break;
                            }
                        }
                        if(!verified){
                            attendance.showDetails(verified);
                            JOptionPane.showMessageDialog(null, "User details not found");
                        }
                });
            }
        });

        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    attendance.makeReport("Fingerprint reader was connected.");
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    enrollment.clear();
                    attendance.makeReport("Fingerprint reader was disconnected.");
                });
            }
        });
        
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    attendance.makeReport("Fingerprint reader was touched.");
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    attendance.makeReport("Finger was removed from the reader.");
                });
            }
        });
        
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                        attendance.makeReport("Quality of the fingerprint sample is good.");
                    } else {
                        attendance.makeReport("Quality of the fingerprint sample is poor.");
                    }
                });
            }
        });
    }

    private void process(JLabel picture, DPFPSample sample) {
        drawPicture(picture, convertSampleToBitmap(sample));
    }

    private void drawPicture(JLabel picture, Image image) {
        picture.setIcon(new ImageIcon(image.getScaledInstance(248, 248, Image.SCALE_DEFAULT)));
    }

    private Image convertSampleToBitmap(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    private DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }
}
