package dao;

import java.sql.*;
import model.Pengembalian;
import util.Koneksi;

public class PengembalianDao {

    public String idBerikut() throws SQLException {
        try (Connection c = Koneksi.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM pengembalian")) {
            rs.next();
            return String.format("P%03d", rs.getInt(1) + 1);
        }
    }

    // Tiga perubahan sekaligus; kalau satu gagal, semuanya dibatalkan
    public void simpan(Pengembalian p) throws SQLException {
        try (Connection c = Koneksi.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps1 = c.prepareStatement(
                         "INSERT INTO pengembalian (id_pengembalian, id_transaksi, tanggal_pengembalian, kondisi_mobil, denda) VALUES (?,?,?,?,?)");
                 PreparedStatement ps2 = c.prepareStatement(
                         "UPDATE transaksi_sewa SET status = 'Selesai' WHERE id_transaksi = ?");
                 PreparedStatement ps3 = c.prepareStatement(
                         "UPDATE mobil SET status_mobil = 'Tersedia' WHERE plat_nomor = ?")) {
                ps1.setString(1, p.getIdPengembalian());
                ps1.setString(2, p.getTransaksi().getIdTransaksi());
                ps1.setDate(3, Date.valueOf(p.getTanggalPengembalian()));
                ps1.setString(4, p.getKondisiMobil());
                ps1.setDouble(5, p.getDenda());
                ps1.executeUpdate();

                ps2.setString(1, p.getTransaksi().getIdTransaksi());
                ps2.executeUpdate();

                ps3.setString(1, p.getTransaksi().getMobil().getPlatNomor());
                ps3.executeUpdate();

                c.commit();
            } catch (SQLException e) {
                c.rollback();
                throw e;
            }
        }
    }
}