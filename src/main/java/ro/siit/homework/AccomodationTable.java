package ro.siit.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class AccomodationTable extends Thread {

    @Override
    public void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO accomodation " +
                    "(id, type, bed_type, max_guests,description) values (?,?,?,?,?)");
            for (int i = 1; i <= 10; i++) {
                preparedStatement1.setInt(1, i);
                preparedStatement1.setString(2, RoomType.getRandom().toString());
                preparedStatement1.setString(3, BedType.getRandom().toString());
                preparedStatement1.setInt(4, 2);
                preparedStatement1.setString(5, "This room has a great view, no-smoking and with breakfast included");
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
