package manager;

import dao.CustomerDao;
import dao.MobilDao;
import dao.PengembalianDao;
import dao.TransaksiDao;
import java.sql.SQLException;
import java.time.LocalDate;
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
        daftarTransaksi = transaksiDao.ambilSemua(daftarCustomer, daftarMobil);
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
    
    public String tambahMobil(Mobil m) {
        if (m.getPlatNomor().isBlank() || m.getNamaMobil().isBlank() || m.getMerk().isBlank()) {
            return "Data harus diisi lengkap";
        }
        if (m.getHargaSewa() <= 0) {
            return "Harga sewa harus lebih dari 0";
        }
        for (Mobil x : daftarMobil) {
            if (x.getPlatNomor().equalsIgnoreCase(m.getPlatNomor())) {
                return "Plat nomor sudah terdaftar";
            }
        }
        try {
            mobilDao.simpan(m);
            daftarMobil.add(m);
            return "OK";
        } catch (SQLException e) {
            return "Gagal menyimpan: " + e.getMessage();
        }
    }
    
    public ArrayList<TransaksiSewa> cariTransaksiAktif(String nik) {
        ArrayList<TransaksiSewa> hasil = new ArrayList<>();
        for (TransaksiSewa t : daftarTransaksi) {
            if (t.getCustomer().getIdCustomer().equals(nik)
                    && t.getStatus().equals(TransaksiSewa.BERJALAN)) {
                hasil.add(t);
            }
        }
        return hasil;
    }

    public String kembalikan(TransaksiSewa t, LocalDate tanggal, String kondisi) {
        if (!t.getStatus().equals(TransaksiSewa.BERJALAN)) {
            return "Transaksi ini sudah selesai";
        }
        if (tanggal.isBefore(t.getTanggalSewa())) {
            return "Tanggal pengembalian tidak boleh sebelum tanggal sewa";
        }
        try {
            Pengembalian p = new Pengembalian(pengembalianDao.idBerikut(), t, tanggal, kondisi);
            pengembalianDao.simpan(p);                       // database
            t.setStatus(TransaksiSewa.SELESAI);              // ArrayList ikut diperbarui
            t.getMobil().setStatusMobil(Mobil.TERSEDIA);
            daftarPengembalian.add(p);
            return "OK";
        } catch (SQLException e) {
            return "Gagal menyimpan: " + e.getMessage();
        }
    }
    
    private TransaksiDao transaksiDao = new TransaksiDao();
    private PengembalianDao pengembalianDao = new PengembalianDao();

    public ArrayList<Customer> getDaftarCustomer() { return daftarCustomer; }
    public ArrayList<Mobil> getDaftarMobil() { return daftarMobil; }
}