package org.plukh.datetimeconverter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateTimeConverterForm {
    private JTextField millisTextField;
    private JTextField stringTextField;
    private JButton convertFromMillis;
    private JButton convertFromString;
    private JCheckBox useUtcCheckBox;
    private JPanel mainPanel;

    public DateTimeConverterForm() {
        convertFromMillis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConvertFromMillis();
            }
        });
        convertFromString.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConvertFromString();
            }
        });
    }

    private void onConvertFromString() {
        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeParser();
        if (useUtcCheckBox.isSelected()) formatter = formatter.withZoneUTC();

        try {
            millisTextField.setText(String.valueOf(formatter.parseMillis(stringTextField.getText())));
        } catch (IllegalArgumentException e) {
            showConversionError(e);
        }
    }

    private void onConvertFromMillis() {
        try {
            final long millis = Long.valueOf(millisTextField.getText());
            DateTime dt = useUtcCheckBox.isSelected() ? new DateTime(millis, DateTimeZone.UTC) : new DateTime(millis);
            stringTextField.setText(ISODateTimeFormat.dateTime().print(dt));
        } catch (NumberFormatException e) {
            showConversionError(e);
        }
    }

    private void showConversionError(Throwable t) {
        JOptionPane.showMessageDialog(mainPanel, "Conversion error: " + t.getMessage(), "Conversion error", JOptionPane.ERROR_MESSAGE);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
