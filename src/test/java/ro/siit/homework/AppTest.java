package ro.siit.homework;

import org.junit.Test;

import java.sql.*;
import java.util.Arrays;

public class AppTest {

    @Test

    public void test_load_driver() {

        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }

    }

    @Test

    public void test_insert_statement() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accomodation " +
                    "(id, type, bed_type, max_guests,description) values (?,?,?,?,?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Standard Double-Room");
            preparedStatement.setString(3, "Queen Size");
            preparedStatement.setInt(4, 2);
            preparedStatement.setString(5, "This room has a great view, no-smoking and with breakfast included");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test

    public void test_connectivity_DB() {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {
            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery("select * from accomodation");
            while (users.next()) {
                System.out.print(users.getInt(1) + "|");
                System.out.print(users.getString(2) + "|");
                System.out.print(users.getString(3) + "|");
                System.out.print(users.getInt(4) + "|");
                System.out.println(users.getString(5));

            }

//            users = statement.executeQuery("select u.name, u.email, a.city as oras from users u join addresses a on u.id = a.user_id");
//            while (users.next()) {
////                System.out.print(users.getString(1) + " ");
////                System.out.print(users.getString(2) + " ");
////                System.out.println(users.getString(3));
//                System.out.print(users.getString("name") + " ");
//                System.out.print(users.getString("email") + " ");
//                System.out.println(users.getString("oras"));
//
//            }
//
//            statement.executeUpdate("update users set name = 'Gelu Naum' where email ='bgp1234@gmail.com'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
