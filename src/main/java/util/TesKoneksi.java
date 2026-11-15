package util;

import java.sql.Connection;

public class TesKoneksi {
    public static void main(String[] args) {
        try (Connection _ = Koneksi.getConnection()) {
            System.out.println("Koneksi berhasil!");
        } catch (Exception e) {
            System.out.println("Gagal: " + e.getMessage());
        }
    }
}