package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Customer;
import util.Koneksi;

public class CustomerDao {

    // 1. MENGAMBIL SEMUA DATA CUSTOMER
    public ArrayList<Customer> ambilSemua()
            throws SQLException {

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
                    rs.getString("alamat")
                );

                Date d = rs.getDate("tgl_promo_terakhir");

                if (d != null) {
                    cu.setTglPromoTerakhir(d.toLocalDate());
                }

                hasil.add(cu);
            }
        }

        return hasil;
    }
    
    public Customer cariById(String nik) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id_customer = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nik);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer cu = new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama_customer"),
                        rs.getString("no_hp"),
                        rs.getString("alamat")
                    );

                    Date d = rs.getDate("tgl_promo_terakhir");

                    if (d != null) {
                        cu.setTglPromoTerakhir(d.toLocalDate());
                    }

                    return cu;
                }
            }
        }

        return null;
    }

    // 2. MENYIMPAN CUSTOMER
    public void simpan(Customer cu)
            throws SQLException {

        String sql = "INSERT INTO customer " +
            "(id_customer, nama_customer, no_hp, alamat) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cu.getIdCustomer());
            ps.setString(2, cu.getNamaCustomer());
            ps.setString(3, cu.getNoHp());
            ps.setString(4, cu.getAlamat());

            ps.executeUpdate();
        }
    }

    // 3. MENCARI CUSTOMER BERDASARKAN ID
    public Customer cari(String id)
            throws SQLException {

        String sql = "SELECT * FROM customer " +
                     "WHERE id_customer = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Customer cu = new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama_customer"),
                        rs.getString("no_hp"),
                        rs.getString("alamat")
                    );

                    Date d = rs.getDate("tgl_promo_terakhir");

                    if (d != null) {
                        cu.setTglPromoTerakhir(d.toLocalDate());
                    }

                    return cu;
                }
            }
        }

        return null;
    }

    // 4. MENGEDIT DATA CUSTOMER
    public void edit(Customer cu)
            throws SQLException {

        String sql = "UPDATE customer SET " +
            "nama_customer = ?, " +
            "no_hp = ?, " +
            "alamat = ? " +
            "WHERE id_customer = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cu.getNamaCustomer());
            ps.setString(2, cu.getNoHp());
            ps.setString(3, cu.getAlamat());
            ps.setString(4, cu.getIdCustomer());

            ps.executeUpdate();
        }
    }

    public void hapus(String id)
            throws SQLException {

        String sql = "DELETE FROM customer " +
                     "WHERE id_customer = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, id);

            ps.executeUpdate();
        }
    }
    
    public void update(Customer cu) throws SQLException {
        String sql = "UPDATE customer SET nama_customer = ?, no_hp = ?, alamat = ? WHERE id_customer = ?";

        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cu.getNamaCustomer());
            ps.setString(2, cu.getNoHp());
            ps.setString(3, cu.getAlamat());
            ps.setString(4, cu.getIdCustomer());

            ps.executeUpdate();
        }
    }
}