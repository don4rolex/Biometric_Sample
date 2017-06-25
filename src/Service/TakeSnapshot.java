/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Andrew
 */
import Config.AppConfig;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class TakeSnapshot extends JDialog {

    private String matricNo;
    private AppConfig appConfig = new AppConfig();
    private Executor executor = Executors.newSingleThreadExecutor();
//    private Dimension size = WebcamResolution.VGA.getSize();
    private Dimension size = new Dimension(320, 240);

    private Webcam webcam = Webcam.getDefault();
    private WebcamPanel panel = new WebcamPanel(webcam, size, false);

    private JButton btSnapMe = new JButton(new SnapMeAction());
    private JButton btStart = new JButton(new StartAction());
    private JButton btStop = new JButton(new StopAction());

    private class SnapMeAction extends AbstractAction {

        public SnapMeAction() {
            super("Capture");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File file = new File(appConfig.getLocalDir() + "/Images/" + matricNo.replaceAll("\\.", "").replaceAll("/", "") + ".png");
                if (!file.exists()) {
                    file.mkdirs();
                }
                ImageIO.write(webcam.getImage(), "PNG", file);
                JOptionPane.showMessageDialog(null, "Image Captured");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class StartAction extends AbstractAction implements Runnable {

        public StartAction() {
            super("Start");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            btStart.setEnabled(false);
            btStart.setVisible(false);
            btSnapMe.setEnabled(true);
            btStart.setVisible(true);

            // remember to start panel asynchronously - otherwise GUI will be
            // blocked while OS is opening webcam HW (will have to wait for
            // webcam to be ready) and this causes GUI to hang, stop responding
            // and repainting
            executor.execute(this);
        }

        @Override
        public void run() {
            btStop.setEnabled(true);
            panel.start();
        }
    }

    private class StopAction extends AbstractAction {

        public StopAction() {
            super("Stop");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            btStart.setEnabled(true);
            btSnapMe.setEnabled(false);
            btStop.setEnabled(false);

            panel.stop();

        }
    }

    public TakeSnapshot(Frame parent, boolean modal, String matricNo) {
        super(parent, modal);
        setTitle("Capture Image");
        getContentPane().setBackground(Color.WHITE);
        
        this.matricNo = matricNo;

        btStart.setBackground(new java.awt.Color(0, 102, 51));
        btStart.setForeground(Color.WHITE);
        btStop.setBackground(Color.RED);
        btStop.setForeground(Color.WHITE);
        btSnapMe.setBackground(Color.BLUE);
        btSnapMe.setForeground(Color.WHITE);
        
        btSnapMe.setEnabled(false);
        btStop.setEnabled(false);

        webcam.setViewSize(size);
//            panel.setFPSDisplayed(true);
        panel.setFillArea(true);
        
        setLayout(new FlowLayout());
        add(panel);
        add(btSnapMe);
        add(btStart);
        add(btStop);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                panel.stop();
            }
        });
    }
}