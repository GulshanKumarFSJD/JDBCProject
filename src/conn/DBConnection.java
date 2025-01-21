package conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static volatile DBConnection ob;
    private final Connection connection;
    private final String url="jdbc:mysql://localhost:3308/student_db";
    private final String user="root";
    private final String pass="root";

    private DBConnection()  {
        try {
            connection= DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static DBConnection getInstance()
    {
        if (ob==null)
        {
            synchronized (DBConnection.class)
            {
                if (ob==null){
                    ob=new DBConnection();
                }
            }
        }
        return ob;
    }

    public Connection getConnection(){
        return connection;
    }


}

