package MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import Gameop.Game;
import GetRes.*;
import MyFrame.DesignFrame;

public class GamePanel extends JPanel {
    public GamePanel(JFrame frame) {
        setBounds(0,0,400+100,650);
        setBackground(new Color(250,248,239));
        setLayout(null);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    public static ImageIcon getim(URL tmp,int x,int y)
    {
        ImageIcon icon = new ImageIcon(tmp);

        Image newImage = icon.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT);
        ImageIcon  imageIconNew= new ImageIcon(newImage);
        return imageIconNew;
    }

    public void drawGame(Graphics g)
    {

        Font f=g.getFont();
        ImageIcon im2048bk=getim(getRes.ul2048bc,150,75);            //2048大字图
        im2048bk.paintIcon(this,g,15,15);

        g.setColor(new Color(187, 173, 160));
        g.fillRect(250-75,25,100,70);           //得分框

        g.fillRect(250-75+110,25,100,70);        //历史最高框


        g.setColor(new Color(255, 255, 255));

        g.setFont(new Font("FACE_PROPORTIONAL",1,20));
        g.drawString("得分:",275-75,55);

        g.drawString("历史最高:",275-75+92,55);

        String nscore=Integer.toString(Game.GetScore());
        int le=nscore.length();
        int xx=50-5*le;
        g.drawString(nscore,250+xx-75,80);

        String lsscore=Integer.toString(DesignFrame.login_Score);
        int l=lsscore.length();
        int yy=50-5*l;
        g.drawString(lsscore,250+xx-75+95,80);

        for(int i=0;i<4;i++)             //将图片加入画板
        {
            for (int j = 0; j < 4; j++) {
                URL tmp = null;
                int x = i * 100+50, y = j * 100+150, t = Game.map[i][j];
                tmp = switch (t) {
                    case 0 -> getRes.ul0;
                    case 2 -> getRes.ul2;
                    case 4 -> getRes.ul4;
                    case 8 -> getRes.ul8;
                    case 16 -> getRes.ul16;
                    case 32 -> getRes.ul32;
                    case 64 -> getRes.ul64;
                    case 128 -> getRes.ul128;
                    case 256 -> getRes.ul256;
                    case 512 -> getRes.ul512;
                    case 1024 -> getRes.ul1024;
                    case 2048 -> getRes.ul2048;
                    default -> tmp;
                };

                ImageIcon icon = getim(tmp,100,100);

                icon.paintIcon(this, g, x, y);
                //g.drawImage(imageIconNew.getImage(), x, y, null);

            }
        }
        g.setFont(f);
    }
}
