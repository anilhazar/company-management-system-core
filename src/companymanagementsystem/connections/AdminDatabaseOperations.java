package companymanagementsystem.connections;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDatabaseOperations extends Connections {

    public boolean isAdminExistsWithUsername(String username) {
        try {
            String query2 = "SELECT username FROM ADMIN";
            statement = connection.createStatement();
            ResultSet resultSet2 = statement.executeQuery(query2);
            while (resultSet2.next()) {
                String name = resultSet2.getString("username");
                if (username.equals(name)) {
                    System.out.println("There is already a user named as " + username);
                    return true;

                }
            }
        } catch (SQLException ex) {

            System.out.println("isAdminExistWithUsername method failed to execute due to " + ex);
            return false;
        }
        return false;
    }

    public boolean preparedAddData(String username, String password) {
        if (isAdminExistsWithUsername(username)) {
            return false;
        }

        try {
            String query = "INSERT INTO ADMIN (username, password) VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, (password));
            int resultSet = preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("preparedAddData method failed due to " + exception);
            return false;
        }

        return true;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public boolean preparedLoginCheckpoint(String username, String password) {

        try {
            String query = "SELECT id,username,password FROM ADMIN WHERE username=? AND password=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            return preparedStatement.executeQuery().next();

        } catch (SQLException exception) {
            System.out.println(exception);
            return false;
        }

    }

}
