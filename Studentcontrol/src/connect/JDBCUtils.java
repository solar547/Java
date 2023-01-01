package connect;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/*
工具类
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static {
        //获取资源文件，获取值

        //1.创建Properties集合类
        Properties pro = new Properties();
        //2.加载文件
        try {
            //获取src路径下的文件的方式-->ClassLoader 类加载器
           // ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = ClassLoader.getSystemResource("jdbc.properties");
            String path = res.getPath();
            System.out.println(path);
            pro.load(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //3.获取数据，赋值
        url = pro.getProperty("url");
        user = pro.getProperty("user");
        password = pro.getProperty("password");
        driver = pro.getProperty("driver");
        //4.注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**

     * 获取连接

     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**

     * 释放资源

     * @param stmt

     * @param conn
     */
    public static void close(Statement stmt, Connection conn){
        if( stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 释放资源
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
