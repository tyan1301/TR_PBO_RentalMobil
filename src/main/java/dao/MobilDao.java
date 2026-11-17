
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Mobil;
import util.Koneksi;

public class MobilDao {

    // Mengambil semua data mobil
    public ArrayList<Mobil> ambilSemua() throws SQLException {
        ArrayList<Mobil> hasil = new ArrayList<>();
        String sql = "SELECT * FROM mobil";

        try (Connection c = Koneksi.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Mobil m = new Mobil(
                    rs.getString("plat_nomor"),
                    rs.getString("nama_mobil"),
                    rs.getString("merk"),
                    rs.getDouble("harga_sewa")
                );

                m.setStatusMobil(rs.getString("status_mobil"));
                hasil.add(m);
            }
        }

        return hasil;
    }

    // Mencari mobil berdasarkan merk
    public ArrayList<Mobil> cariMerk(String merk) throws SQLException {
        ArrayList<Mobil> hasil = new ArrayList<>();

        String sql = "SELECT * FROM mobil WHERE LOWER(merk) LIKE LOWER(?)";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + merk.trim() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mobil m = new Mobil(
                        rs.getString("plat_nomor"),
                        rs.getString("nama_mobil"),
                        rs.getString("merk"),
                        rs.getDouble("harga_sewa")
                    );

                    m.setStatusMobil(rs.getString("status_mobil"));
                    hasil.add(m);
                }
            }
        }

        return hasil;
    }

    // Menyimpan mobil baru
    public void simpan(Mobil m) throws SQLException {
        String sql = "INSERT INTO mobil "
                + "(plat_nomor, nama_mobil, merk, harga_sewa, status_mobil) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, m.getPlatNomor());
            ps.setString(2, m.getNamaMobil());
            ps.setString(3, m.getMerk());
            ps.setDouble(4, m.getHargaSewa());
            ps.setString(5, m.getStatusMobil());

            ps.executeUpdate();
        }
    }

    // Mengubah status mobil
    public void ubahStatus(String platNomor, String status)
            throws SQLException {

        String sql = "UPDATE mobil SET status_mobil = ? WHERE plat_nomor = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, platNomor);

            ps.executeUpdate();
        }
    }

    // Menghapus mobil
    public void hapus(String platNomor) throws SQLException {
        String sql = "DELETE FROM mobil WHERE plat_nomor = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, platNomor);
            ps.executeUpdate();
        }
    }
}
