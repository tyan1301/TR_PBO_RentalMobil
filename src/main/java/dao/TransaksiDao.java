package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Customer;
import model.Mobil;
import model.TransaksiSewa;
import util.Koneksi;

public class TransaksiDao {

    public ArrayList<TransaksiSewa> ambilSemua(
            ArrayList<Customer> customers,
            ArrayList<Mobil> mobils) throws SQLException {

        ArrayList<TransaksiSewa> hasil = new ArrayList<>();

        // Kosongkan riwayat customer agar tidak duplikat
        for (Customer cu : customers) {
            cu.getRiwayat().clear();
        }

        String sql = "SELECT * FROM transaksi_sewa";

        try (Connection c = Koneksi.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Customer cu = null;

                for (Customer x : customers) {
                    if (x.getIdCustomer().equals(
                            rs.getString("id_customer"))) {
                        cu = x;
                        break;
                    }
                }

                Mobil mo = null;

                for (Mobil x : mobils) {
                    if (x.getPlatNomor().equals(
                            rs.getString("plat_nomor"))) {
                        mo = x;
                        break;
                    }
                }

                if (cu == null || mo == null) {
                    continue;
                }

                Date tanggal = rs.getDate("tanggal_sewa");

                if (tanggal == null) {
                    continue;
                }

                TransaksiSewa t = new TransaksiSewa(
                        rs.getString("id_transaksi"),
                        cu,
                        mo,
                        tanggal.toLocalDate(),
                        rs.getInt("lama_sewa")
                );

                t.setStatus(rs.getString("status"));
                t.setTotalBiaya(rs.getDouble("total_biaya"));
                t.setGratis(rs.getBoolean("is_gratis"));

                hasil.add(t);

                // Masukkan transaksi ke riwayat customer
                cu.getRiwayat().add(t);
            }
        }

        return hasil;
    }
    
    public TransaksiSewa cariById(String idTransaksi, ArrayList<Customer> customers, ArrayList<Mobil> mobils) throws SQLException {

        String sql = "SELECT * FROM transaksi_sewa WHERE id_transaksi = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, idTransaksi);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Date tanggal = rs.getDate("tanggal_sewa");
                    if (tanggal == null) {
                        return null;
                    }

                    Customer cu = null;
                    for (Customer x : customers) {
                        if (x.getIdCustomer().equals(rs.getString("id_customer"))) {
                            cu = x;
                            break;
                        }
                    }

                    Mobil mo = null;
                    for (Mobil x : mobils) {
                        if (x.getPlatNomor().equals(rs.getString("plat_nomor"))) {
                            mo = x;
                            break;
                        }
                    }

                    if (cu == null || mo == null) {
                        return null;
                    }
                   
                    TransaksiSewa t = new TransaksiSewa(
                            rs.getString("id_transaksi"),
                            cu,
                            mo,
                            tanggal.toLocalDate(),
                            rs.getInt("lama_sewa")
                    );

                    t.setStatus(rs.getString("status"));
                    t.setTotalBiaya(rs.getDouble("total_biaya"));
                    t.setGratis(rs.getBoolean("is_gratis"));

                    return t;
                }
            }
        }

        return null;
    }
    
    public boolean tambah(TransaksiSewa t) throws SQLException {

        String sql = "INSERT INTO transaksi_sewa "
                + "(id_transaksi, id_customer, plat_nomor, tanggal_sewa, "
                + "lama_sewa, status, total_biaya, is_gratis) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, t.getIdTransaksi());
            ps.setString(2, t.getCustomer().getIdCustomer());
            ps.setString(3, t.getMobil().getPlatNomor());
            ps.setDate(4, Date.valueOf(t.getTanggalSewa()));
            ps.setInt(5, t.getLamaSewa());
            ps.setString(6, t.getStatus());
            ps.setDouble(7, t.hitungBiaya());
            ps.setBoolean(8, t.isGratis());

            boolean berhasil = ps.executeUpdate() > 0;
            if (berhasil) {
                t.getCustomer().getRiwayat().add(t);
            }

            return berhasil;
        }
    }
    
    public boolean update(TransaksiSewa t, ArrayList<Customer> customers, ArrayList<Mobil> mobils) throws SQLException {

        TransaksiSewa lama = cariById(
                t.getIdTransaksi(),
                customers,
                mobils
        );

        String sql = "UPDATE transaksi_sewa SET "
                + "id_customer = ?, "
                + "plat_nomor = ?, "
                + "tanggal_sewa = ?, "
                + "lama_sewa = ?, "
                + "status = ?, "
                + "total_biaya = ?, "
                + "is_gratis = ? "
                + "WHERE id_transaksi = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, t.getCustomer().getIdCustomer());
            ps.setString(2, t.getMobil().getPlatNomor());
            ps.setDate(3, Date.valueOf(t.getTanggalSewa()));
            ps.setInt(4, t.getLamaSewa());
            ps.setString(5, t.getStatus());
            ps.setDouble(6, t.hitungBiaya());
            ps.setBoolean(7, t.isGratis());
            ps.setString(8, t.getIdTransaksi());

            boolean berhasil = ps.executeUpdate() > 0;

            if (berhasil) {
                if (lama != null) {
                    lama.getCustomer().getRiwayat().removeIf(
                            x -> x.getIdTransaksi().equals(t.getIdTransaksi())
                    );
                }

                t.getCustomer().getRiwayat().add(t);
            }

            return berhasil;
        }
    }
    
    public boolean hapus(String idTransaksi,
            ArrayList<Customer> customers,
            ArrayList<Mobil> mobils) throws SQLException {

        TransaksiSewa t = cariById(idTransaksi, customers, mobils);

        if (t == null) {
            return false;
        }

        String sql = "DELETE FROM transaksi_sewa WHERE id_transaksi = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, idTransaksi);

            boolean berhasil = ps.executeUpdate() > 0;

            if (berhasil) {
                t.getCustomer().getRiwayat().removeIf(
                        x -> x.getIdTransaksi().equals(idTransaksi)
                );
            }

            return berhasil;
        }
    }
}