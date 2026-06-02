import com.stapimex.config.DBConnection;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            System.out.println("=================================");
            System.out.println("KET NOI MYSQL THANH CONG");
            System.out.println("Database: " + conn.getCatalog());
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println("KET NOI THAT BAI");
            e.printStackTrace();

        }
    }
}
