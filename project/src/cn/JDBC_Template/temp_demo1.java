package cn.JDBC_Template;

import cn.jdbc.emp;
import cn.jdbc_Druid.Util_druid;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class temp_demo1 {
    //1.参数就是连接池对象（Druid工具类实现的）
    //2.创建JdbcTemplate（相当于实现了Connection对象,PrepareStatement对象,ResultSet对象）
    private JdbcTemplate temp = new JdbcTemplate(Util_druid.getDataSource());
    public static void main(String[] args) {

    }
    /**
     * 测试1.执行修改
     */
    @Test
    public void test1(){

        //3.定义sql语句
        String sql = "update account set username='马六' where id = ? and password = ?";
        //4.加上数据 执行sql语句 返回执行条数
        //  第一个参数是sql语句 第二三个参数对应问号的值
        int count =temp.update(sql,4,"44"); //update 进行增删改操作
        System.out.println(count);
    }

    /**
     *测试2.queryForMap执行查询一行数据
     */
    @Test
    public void test2(){
        String sql = "select * from stu where id = ?";
        //queryForMap查询出来的结果 只能是一行数据 （列名为key,数据为value）
        Map<String, Object> map = temp.queryForMap(sql, 2);
        System.out.println(map);
    }

    /**
     * 测试3.queryForList执行查询多行数据
     */
    @Test
    public void test3(){
        String sql = "select * from stu";
        //queryForList 先将表封装成Map，然后再将每个Map封装成List
        List<Map<String, Object>> maps = temp.queryForList(sql);
        System.out.println(maps);
    }

    /**
     * 测试4.query 实现Javabean方式查询全部结果
     */
    @Test
    public void test4(){
        String sql = "select * from course";
        /* query 需要实现RowMapper接口 ,BeanPropertyRowMapper是它的子类,
         * 此类将列名和javabean的成员变量名进行匹配，相同就拿出来输出
         * emp 成员可能存在null情况 而基本数据类型不允许空值
         * 所以emp成员变量应该改为引用数据类型 允许空值 应该是大写的数据类型 如int -> Integer
         * 目前更新了不需要修改为大写了
         */
        List<emp> query = temp.query(sql, new BeanPropertyRowMapper<emp>(emp.class));
        for (emp emp : query ){
            System.out.println(emp);
        }
    }

    /**
     * 测试5.查询 记录数
     */
    @Test
    public void test5(){
        String sql = "select count(id) from account";
        //第二个参数是返回的数据类型
        Long aLong = temp.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }

}
