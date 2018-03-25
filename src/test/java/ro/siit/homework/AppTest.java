package ro.siit.homework;

import org.junit.Test;
import org.postgresql.util.PSQLException;

import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AppTest {

    @Test

    public void test_delete_a_row() {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {
            Statement deleteRow1 = connection.createStatement();
            ResultSet executeQuery1 = deleteRow1.executeQuery("DELETE FROM accomodation_fair_relation");
//            ResultSet executeQuery2 = deleteRow1.executeQuery("DELETE FROM accomodation");
//            ResultSet executeQuery3 = deleteRow1.executeQuery("DELETE FROM room_fair");

        } catch (SQLException s) {
            s.printStackTrace();
        }
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

            for (int i = 1; i <= 10; i++) {
                preparedStatement1.setInt(1, i);
                int randomIDRoomFair = ThreadLocalRandom.current().nextInt(10000);
                preparedStatement2.setInt(1, randomIDRoomFair);
                preparedStatement3.setInt(1, i);

                preparedStatement1.setString(2, RoomType.getRandom().toString());
                double randomRoomFair = ThreadLocalRandom.current().nextDouble(245, 700);
                preparedStatement2.setDouble(2, Double.parseDouble(new DecimalFormat("#.##").format(randomRoomFair)));
                preparedStatement3.setInt(2, i);

                preparedStatement1.setString(3, BedType.getRandom().toString());
                preparedStatement2.setString(3, Seasons.getRandom().toString());
                preparedStatement3.setInt(3, randomIDRoomFair);

                preparedStatement1.setInt(4, 2);

                preparedStatement1.setString(5, "This room has a great view, no-smoking and with breakfast included");

                preparedStatement1.executeUpdate();
                preparedStatement2.executeUpdate();
                preparedStatement3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void test_join_tables() {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {
            Statement joinTables = connection.createStatement();
            ResultSet resultSet = joinTables.executeQuery("select a.id as RoomNo, r.value as Price from accomodation_fair_relation a join room_fair r on a.id_room_fair = r.id");
            while (resultSet.next()) {
                System.out.print(resultSet.getString("RoomNo")+ " | ");
                System.out.println(resultSet.getString("Price") + " RON ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test

    public void test_connectivity_DB() {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from accomodation");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + " | ");
                System.out.print(resultSet.getString(2) + " | ");
                System.out.print(resultSet.getString(3) + " | ");
                System.out.print(resultSet.getInt(4) + " | ");
                System.out.println(resultSet.getString(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
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
}
