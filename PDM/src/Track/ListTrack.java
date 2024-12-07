package Track;

import Main.Database;
import Main.GUI;
import Station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ListTrack {
    private JComboBox<String> id;
    private JTextField StartStationName;
    private JTextField EndStationName;

    public ListTrack(JFrame parent, Database db) throws SQLException {
        JFrame frame = new JFrame("List Track");
        frame.setSize(500, 300);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID"));
        id = GUI.JComboBox(TrackDatabase.getTrackIDs(db));
        panel.add(id);

        panel.add(GUI.Label("Start Station"));
        StartStationName = GUI.TextField();
        panel.add(StartStationName);

        panel.add(GUI.Label("End Station"));
        EndStationName = GUI.TextField();
        panel.add(EndStationName);

        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Track track = TrackDatabase.getTrackByID(id.getSelectedItem().toString(), db);
                    StartStationName.setText(track.getStartStationName());
                    EndStationName.setText(track.getEndStationName());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    frame.dispose();
                }
            }
        });

        if (id.getSelectedItem() != null) {
            try {
                Track track = TrackDatabase.getTrackByID(id.getSelectedItem().toString(), db);
                StartStationName.setText(track.getStartStationName());
                EndStationName.setText(track.getEndStationName());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, ex.toString());
                frame.dispose();
            }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
