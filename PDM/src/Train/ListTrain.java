package Train;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ListTrain {

    private JComboBox<String> id;
    private JTextField name;

    public ListTrain(JFrame parent, Database db) throws SQLException {
        JFrame frame = new JFrame("List Train");
        frame.setSize(500, 200);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID"));
        id = GUI.JComboBox(TrainDatabase.getTrainIDs(db));
        panel.add(id);

        panel.add(GUI.Label("Name"));
        name = GUI.TextField();
        panel.add(name);

        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Train t = TrainDatabase.getTrainByID(id.getSelectedItem().toString(), db);
                    name.setText(t.getName());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    frame.dispose();
                }
            }
        });

        if (id.getSelectedItem() != null) {
            try {
                Train t = TrainDatabase.getTrainByID(id.getSelectedItem().toString(), db);
                name.setText(t.getName());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, ex.toString());
                frame.dispose();
            }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
