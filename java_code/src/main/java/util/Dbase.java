package util;

import java.sql.*;

public class Dbase {
    private static final String URL ="jdbc:mysql://localhost:3306/mydb";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PWD = "F201936.";

    static {
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //获取数据库链接
    public static Connection getConn(){
        try{
            return DriverManager.getConnection(URL,USER_NAME,PWD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConn(Connection connection){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement ps){
        if (ps!=null){
            try {
                ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
