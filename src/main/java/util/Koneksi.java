package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3307/rental_mobil";
    private static final String USER = "rental";
    private static final String PASS = "rental123";    // bawaan XAMPP: kosong

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}