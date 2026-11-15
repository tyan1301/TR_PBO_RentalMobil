package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Mobil;
import util.Koneksi;

public class MobilDao {

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
                        rs.getDouble("harga_sewa"));
                m.setStatusMobil(rs.getString("status_mobil"));
                hasil.add(m);
            }
        }
        return hasil;
    }

    public void simpan(Mobil m) throws SQLException {
        String sql = "INSERT INTO mobil (plat_nomor, nama_mobil, merk, harga_sewa, status_mobil) VALUES (?,?,?,?,?)";
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
}