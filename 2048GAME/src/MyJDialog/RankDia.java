package MyJDialog;



import MyJPanel.RankPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RankDia extends JDialog {
    public RankDia(JFrame frame) {
        setTitle("排名");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(null);

        JScrollPane pane = new JScrollPane(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        //pane.setBounds(0,0,1000,1000);
        setContentPane(pane);
        pane.setViewportView(new RankPanel());

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.repaint();
                dispose();
            }
        });
    }

}
