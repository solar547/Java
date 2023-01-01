package connect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC类
 * 与功能类分开，可以被多次重复调用，相似功能可以调用相同的JDBC类，起到代码重复性低高效的特点
 * 其中还调用JDBCUtils工具类，简化每次连接和关闭数据库的代码
 */
public class JdbcConnect {
    //定义JDBC三个常用方法，便于被JDBCUtils函数调用
    Connection conn = null;
    PreparedStatement stat = null;
    ResultSet rs = null;

    //executeQuery 查询 三参数
    public boolean executeQ(String UserName, String PassWord,String sql){
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            stat.setString(1,UserName);
            stat.setString(2,PassWord);
            rs = stat.executeQuery();
            //正确判断
            boolean a = rs.next();
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    //executQuery 查询学生表内容
    public Map<Integer,ArrayList<String>> executeQAll(String sql,String n){
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            if (n != "-1"){
                stat.setString(1,n);
                rs = stat.executeQuery();
                Map<Integer,ArrayList<String>> map = new HashMap<>();
                //正确判断
                Integer a = 0;
                while(rs.next()){
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(rs.getString("学号"));
                    arr.add(rs.getString("姓名"));
                    arr.add(rs.getString("性别"));
                    arr.add(rs.getString("籍贯"));
                    arr.add(rs.getString("政治面貌"));
                    arr.add(rs.getString("班级"));
                    arr.add(rs.getString("专业名"));
                    arr.add(rs.getString("学院名"));
                    arr.add(rs.getString("院长"));
                    map.put(a,arr);
                    a++;
                }
                return map;
            }else {
                rs = stat.executeQuery();
                Map<Integer, ArrayList<String>> map = new HashMap<>();
                //正确判断
                Integer a = 0;
                while (rs.next()) {
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(rs.getString("学号"));
                    arr.add(rs.getString("姓名"));
                    arr.add(rs.getString("性别"));
                    arr.add(rs.getString("籍贯"));
                    arr.add(rs.getString("政治面貌"));
                    arr.add(rs.getString("班级"));
                    map.put(a, arr);
                    a++;
                }
                return map;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    //executeUpdate用法 更新菜单内容
    public void executeUpdate(String sql2, String id, String name, String sex, String country, String zzFace, String classs) {
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql2);
            stat.setString(1,name);
            stat.setString(2,sex);
            stat.setString(3,country);
            stat.setString(4,zzFace);
            stat.setString(5,classs);
            stat.setString(6,id);
            //因为executeUpdate可以返回修改行数，以此特性来判断是否更新成功、
            int count = stat.executeUpdate();
            if (count > 0){
                JOptionPane.showMessageDialog(null,"修改成功");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"请检查输入信息是否格式内容正确","修改失败", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(stat,conn);
        }
    }

    //查询院系表的内容:学院名
    public ArrayList<String> executeDepartXueyuan(String sql, String a) {
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery(sql);
            ArrayList<String> list = new ArrayList<>();
            while(rs.next()){
                list.add(rs.getString(a));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    //根据学院查询所属院系表的班级名
    public ArrayList<String> executeDepartClass(String sql, String a){
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            stat.setString(1,a);
            rs = stat.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (rs.next()){
                list.add(rs.getString("班级名"));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    //插入新内容
    public void executeInsert(String sql, String id, String name, String sex, String country, String zzFace, String classs) {
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            stat.setString(1,id);
            stat.setString(2,name);
            stat.setString(3,sex);
            stat.setString(4,country);
            stat.setString(5,zzFace);
            stat.setString(6,classs);
            int count = stat.executeUpdate();
            //做出添加成功失败的提示小窗
            if (count > 0){
                JOptionPane.showMessageDialog(null,"添加成功");
            }else {
                JOptionPane.showMessageDialog(null,"添加失败");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"添加失败,请重新输入" );
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    //删除数据
    public void executeDelete(String sql,String id) {
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            stat.setString(1,id);
            int count = stat.executeUpdate();
            //做出删除成功失败的提示小窗
            if (count > 0){
                JOptionPane.showMessageDialog(null,"删除成功");
            }else {
                JOptionPane.showMessageDialog(null,"删除失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    public Map<Integer,ArrayList<String>> executeDepartALL(String sql) {
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            Map<Integer,ArrayList<String>> map = new HashMap<>();
            int a = 0;//做键值对中的键值
            while (rs.next()){
                ArrayList<String> list = new ArrayList<>();
                list.add(rs.getString("学院名"));
                list.add(rs.getString("院长"));
                list.add(rs.getString("专业名"));
                list.add(rs.getString("班级名"));
                map.put(a,list);
                a++;
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }

    public Integer executeQcount(String sql, String id) {
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.prepareStatement(sql);
            stat.setString(1,id);
            rs = stat.executeQuery();
            if(rs.next()){
                return rs.getInt("统计");
            }else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(rs,stat,conn);
        }
    }
}
