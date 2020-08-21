package cn.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将数据库数据当作类对象,然后进行操作
 */
public class demo3 {

    public static void main(String[] args) {
        List<emp> list = new demo3().show();
        System.out.println(list);
    }
    public List<emp> show(){
        Connection conn = null;
        Statement state = null;
        ResultSet result = null;
        List<emp> list = null;//存储数据对象的集合
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8","root","guolongkai@123");
            String sql = "select * from stu";
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (result != null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (state != null){
                try {
                    state.close();
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

        return list;
    }
}
