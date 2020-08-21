package cn.jdbc;
import java.sql.*;

/**
 * 遍历查询结果
 */
public class demo2 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement state = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8","root","guolongkai@123");
            String sql = "select distinct * from stu";
            state = conn.createStatement();
            //executeQuery 执行的是查询语句
            resultSet =  state.executeQuery(sql);
            //循环判断游标是否是最后一行末尾。
            while(resultSet.next()){
                //得到数据
                int id = resultSet.getInt(1); //得到这一行的第一列数据
                String name = resultSet.getString("name");//得到这一行name列的数据
                int age = resultSet.getInt(3);//得到这一行的第三列数据
                System.out.println(id + "---"+name +"---"+age);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultSet != null){
            try {
                resultSet.close();
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
}
