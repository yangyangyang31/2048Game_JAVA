package MyFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import Gameop.Game;
import MyJPanel.GamePanel;
import MyJDialog.*;
import RankNode.*;

public class GameFrame extends JFrame{
    public static Vector<RankNode> rank;

    Game game;
    DesignFrame designFrame;
    public GameFrame(){

    }

    public GameFrame(DesignFrame designFrame){
        //super("2048Game");
        JFrame frame = new JFrame();
        frame.setTitle("2048Game");
        this.designFrame = designFrame;
        GetRank();
        game=new Game();
        Game.Rand_new();

        setBounds(400,50,413+100,500+87+50);

        JButton button = new JButton("排行");
        button.setBounds(400,25,75,70);
        button.setBackground(new Color(187, 173, 160));
        add(button);

        add(new GamePanel(this));

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);          //设置焦点事件

        setVisible(true);
        setResizable(false);      //固定JFrame大小

        addKeyListener(new KeyAdapter() {                     //对键盘响应
            public void keyPressed(KeyEvent e){
                int key=e.getKeyCode();
                updatemap(key);
            }
        });

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               new RankDia(frame);

            }
        });

        button.addKeyListener(new KeyAdapter() {                     //对键盘响应
            public void keyPressed(KeyEvent e){
                int key=e.getKeyCode();
                updatemap(key);
            }
        });
    }

    public void GetRank()
    {
        rank=designFrame.myOpr.selectall();
        rank.sort(new Comparator<RankNode>() {
            //重写compare方法，最好加注解，不加也没事
            public int compare(RankNode a, RankNode b) {
                //返回值>0交换
                return b.s - a.s;
            }
        });
    }

    public void updatemap(int key)
    {
        int k=game.move((char)key);
        if(k!=-1&&k!=-10&&k!=10)
            Game.Rand_new();
        if(k==-10)    //游戏失败 弹窗询问是否重开
        {
            if(Game.GetScore()>designFrame.login_Score)
            {
                String mxsorce=Integer.toString(Game.GetScore());
                designFrame.myOpr.updatadata(designFrame.login_Name,mxsorce);
            }
            designFrame.login_Score=Game.GetScore();
            GetRank();
            new GameOver(this);
        }
        else             //游戏成功
        {
            if(Game.GetScore()>designFrame.login_Score)
            {
                String mxsorce=Integer.toString(Game.GetScore());
                designFrame.myOpr.updatadata(designFrame.login_Name,mxsorce);
            }
            new GameWin();
        }
        repaint();
    }

}
