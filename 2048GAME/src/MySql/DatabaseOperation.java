package MySql;

import MyFrame.DesignFrame;
import RankNode.RankNode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import RankNode.*;

public class DatabaseOperation {
    private MySQLCon myDB=null;
    private Statement stmt=null;
    private Connection conn=null;
    private int num1,num2;
    private String name,password,score;
    public DatabaseOperation(MySQLCon mydb)
    {
        conn=mydb.getMyConnection();
        stmt=mydb.getMyStatement();
        num1=0;
        num2=0;
    }
    public void insertData(String name,String password,String mscore)    //插入
    {
        try
        {
            String sql="insert into user(username,password,score) values('"+name+"','"+password+"','"+mscore+"')";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void deletedata(String name)      //删除
    {
        String sql="delete from user where username="+name+"";
        System.out.print(sql);
        try
        {
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updatadata(String name,String score)         //修改
    {
        //String sql="updata user set score ='"+score+"' where name='"+name+"'&&name='"+name+"'";
        String sql="update user set score = '" + score + "' where username = '" + name + "'";
        //String sql="updata user set name ='"+name+"',password='"+password+"'where name='"+name+"'&&password='"+password+"'";
        try
        {
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean selectpassword(String mpasswpord)
    {
        String sql="select * from user";
        try
        {
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                password=rs.getString(("password"));
                num2++;
                if(password.equals(mpasswpord)&&(num2==num1))
                {
                    DesignFrame.login_Name=rs.getString(("username"));
                    DesignFrame.login_Score=rs.getInt(("score"));
                    return true;
                }

            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
//    public  ResultSet GetAllScore()
//    {
//
//    }
    public boolean selectname(String mname)
    {
        String sql="select * from user";
        try
        {
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                name=rs.getString(("username"));
                num1++;
                if(name.equals(mname)) {

                    return true;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Vector<RankNode> selectall()
    {
        String sql="select * from user";
        Vector<RankNode> rank=new Vector();
        try
        {
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                name=rs.getString(("username"));
                score=rs.getString(("score"));
                int t=Integer.parseInt(score);
                rank.add(new RankNode(t,name));
            }
            return rank;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return rank;
    }
    public String getName()
    {
        return name;
    }
    public String getPassword()
    {
        return password;
    }
    public void setnum1()
    {
        num1=0;
    }
    public void setnum2()
    {
        num2=0;
    }
}
