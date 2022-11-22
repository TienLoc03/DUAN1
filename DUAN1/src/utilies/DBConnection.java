package utilies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HangNT
 */
public class DBConnection {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "DUAN1";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123456";

    /**
     * Get connection to MSSQL Server
     *
     * @return Connection
     */
    /*
    jdbc driver types: 
        - JDBC-ODBC: chỉ hỗ trợ trên windown : ko giao tiếp trực tiếp vs datasorce (chỉ dùng đc trên window => ít đc dùng)
        - netive api: lai mã C và C++ -> giữa ứng dụng java và thư viện bị chậm và ko thuần túy java
        -  network  protocal :
        - native protocal: ko có trung gian và chọc trực tiếp và data sorce , mức độ tin tưởng tốt nhất
     */
    public static Connection getConnection() {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME;

        try {
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
