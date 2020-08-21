package cn.jdbc_Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * druid 阿里开发的数据库连接池
 */

public class druid_demo1 {
    public static void main(String[] args) {
        //1.导入druid jar包 定义properties文件 （手动）
        try {
            //2.导入 配置文件 properties
            Properties pro = new Properties();
            pro.load(new FileReader("E:\\Java_IDEA\\project\\src\\druid.properties"));
//            InputStream is = druid_demo1.class.getClassLoader().getResourceAsStream("druid.properties");
//            pro.load(is);
            //3.创建连接池对象
            DataSource ds = DruidDataSourceFactory.createDataSource(pro);
            //4.获取连接对象
            for (int i = 1; i<=21;i++) {
                Connection conn = ds.getConnection();
                System.out.println(i+":"+conn);
                if (i==5){
                    //close 是回到连接池中不是释放资源
                    conn.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
