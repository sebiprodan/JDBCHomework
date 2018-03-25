package ro.siit.homework;

import org.junit.Test;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AppTest {

    @Test

    public void test_delete_a_row() {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {
            Statement deleteRow = connection.createStatement();
            ResultSet executeQuery1 = deleteRow.executeQuery("DELETE FROM accomodation");
            ResultSet executeQuery2 = deleteRow.executeQuery("DELETE FROM room_fair");
            ResultSet executeQuery3 = deleteRow.executeQuery("DELETE FROM accomodation_fair_relation");
            } catch (SQLException s){
            s.printStackTrace();
        }


    }

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

    public void test_insert_statement_with_threads(){

        AccomodationTable accomodationTable = new AccomodationTable();
        accomodationTable.start();
        RoomFairTable roomFairTable = new RoomFairTable();
        roomFairTable.start();
    }
    @Test

    public void test_insert_statement() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO accomodation " +
                    "(id, type, bed_type, max_guests,description) values (?,?,?,?,?)");
            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO room_fair "
                    + "(id, value, season) values (?,?,?)");
            PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO accomodation_fair_relation "
                    + "(id, id_accomodation, id_room_fair) values (?,?,?)");

            for (int i =1; i <= 10; i++){
                preparedStatement1.setInt(1, i);
                preparedStatement2.setInt(1, i);
                preparedStatement3.setInt(1, i);

                preparedStatement1.setString(2, RoomType.getRandom().toString());
                double random = ThreadLocalRandom.current().nextDouble(245, 700);
                preparedStatement2.setDouble(2, random);
                preparedStatement3.setInt(2, i);

                preparedStatement1.setString(3, BedType.getRandom().toString());
                preparedStatement2.setString(3,Seasons.getRandom().toString());
                preparedStatement3.setInt(3, i);

                preparedStatement1.setInt(4, 2);

                preparedStatement1.setString(5, "This room has a great view, no-smoking and with breakfast included");

                preparedStatement1.executeUpdate();
                preparedStatement2.executeUpdate();
                preparedStatement3.executeUpdate();}
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
