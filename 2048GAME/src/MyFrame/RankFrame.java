package MyFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RankFrame extends JFrame {
    public RankFrame(JFrame frame) {
        setTitle("排名");
        setBounds(100, 100, 450, 300);

        setFocusable(false);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.repaint();
                dispose();
            }
        });
    }
}
