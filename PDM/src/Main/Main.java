package Main;

import Schedule.ListSchedule;
import Station.ListStation;
import Track.ListTrack;
import Train.ListTrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Main {

    private static JFrame frame;
    private static JPanel table;
    private static GridLayout gridLayout;
    private static Database database;

    public static void main(String[] args) throws SQLException {
        database = new Database();

        frame = new JFrame("Railway System");
        frame.setSize(1280, 720);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#EBFFD8"));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 10, 10, 10));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel title = new JLabel("Railway System");
        title.setForeground(Color.decode("#012030"));
        title.setFont(new Font(null, Font.BOLD, 35));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        JButton listTrain = GUI.Button("List Train");
        listTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListTrain(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(listTrain);

        JButton listStation = GUI.Button("List Station");
        listStation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListStation(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(listStation);

        JButton listTrack = GUI.Button("List Track");
        listTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListTrack(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(listTrack);

        JButton listSchedule = GUI.Button("List Schedule");
        listSchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListSchedule(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(listSchedule);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
