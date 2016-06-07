package cn.xiaomei.crawler.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by chunli on 16/5/11.
 */

public class MysqlConnect {
    public static MysqlConnect instance=new MysqlConnect();
    public Connection conn;
    private MysqlConnect(){
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                conn = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }



    public void close() throws SQLException {
        if(conn!=null&&!conn.isClosed()){
                 conn.close();
        }
    }
}
