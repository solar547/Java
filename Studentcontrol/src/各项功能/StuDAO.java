package 各项功能;


import connect.JdbcConnect;

import java.util.ArrayList;
import java.util.Map;

/**
 * 是功能库，保存各功能的mysql语句部分，在StuDAO函数中再调用JDBC类与数据库进行连接，完成相应功能。
 */
public class StuDAO{

    //登录页面连接数据库，调用三参数的executeQuery方法，登录管理员账户
    public boolean Login(String UserName, String PassWord){
        if (UserName == null || PassWord == null){
            return false;
        }
        //使用？防止数据库注入问题
        String sql = "select id from user where username = ? and password = ?";
        boolean a = new JdbcConnect().executeQ(UserName, PassWord,sql);
        return a;
    }

    //登录学生账户
    public boolean LoginStu(String UserName, String PassWord) {
        if (UserName == null || PassWord == null){
            return false;
        }
        //使用？防止数据库注入问题
        String sql = "select id from studentuser where username = ? and password = ?";
        boolean a = new JdbcConnect().executeQ(UserName,PassWord,sql);
        return a;
    }

    //查询学生表studentinfor内容，不包括院系表内容，用于填充菜单首页的选择内容
    public Map<Integer,ArrayList<String>> findAll() {
        String sql = "select * from studentinfor";
        //将数据库内容存储在集合内进行传递，因为需要分段来填充JTable类，所以这里选择
        //List接口的ArraysList
        String a = "-1";
        return new JdbcConnect().executeQAll(sql,a);
    }

    // 查询指定内容（studentinfor）
    public ArrayList<String> FindSwing(String id) {
        String sql1 = "select * from studentinfor s,depart d where s.班级 = d.班级名 and 学号 = ? ";
        Map<Integer,ArrayList<String>> ma = new JdbcConnect().executeQAll(sql1,id);
        ArrayList<String> arr = ma.get(0);
        return arr;
    }

    //DML 更新表格内容
    public void DMLUpdate(String id, String name, String sex, String country, String zzFace, String classs) {
        String sql2 = "update studentinfor set 姓名 = ?, 性别 = ?, 籍贯 = ?, 政治面貌 = ?, 班级 = ? where 学号 = ?";
        new JdbcConnect().executeUpdate(sql2,id,name,sex,country,zzFace,classs);
    }

    //查找学院表的学院名信息填充下拉列表
    public ArrayList<String> findDepartXueyuan(String a){
            String sql3 = "select distinct 学院名 from depart;";
            return new JdbcConnect().executeDepartXueyuan(sql3,a);
    }

    //查找学院表的班级信息做下拉列表
    public ArrayList<String> findDepartClass(String classm){
        String sql3 = "select distinct 班级名 from depart where 学院名 = ?";
        return new JdbcConnect().executeDepartClass(sql3,classm);
    }

    //插入新的信息
    public void DMLInsert(String id, String name, String sex, String country, String zzFace, String classs) {
        String sql = "insert into studentinfor values(?,?,?,?,?,?)";
        new JdbcConnect().executeInsert(sql,id,name,sex,country,zzFace,classs);
    }

    //删除选中的信息
    public void DMLDelete(String id) {
        String sql = "delete from studentinfor where 学号 = ?";
        new JdbcConnect().executeDelete(sql,id);
    }

    public Map<Integer,ArrayList<String>> FindAllDepart() {
        String sql = "select distinct * from depart";
        return new JdbcConnect().executeDepartALL(sql);
    }

    public Integer FindNumber(String id) {
        String sql = "select count(班级) 统计 from studentinfor where 班级 = ?";
        return new JdbcConnect().executeQcount(sql,id);
    }


}
