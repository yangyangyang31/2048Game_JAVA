package MyFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import java.util.*;
import MySql.*;

public class DesignFrame implements MouseListener {
    public static String login_Name;
    public static int login_Score;
    public JFrame frame=new JFrame("2048登录窗口");
    private JLabel lbl_usn=new JLabel("用户名：");
    private JTextField txt_username=new JTextField();
    private JLabel lbl_psw=new JLabel("密码");
    private JPasswordField txt_psw=new JPasswordField();
    private JButton btn_login=new JButton("登录");
    private JButton btn_reg=new JButton("注册");
    private JButton btn_exit=new JButton("退出");
    private String username;
    private String password;
    private int distinguish;
    String Driver="com.mysql.cj.jdbc.Driver";
    String URL="jdbc:mysql://localhost:3306/2048game?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    String user="root";
    String dbpsw="050211wxh";
    MySQLCon myDB=new MySQLCon(Driver,URL,user,dbpsw);
    public DatabaseOperation myOpr=new DatabaseOperation(myDB);
    public DesignFrame()
    {
        show();
    }
    public void show()
    {
        frame.setLayout(null);
        frame.setSize(470,300);
        frame.setLocation(400,200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font font=new Font("宋体",Font.BOLD,18);
        lbl_usn.setFont(font);
        lbl_usn.setForeground(Color.black);

        lbl_psw.setFont(font);
        lbl_psw.setForeground(Color.black);

        txt_username.setBackground(Color.white);
        txt_username.setFont(font);

        txt_psw.setBackground(Color.white);
        txt_psw.setFont(font);

        btn_login.setContentAreaFilled(false);
        btn_login.setFont(font);
        btn_login.setForeground(Color.black);
        btn_login.setBorder(BorderFactory.createRaisedBevelBorder());

        btn_reg.setContentAreaFilled(false);
        btn_reg.setFont(font);
        btn_reg.setForeground(Color.black);
        btn_reg.setBorder(BorderFactory.createRaisedBevelBorder());

        btn_exit.setContentAreaFilled(false);
        btn_exit.setFont(font);
        btn_exit.setForeground(Color.black);
        btn_exit.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel bj=new JPanel()
        {
            private static final long serialVersionUID=-4698290663120115225L;
            protected void paintComponent(Graphics g)
            {
                Image bg_image;
                try
                {
                    bg_image=ImageIO.read(new File("src/images/背景2.gif"));
                    g.drawImage(bg_image,0,0,getWidth(),getHeight(),null);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        };

        lbl_usn.setBounds(120,40,100,100);
        txt_username.setBounds(200,80,150,26);
        lbl_psw.setBounds(120,70,100,100);
        txt_psw.setBounds(200,110,150,26);
        btn_login.setBounds(100,180,80,26);
        btn_login.setBounds(100,180,80,26);
        btn_reg.setBounds(190,180,80,26);
        btn_exit.setBounds(280,180,80,26);

        frame.setContentPane(bj);
        frame.setLayout(null);

        frame.add(lbl_usn);
        frame.add(txt_username);
        frame.add(lbl_psw);
        frame.add(txt_psw);
        frame.add(btn_login);
        frame.add(btn_reg);
        frame.add(btn_exit);

        btn_login.addMouseListener(this);
        btn_reg.addMouseListener(this);
        btn_exit.addMouseListener(this);

        frame.setVisible(true);
    }
    public void mouseClicked(MouseEvent arg0)
    {
        username=txt_username.getText();
        password=new String(txt_psw.getPassword());
        if(distinguish==1)
        {
            if(myOpr.selectname(username))
            {
                if(myOpr.selectpassword(password))
                {
                    JOptionPane.showMessageDialog(null,"登陆成功,点击开始游戏","提示",2);
                    txt_username.setText("");
                    txt_psw.setText("");
                    distinguish=4;
                    new GameFrame(this);
                    frame.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"密码错误","提示",2);
                    myOpr.setnum1();
                    myOpr.setnum2();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"此用户不存在，请注册","提示",2);
                txt_username.setText("");
                txt_psw.setText("");
            }
        }
        if(distinguish==2)
        {
            String usn=(String)JOptionPane.showInputDialog(null,"请输入你的用户名：\n","注册",JOptionPane.PLAIN_MESSAGE,null,null,"");
            String psw=(String)JOptionPane.showInputDialog(null,"请输入你的密码:\n","注册",JOptionPane.PLAIN_MESSAGE,null,null,"");
            String score="0";
            if(usn!=null&&usn.length()!=0&&psw!=null&&psw.length()!=0)
            {
                myOpr.insertData(usn, psw,score);
                JOptionPane.showMessageDialog(null, "注册成功", "提示", 2);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"注册失败，用户名或密码不能为空","提示",2);
            }
        }
        if(distinguish==3)
        {
            int n=JOptionPane.showConfirmDialog(null,"是否退出？","结束登录",JOptionPane.YES_NO_OPTION);
            myDB.closeMyConnection();
            if(n==JOptionPane.YES_OPTION)
            {
                System.exit(1);
            }
        }
    }
    public void mouseEntered(MouseEvent arg0)
    {
        if(arg0.getSource()==btn_login)
        {
            distinguish=1;
            btn_login.setForeground(Color.blue);
            btn_login.setBorder(BorderFactory.createLoweredBevelBorder());

            btn_reg.setForeground(Color.black);
            btn_reg.setBorder(BorderFactory.createRaisedBevelBorder());

            btn_exit.setForeground(Color.black);
            btn_exit.setBorder(BorderFactory.createRaisedBevelBorder());
        }
        if(arg0.getSource()==btn_reg)
        {
            distinguish=2;

            btn_login.setForeground(Color.black);
            btn_login.setBorder(BorderFactory.createRaisedBevelBorder());

            btn_reg.setForeground(Color.blue);
            btn_reg.setBorder(BorderFactory.createLoweredBevelBorder());

            btn_exit.setForeground(Color.black);
            btn_exit.setBorder(BorderFactory.createRaisedBevelBorder());
        }
        if(arg0.getSource()==btn_exit)
        {
            distinguish=3;

            btn_login.setForeground(Color.black);
            btn_login.setBorder(BorderFactory.createRaisedBevelBorder());

            btn_reg.setForeground(Color.black);
            btn_reg.setBorder(BorderFactory.createRaisedBevelBorder());

            btn_exit.setForeground(Color.blue);
            btn_exit.setBorder(BorderFactory.createLoweredBevelBorder());
        }
    }
    public void mouseExited(MouseEvent arg0)
    {
        distinguish =0;

        //lbl_usn.setForeground(Color.black);
        //lbl_psw.setForeground(Color.black);

        txt_username.setOpaque(false);
        txt_psw.setOpaque(false);

        btn_login.setContentAreaFilled(false);
        btn_login.setForeground(Color.black);
        btn_login.setBorder(BorderFactory.createRaisedBevelBorder());

        btn_reg.setContentAreaFilled(false);
        btn_reg.setBorder(BorderFactory.createRaisedBevelBorder());
        btn_reg.setForeground(Color.black);

        btn_exit.setContentAreaFilled(false);
        btn_exit.setBorder(BorderFactory.createRaisedBevelBorder());
        btn_exit.setForeground(Color.black);


    }

    public void mousePressed(MouseEvent arg0)
    {}
    public void mouseReleased(MouseEvent arg0)
    {}
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public int getDistinguish()
    {
        return  distinguish;
    }
}
