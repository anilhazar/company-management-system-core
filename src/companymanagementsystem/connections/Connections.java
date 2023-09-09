package companymanagementsystem.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
//TODO : Connections sýnýfý tek baþýna veritabaný baðlanacak. Bu sýnýf üzerinden 3 ayrý class ve classlarý spesifik veritabanýna baðla 

 abstract class Connections {

    private final String username = "root";
    private final String password = "123456";

    private final String dbname = "company_management";

    private final String host = "localhost";

    private final int port = 3306;

    protected Connection connection;

    protected Statement statement;

    protected PreparedStatement preparedStatement;

    public Connections() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed" + e);
        }
    }

}
