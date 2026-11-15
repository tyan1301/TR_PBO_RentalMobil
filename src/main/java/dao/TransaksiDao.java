package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Customer;
import model.Mobil;
import model.TransaksiSewa;
import util.Koneksi;

public class TransaksiDao {

    public ArrayList<TransaksiSewa> ambilSemua(ArrayList<Customer> customers,
                                               ArrayList<Mobil> mobils) throws SQLException {
        ArrayList<TransaksiSewa> hasil = new ArrayList<>();
        String sql = "SELECT * FROM transaksi_sewa";
        try (Connection c = Koneksi.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Customer cu = null;
                for (Customer x : customers) {
                    if (x.getIdCustomer().equals(rs.getString("id_customer"))) cu = x;
                }
                Mobil mo = null;
                for (Mobil x : mobils) {
                    if (x.getPlatNomor().equals(rs.getString("plat_nomor"))) mo = x;
                }
                if (cu == null || mo == null) continue;

                TransaksiSewa t = new TransaksiSewa(
                        rs.getString("id_transaksi"), cu, mo,
                        rs.getDate("tanggal_sewa").toLocalDate(),
                        rs.getInt("lama_sewa"));
                t.setStatus(rs.getString("status"));
                t.setTotalBiaya(rs.getDouble("total_biaya"));
                t.setGratis(rs.getBoolean("is_gratis"));
                hasil.add(t);
                cu.getRiwayat().add(t);      // riwayat customer ikut terisi (dipakai promo nanti)
            }
        }
        return hasil;
    }
}