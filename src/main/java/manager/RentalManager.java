package manager;

import dao.CustomerDao;
import dao.MobilDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import model.Mobil;
import model.Pengembalian;
import model.TransaksiSewa;

public class RentalManager {
    private ArrayList<Customer> daftarCustomer = new ArrayList<>();
    private ArrayList<Mobil> daftarMobil = new ArrayList<>();
    private ArrayList<TransaksiSewa> daftarTransaksi = new ArrayList<>();
    private ArrayList<Pengembalian> daftarPengembalian = new ArrayList<>();

    private CustomerDao customerDao = new CustomerDao();
    private MobilDao mobilDao = new MobilDao();

    // Saat program dimulai: database -> ArrayList
    public RentalManager() throws SQLException {
        daftarCustomer = customerDao.ambilSemua();
        daftarMobil = mobilDao.ambilSemua();
    }

    public Customer cariCustomer(String nik) {
        for (Customer c : daftarCustomer) {
            if (c.getIdCustomer().equals(nik)) {
                return c;
            }
        }
        return null;   // tidak ketemu = customer baru
    }

    public String tambahCustomer(Customer c) {
        if (c.getNamaCustomer().isBlank() || c.getNoHp().isBlank() || c.getAlamat().isBlank()) {
            return "Data harus diisi lengkap";
        }
        if (!Customer.nikValid(c.getIdCustomer())) {
            return "NIK harus 16 digit angka";
        }
        if (cariCustomer(c.getIdCustomer()) != null) {
            return "NIK sudah terdaftar";
        }
        try {
            customerDao.simpan(c);      // simpan ke database
            daftarCustomer.add(c);      // dan ke ArrayList
            return "OK";
        } catch (SQLException e) {
            return "Gagal menyimpan: " + e.getMessage();
        }
    }

    public ArrayList<Customer> getDaftarCustomer() { return daftarCustomer; }
    public ArrayList<Mobil> getDaftarMobil() { return daftarMobil; }
}