package cn.jdbc_Druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class druid_Util_demo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstate = null;
        try {
            //获取连接池工具类中的连接对象
            conn = Util_druid.getConnection();
            String sql = "insert into account value (null,?,?)";
            //执行sql语句对象
            pstate = conn.prepareStatement(sql);
            //设置数据
            pstate.setString(1,"孙七");
            pstate.setString(2,"55");
            //输出结果
            int count = pstate.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Util_druid.close(null,pstate,conn);
        }

    }
}
