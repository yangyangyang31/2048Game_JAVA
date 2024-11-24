package MyJPanel;

import GetRes.getRes;
import MyFrame.GameFrame;

import javax.swing.*;
import java.awt.*;

public class RankPanel extends JPanel {
    public RankPanel() {
        setLayout(null);
        setBounds(0, 0, 1000, 1000);
        setVisible(true);
        //setBackground(Color.gray);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int n=GameFrame.rank.size();
        g.setFont(new Font("FACE_PROPORTIONAL",1,20));

        setPreferredSize(new Dimension(400,40*(n+1)+130));

        ImageIcon bk= GamePanel.getim(getRes.ulrankbk,420,40*(n+1)+120);

        bk.paintIcon(this,g,0,0);

        g.drawString("排名",20,40+100);
        g.drawString("用户名",100,40+100);
        g.drawString("得分",320,40+100);

        for(int i=0;i<n;i++)
        {
            //g.drawRect(0,(i+1)*40+10,450,40);
            switch (i) {
                case 0:
                    g.setColor(new Color(255,196,18));
                    break;
                case 1:
                    g.setColor(new Color(255,137,41));
                    break;
                case 2:
                    g.setColor(new Color(255,91,30));
                    break;
                default:
                    g.setColor(new Color(36,176,141));
            }
            g.fillRect(20,(i+1)*40+10+100,380,35);
            g.setColor(Color.black);
            g.drawString(""+(i+1),25,(i+2)*40+100);
            g.drawString(GameFrame.rank.get(i).n,105,(i+2)*40+100);
            g.drawString(""+GameFrame.rank.get(i).s,320,(i+2)*40+100);

        }
    }
}
