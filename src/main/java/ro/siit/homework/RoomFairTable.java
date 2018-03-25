package ro.siit.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class RoomFairTable extends Thread {
    @Override
    public void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin")) {
            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO room_fair "
                    + "(id, value, season) values (?,?,?)");

            for (int i = 1; i <= 10; i++) {
                preparedStatement2.setInt(1, i);
                double randomRoomFair = ThreadLocalRandom.current().nextDouble(245, 700);
                preparedStatement2.setDouble(2, Double.parseDouble(new DecimalFormat("#.##").format(randomRoomFair)));
                preparedStatement2.setString(3, Seasons.getRandom().toString());
                preparedStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

