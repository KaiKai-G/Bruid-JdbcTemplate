package cn.jdbc;

import java.sql.*;
import java.util.Scanner;

public class account_demo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        boolean account = new account_demo().account(username,password);;
        if (account){
            System.out.println("登陆成功");
        }else System.out.println("账号或密码错误");

    }

    public boolean account(String username,String password){
        if (username == null || password == null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstate = null;
        ResultSet result = null;
        try {
            //1.注册驱动和连接
            conn = Util_demo.getconnection();
            //2.使用preparedstatement 执行sql语句 ：防止sql注入（特殊关键字参与字符串的拼接。会造成安全性问题）
            String sql = "select * from account where username = ? and password = ?";
            //3.创建执行sql对象
            pstate = conn.prepareStatement(sql);
            //4.输入数据 第一个参数是？的位置 第二个参数是值
            pstate.setString(1,username);
            pstate.setString(2,password);
            //5.创建数据对象 不需要输入参数
            result = pstate.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //pstate 父类引用实现子类对象
            Util_demo.close(conn,pstate,result);
        }
        return false;
    }

}

