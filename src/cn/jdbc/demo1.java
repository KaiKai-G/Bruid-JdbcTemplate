package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 最初的添加 / 修改
 */
public class demo1 {
    public static void main(String[] args){
        Connection con = null;
        Statement stat = null;
        try {
            /* mysql 5后的版本
             * 不需要注册驱动了*/
            //0.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //1.连接数据库对象 （要是连接本地的ip和端口可以在url中省略ip和端口）
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8","root","guolongkai");
            //2.定义sql语句
            String sql = "insert  into stu (id,name,age) values (3,'凯哥',5)";
//            String sql ="update course set id = 2";
            //3.创建执行sql语句的对象
            stat = con.createStatement();
            //4.执行sql语句 executeUpdate执行DML（insert、update、delete）语句、DDL(create，alter、drop)语句
            int count = stat.executeUpdate(sql); //返回的是更新的条数
            //5.处理结果
            System.out.println(count);
            if (count > 0){
                System.out.println("添加成功");
            }else System.out.println("添加失败");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //6.释放资源
            if (stat != null){
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
