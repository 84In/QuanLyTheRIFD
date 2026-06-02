package com.stapimex.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Dotenv dotenv =
            Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

    private static final String URL =
            "jdbc:mysql://"
                    + dotenv.get("DB_HOST")
                    + ":"
                    + dotenv.get("DB_PORT")
                    + "/"
                    + dotenv.get("DB_NAME")
                    + "?allowPublicKeyRetrieval=true"
                    + "&useSSL=false"
                    + "&serverTimezone=Asia/Ho_Chi_Minh";

    private static final String USER =
            dotenv.get("DB_USER");

    private static final String PASSWORD =
            dotenv.get("DB_PASSWORD");

    private DBConnection() {
    }

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}