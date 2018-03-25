package ro.siit.homework;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/siit6", "postgres", "postgres")) {
            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery("select * from users");
            while (users.next()) {
                System.out.print(users.getString(1) + " ");
                System.out.print(users.getString(2) + " ");
                System.out.print(users.getString(3) + " ");
                System.out.println(users.getInt(4));

            }

            users = statement.executeQuery("select u.name, u.email, a.city as oras from users u join addresses a on u.id = a.user_id");
            while (users.next()) {
//                System.out.print(users.getString(1) + " ");
//                System.out.print(users.getString(2) + " ");
//                System.out.println(users.getString(3));
                System.out.print(users.getString("name") + " ");
                System.out.print(users.getString("email") + " ");
                System.out.println(users.getString("oras"));

            }

            statement.executeUpdate("update users set name = 'Gelu Naum' where email ='bgp1234@gmail.com'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
