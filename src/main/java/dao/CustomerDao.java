package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Customer;
import util.Koneksi;

public class CustomerDao {

    public ArrayList<Customer> ambilSemua() throws SQLException {
        ArrayList<Customer> hasil = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection c = Koneksi.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Customer cu = new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama_customer"),
                        rs.getString("no_hp"),
                        rs.getString("alamat"));
                Date d = rs.getDate("tgl_promo_terakhir");
                if (d != null) cu.setTglPromoTerakhir(d.toLocalDate());
                hasil.add(cu);
            }
        }
        return hasil;
    }

    public void simpan(Customer cu) throws SQLException {
        String sql = "INSERT INTO customer (id_customer, nama_customer, no_hp, alamat) VALUES (?,?,?,?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cu.getIdCustomer());
            ps.setString(2, cu.getNamaCustomer());
            ps.setString(3, cu.getNoHp());
            ps.setString(4, cu.getAlamat());
            ps.executeUpdate();
        }
    }
}