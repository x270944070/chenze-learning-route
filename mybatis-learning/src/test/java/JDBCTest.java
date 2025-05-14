import org.junit.Test;

import java.sql.*;


public class JDBCTest {

    @Test
    public void test1(){
        // 数据库连接信息
        String jdbcUrl = "jdbc:mysql://gz-cdb-nf8meuv5.sql.tencentcdb.com:26242/chenze_learning";
        String username = "root";
        String password = "Cz0523..";

        // JDBC 查询语句
        String sql = "SELECT id, last_name, email, gender FROM mybatis_cache_employee where id > ?";

        // JDBC 相关对象
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立连接
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            conn.setAutoCommit(false);

            // 参数组装
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 5);


            pstmt.execute();

            rs = pstmt.getResultSet();

            // 处理结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String gender = rs.getString("gender");

                System.out.println("ID: " + id + ", Name: " + lastName + ", Email: " + email + ", Gender: " + gender);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database access error.");
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
