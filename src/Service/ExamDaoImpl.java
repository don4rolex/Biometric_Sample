/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Config.AppConfig;
import Model.Student;
import View.Exams;
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
public class ExamDaoImpl implements ExamDao{
    
    private AppConfig appConfig = new AppConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    
    private List <Student>list = new ArrayList();
    
    public ExamDaoImpl(){
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
    public void startScanner(Exams exam) {
        init(exam);
        capturer.startCapture();
    }

    @Override
    public void stopScanner() {
        capturer.stopCapture();
    }
    
    @Override
    public void validate(Exams exam, String courseCode, String matricNo){
        con = appConfig.con();
        try {
            ps = con.prepareStatement("SELECT DISTINCT a.TOTAL_CLASSES, a.PERCENTAGE, "
                    + "(SELECT COUNT(*) FROM attendance WHERE COURSE_CODE = ? AND MATRIC_NO = ?) as COUNT "
                    + "FROM courses a JOIN attendance b ON b.COURSE_CODE = a.COURSE_CODE "
                    + "WHERE a.COURSE_CODE = ? AND b.MATRIC_NO = ?");
//            ps = con.prepareStatement("SELECT DISTINCT a.TOTAL_CLASSES, a.PERCENTAGE, COUNT(MATRIC_NO) "
//                    + "FROM courses a JOIN attendance b ON b.COURSE_CODE = a.COURSE_CODE "
//                    + "WHERE a.COURSE_CODE = ? AND b.MATRIC_NO = ?");
            ps.setString(1, courseCode);
            ps.setString(2, matricNo);
            ps.setString(3, courseCode);
            ps.setString(4, matricNo);
            rs = ps.executeQuery();
            if(rs.next()){
                double totalClasses = rs.getInt(1);
                double percentageRequired = rs.getDouble(2);
                double totalAttendance = rs.getInt(3);
                double percentageAchieved = (totalAttendance/totalClasses)*100;
                
                exam.setTotalClasses(String.valueOf(totalClasses));
                exam.setPercentageRequired(String.format("%.2f",percentageRequired));
                exam.setTotalAttendance(String.valueOf(totalAttendance));
                exam.setPercentage(String.format("%.2f",percentageAchieved));
                
                if(percentageAchieved<percentageRequired){
                    JOptionPane.showMessageDialog(null, "Student not qualified for exam");
                }else{
                    JOptionPane.showMessageDialog(null, "Student qualified for exam");
                }
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
    
    private void init(Exams exam) {
        
        capturer.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent event) {
                SwingUtilities.invokeLater(() -> {
                    
                        exam.makeReport("Fingerprint sample was captured.");
                        process(exam.getPictureLabel(), event.getSample());
                        
                        DPFPFeatureSet features = extractFeatures(event.getSample(), DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
                        DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
                        matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
                        
                        boolean verified = false;
                        for(Student student : list){
                            DPFPTemplate template = DPFPGlobal.getTemplateFactory().createTemplate(student.getFingerPrintData());
                            DPFPVerificationResult result = matcher.verify(features, template);
                            verified = result.isVerified();
                            if(verified){
                                exam.setMatricNo(student.getMatricNo());
                                exam.setSurname(student.getSurname());
                                exam.setOtherNames(student.getOtherNames());
                                exam.setGender(student.getGender());
                                exam.setImage(appConfig.getLocalDir()+"/Images/"+student.getMatricNo().replaceAll("\\.", "").replaceAll("/", "")+".png");
                                exam.showDetails(verified);
                                validate(exam, exam.getCourseCode(), exam.getMatricNo());
                                break;
                            }
                        }
                        if(!verified){
                            exam.showDetails(verified);
                            JOptionPane.showMessageDialog(null, "User details not found");
                        }
                });
            }
        });

        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    exam.makeReport("Fingerprint reader was connected.");
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    enrollment.clear();
                    exam.makeReport("Fingerprint reader was disconnected.");
                });
            }
        });
        
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    exam.makeReport("Fingerprint reader was touched.");
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    exam.makeReport("Finger was removed from the reader.");
                });
            }
        });
        
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                        exam.makeReport("Quality of the fingerprint sample is good.");
                    } else {
                        exam.makeReport("Quality of the fingerprint sample is poor.");
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
