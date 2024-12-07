package Schedule;

import Main.Database;
import Main.GUI;
import Sequence.ListSequence;
import Sequence.SequenceDatabase;
import Track.Track;
import Train.TrainDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ListSchedule {

    private JComboBox<String> train, deptHH, deptMM, arriveHH, arriveMM;
    private JTextField start, destination;

    public ListSchedule(JFrame parent, Database database) throws SQLException, ClassNotFoundException {
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
        frame.setSize(750, 600);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(6, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("Train:"));
        train = GUI.JComboBox(TrainDatabase.getTrainIDs(database) );
        panel.add(train);

        panel.add(GUI.Label("Start:"));
        start = GUI.TextField();
        panel.add(start);

        panel.add(GUI.Label("Destination:"));
        destination = GUI.TextField();
        panel.add(destination);

        panel.add(GUI.Label("Departure Time:"));
        JPanel dept = new JPanel(new GridLayout(1, 2, 10, 10));
        dept.setBackground(null);
        deptHH = GUI.JComboBox(HH);
        deptMM = GUI.JComboBox(mm);
        dept.add(deptHH);
        dept.add(deptMM);
        panel.add(dept);

        panel.add(GUI.Label("Arrival Time:"));
        JPanel arrive = new JPanel(new GridLayout(1, 2, 10, 10));
        arrive.setBackground(null);
        arriveHH = GUI.JComboBox(HH);
        arriveMM = GUI.JComboBox(mm);
        arrive.add(arriveHH);
        arrive.add(arriveMM);
        panel.add(arrive);

        panel.add(GUI.Label("Sequence:"));
        JButton sequence = GUI.Button("Sequence");
        sequence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListSequence(frame, database, ScheduleDatabase.getScheduleID(train.getSelectedItem().toString(), database));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(sequence);

        train.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Schedule schedule = ScheduleDatabase.getSchedule(train.getSelectedItem().toString(), database);
                    start.setText(schedule.getStartStation());
                    destination.setText(schedule.getDestinationStation());
                    deptHH.setSelectedItem(schedule.getDeptHour());
                    deptMM.setSelectedItem(schedule.getDeptMinute());
                    arriveHH.setSelectedItem(schedule.getArrivalHour());
                    arriveMM.setSelectedItem(schedule.getArrivalMinute());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex);
                    frame.dispose();
                }
            }
        });

        if (train.getSelectedItem() != null) {
            try {
                Schedule schedule = ScheduleDatabase.getSchedule(train.getSelectedItem().toString(), database);
                start.setText(schedule.getStartStation());
                destination.setText(schedule.getDestinationStation());
                deptHH.setSelectedItem(schedule.getDeptHour());
                deptMM.setSelectedItem(schedule.getDeptMinute());
                arriveHH.setSelectedItem(schedule.getArrivalHour());
                arriveMM.setSelectedItem(schedule.getArrivalMinute());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, ex);
                frame.dispose();
            }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
