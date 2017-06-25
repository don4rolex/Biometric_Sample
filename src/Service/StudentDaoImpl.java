/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Config.AppConfig;
import Model.Student;
import View.StudentEnrollment;
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
import com.digitalpersona.onetouch.processing.DPFPTemplateStatus;
import java.awt.Image;
import java.io.File;
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
public class StudentDaoImpl implements StudentDao {

    private AppConfig appConfig = new AppConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    @Override
    public void startScanner(StudentEnrollment enrollmentView) {
        init(enrollmentView);
        capturer.startCapture();
    }

    @Override
    public void stopScanner() {
        capturer.stopCapture();
    }

    @Override
    public int enrollStudent(Student student) {
        int n = 0;
        List <String>errorList = new ArrayList();
        
        String matricNo = student.getMatricNo();
        String surname = student.getSurname();
        String otherNames = student.getOtherNames();
        String dateOfBirth = student.getDateOfBirth();
        String gender = student.getGender();
        String email = student.getEmail();
        String phone = student.getPhone();
        
        if(matricNo.isEmpty()){
            errorList.add("Empty Matric No. field");
        }
        
        if(surname.isEmpty()){
            errorList.add("Empty Surname field");
        }
        
        if(otherNames.isEmpty()){
            errorList.add("Empty Other Names field");
        }
        
        if(dateOfBirth.isEmpty()){
            errorList.add("Empty Date of Birth field");
        }
        
        if(gender.equals("Please Select")){
            errorList.add("Empty Gender field");
        }
        
        if(email.isEmpty()){
            errorList.add("Empty Email field");
        }
        
        if(phone.isEmpty()){
            errorList.add("Empty Phone field");
        }
        
        File file = new File(appConfig.getLocalDir()+"/Images/"+matricNo.replaceAll("\\.", "").replaceAll("/", "")+".png");
        if(!file.exists()){
            errorList.add("Image not captured");
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
                DPFPTemplate template = enrollment.getTemplate();
                
                ps = con.prepareStatement("INSERT INTO students VALUES (?,?,?,?,?,?,?,?)");
                ps.setString(1, matricNo);
                ps.setString(2, surname);
                ps.setString(3, otherNames);
                ps.setString(4, dateOfBirth);
                ps.setString(5, gender);
                ps.setString(6, email);
                ps.setString(7, phone);
                ps.setBytes(8, template.serialize());
                
                n = ps.executeUpdate();
                if(n>0){
                    enrollment.clear();
                }
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
    public List<Student>getStudents(){
        List <Student>students = new ArrayList();
        
        try {
            con = appConfig.con();
            ps = con.prepareStatement("SELECT * FROM students");
            rs = ps.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setMatricNo(rs.getString(1));
                student.setSurname(rs.getString(2));
                student.setOtherNames(rs.getString(3));
                student.setDateOfBirth(rs.getString(4));
                student.setGender(rs.getString(5));
                student.setEmail(rs.getString(6));
                student.setPhone(rs.getString(7));
                students.add(student);
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
        return students;
    }
    
    @Override
    public int updateStudents(List <Student> students){
        int n[] = {};
        List <String>errorList = new ArrayList();
        
        if(!students.isEmpty()){
            try {
                con = appConfig.con();
                ps = con.prepareStatement("UPDATE students SET SURNAME = ?, OTHER_NAMES = ?, DATE_OF_BIRTH = ?, GENDER = ?, "
                        + "EMAIL = ?, PHONE = ? WHERE MATRIC_NO = ?");
                int i = 1;
                for(Student student : students){
                    
                    String matricNo = student.getMatricNo();
                    String surname = student.getSurname();
                    String otherNames = student.getOtherNames();
                    String dateOfBirth = student.getDateOfBirth();
                    String gender = student.getGender();
                    String email = student.getEmail();
                    String phone = student.getPhone();
                    
                    if(matricNo.isEmpty() || surname.isEmpty() || otherNames.isEmpty() || dateOfBirth.isEmpty() || gender.isEmpty()
                            || email.isEmpty() || phone.isEmpty()){
                        errorList.add("Incomplete cells on row "+i);
                    }else{
                        ps.setString(1, surname);
                        ps.setString(2, otherNames);
                        ps.setString(3, dateOfBirth);
                        ps.setString(4, gender);
                        ps.setString(5, email);
                        ps.setString(6, phone);
                        ps.setString(7, matricNo);
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
    public int deleteStudents(List <Student> students){
        int n[] = {};
        if(!students.isEmpty()){
            try {
                con = appConfig.con();
                ps = con.prepareStatement("DELETE FROM students WHERE MATRIC_NO = ?");
                for(Student student : students){
                    String matricNo = student.getMatricNo();
                    ps.setString(1, matricNo);
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

    private void init(StudentEnrollment enrollmentView) {
        
        capturer.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent event) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        enrollmentView.makeReport("Fingerprint sample was captured.");
                        process(enrollmentView.getPictureLabel(), event.getSample());
                        DPFPFeatureSet features = extractFeatures(event.getSample(), DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
                        if (features != null) {
                            enrollment.addFeatures(features);
                            DPFPTemplateStatus status = enrollment.getTemplateStatus();
                            if (status == DPFPTemplateStatus.TEMPLATE_STATUS_READY) {
                                stopScanner();
                                enrollmentView.setPrompt("Fingerprint data saved.");
                                enrollmentView.enableSaveButton(true);
                                JOptionPane.showMessageDialog(null, "Fingerprint data saved.");
                            } else {
                                enrollmentView.setPrompt("Scan same fingerprint again.");
                                enrollmentView.enableSaveButton(false);
                            }
                        }
                    } catch (DPFPImageQualityException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    enrollmentView.makeReport("Fingerprint reader was connected.");
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    enrollment.clear();
                    enrollmentView.makeReport("Fingerprint reader was disconnected.");
                });
            }
        });
        
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    enrollmentView.makeReport("Fingerprint reader was touched.");
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    enrollmentView.makeReport("Finger was removed from the reader.");
                });
            }
        });
        
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                        enrollmentView.makeReport("Quality of the fingerprint sample is good.");
                    } else {
                        enrollmentView.makeReport("Quality of the fingerprint sample is poor.");
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
