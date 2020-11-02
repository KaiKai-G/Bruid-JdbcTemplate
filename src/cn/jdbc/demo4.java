package cn.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用自定义的工具类Util_demo 简化代码
 */

public class demo4 {
    public static void main(String[] args) {
        List<emp> list = new demo4().show2();
        System.out.println(list);
    }
    public List<emp> show2(){
        Connection conn = null;
        Statement state = null;
        ResultSet result = null;
        List<emp> list = null;//存储数据对象的集合
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8","root","guolongkai");
            conn = Util_demo.getconnection();
            String sql = "SELECT * FROM course";
            state = conn.createStatement();
            result = state.executeQuery(sql);
            list =  new ArrayList<emp>();

            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                emp emp = new emp();
                emp.setId(id);
                emp.setName(name);
                emp.setAge(age);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Util_demo.close(conn, state, result);
        }
        return list;
    }

}
