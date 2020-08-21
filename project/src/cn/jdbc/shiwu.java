package cn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 在程序运行过程中 如果出现错误 会停止运行
 * 但是 如果这个业务被事务管理
 * 即使因为其他问题出错 也会回滚事务将事务管理内的全部业务完成
 */

public class shiwu {
    public static void main(String[] args) {
        shiwu shiwu = new shiwu();
        shiwu.execute();
    }
    public void execute(){
        Connection conn = null;
        PreparedStatement pstate1 = null;
        PreparedStatement pstate2 = null;
        ResultSet result = null;
        try {
            //注册连接
            conn = Util_demo.getconnection();

            //开启事务
            conn.setAutoCommit(false);

            String sql1 = "update stu set age = age - ? where id = ?";
            String sql2 ="update stu set age = age + ? where id = ?";
            //创建preparedstatement对象
            pstate1 = conn.prepareStatement(sql1);
            pstate2 = conn.prepareStatement(sql2);
            //输入数据
            pstate1.setInt(1,2);
            pstate1.setInt(2,1);
            pstate2.setInt(1,2);
            pstate2.setInt(2,2);
            //执行sql语句
            pstate1.executeUpdate();

            //手动制作错误
            int a = 3/0;

            pstate2.executeUpdate();
            //提交事务
            conn.commit();
        } catch (Exception e) {
            if (conn !=null){
                //回滚事务
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }
}
