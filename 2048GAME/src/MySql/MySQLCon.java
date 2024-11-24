package MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class MySQLCon {
    private String DbDriver;
    private String DBURL;
    private String DBUser;
    private String DBPass;
    private Connection conn=null;
    private Statement stmt=null;

    public MySQLCon(String driver,String dburl,String user,String pass)
    {
        DbDriver=driver;
        DBURL=dburl;
        DBUser=user;
        DBPass=pass;
        try
        {
            Class.forName(DbDriver);
        }catch(Exception e)
        {
            System.out.println(1);
            e.printStackTrace();
        }
        try
        {
            conn=DriverManager.getConnection(DBURL,DBUser,DBPass);
            stmt=conn.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(2);

            e.printStackTrace();
        }
    }
    public Connection getMyConnection()
    {
        return conn;
    }
    public Statement getMyStatement()
    {
        return stmt;
    }
    public void closeMyConnection()
    {
        try
        {
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(3);

            e.printStackTrace();
        }
    }
    public String toString()
    {
        return "数据库驱动程序"+DbDriver+",链接地址"+DBURL+",用户名"+DBUser+",密码"+DBPass;
    }
}
