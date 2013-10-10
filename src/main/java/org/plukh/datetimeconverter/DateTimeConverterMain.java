package org.plukh.datetimeconverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class DateTimeConverterMain {
    private static final Logger log = LogManager.getLogger(DateTimeConverterMain.class);

    private static JFrame mainFrame;
    private static DateTimeConverterForm mainForm;

    public static void main(String[] args) {
        log.info("DateTime Converter starting up..");

        initGui();
    }

    private static void initGui() {
        log.debug("Initializing GUI...");

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    //Init look and feel
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                            UnsupportedLookAndFeelException e) {
                        log.error("Unable to set system look and feel", e);
                    }

                    //Init main frame and main panel
                    mainFrame = new JFrame("DateTime Converter");
                    mainForm = new DateTimeConverterForm();

                    //Set up content pane and show main frame
                    mainFrame.setContentPane(mainForm.getMainPanel());
                    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                    mainFrame.pack();

                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            log.fatal("Error initializing GUI", e);
            System.exit(1);
        }

        log.info("GUI initialized");
    }
}
