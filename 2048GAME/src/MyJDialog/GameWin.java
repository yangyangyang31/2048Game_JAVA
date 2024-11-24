package MyJDialog;

import Gameop.Game;
import Main.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameWin extends JDialog {
    public GameWin()
    {
    }
    JFrame frame;
    public GameWin(JFrame frame) {
       this.frame = frame;
        setTitle("游戏结束");
        setBounds(600,300,350,150);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JButton ok =new JButton("是");
        ok.setBounds(75,50,80,30);

        JButton no=new JButton("否");
        no.setBounds(175,50,75,30);

        JLabel test=new JLabel("你成功得到了2048，是否再来一局");
        test.setBounds(70,10,200,30);

        add(ok);
        add(no);
        add(test);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.init();
                frame.repaint();
                dispose();

            }
        });

        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
