package Sequence;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ListSequence {
    private JComboBox<String> number, deptHH, deptMM, arriveHH, arriveMM;
    private JTextField station;

    public ListSequence(JFrame parent, Database database, String schedule) throws SQLException {
        String[] HH = new String[25];
        HH[0] = "HH";
        for (int i = 0; i < 24; i++) {
            HH[i+1] = String.format("%02d", i);
        }

        String[] mm = new String[61];
        mm[0] = "mm";
        for (int i = 0; i < 60; i++) {
            mm[i+1] = String.format("%02d", i);
        }

        JFrame frame = new JFrame("List of Schedule");
        frame.setSize(750, 400);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("Sequence:"));
        number = GUI.JComboBox(SequenceDatabase.getSequencesNums(schedule, database));
        panel.add(number);



        panel.add(GUI.Label("Station:"));
        station = GUI.TextField();
        panel.add(station);

        panel.add(GUI.Label("Arrival Time:"));
        JPanel arrive = new JPanel(new GridLayout(1, 2, 10, 10));
        arrive.setBackground(null);
        arriveHH = GUI.JComboBox(HH);
        arriveMM = GUI.JComboBox(mm);
        arrive.add(arriveHH);
        arrive.add(arriveMM);
        panel.add(arrive);

        panel.add(GUI.Label("Departure Time:"));
        JPanel dept = new JPanel(new GridLayout(1, 2, 10, 10));
        dept.setBackground(null);
        deptHH = GUI.JComboBox(HH);
        deptMM = GUI.JComboBox(mm);
        dept.add(deptHH);
        dept.add(deptMM);
        panel.add(dept);

        number.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Sequence sequence = SequenceDatabase.getSequenceNumber(schedule, number.getSelectedItem().toString(), database);
                    station.setText(sequence.getStation_name());
                    deptHH.setSelectedItem(sequence.getDeptHour());
                    deptMM.setSelectedItem(sequence.getDeptMinute());
                    arriveHH.setSelectedItem(sequence.getArrivalHour());
                    arriveMM.setSelectedItem(sequence.getArrivalMinute());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex);
                    frame.dispose();                    }
            }
        });

        if (number.getSelectedItem() != null) {
            try {
                Sequence sequence = SequenceDatabase.getSequenceNumber(schedule, number.getSelectedItem().toString(), database);
                station.setText(sequence.getStation_name());
                deptHH.setSelectedItem(sequence.getDeptHour());
                deptMM.setSelectedItem(sequence.getDeptMinute());
                arriveHH.setSelectedItem(sequence.getArrivalHour());
                arriveMM.setSelectedItem(sequence.getArrivalMinute());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, ex);
                frame.dispose();
            }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
