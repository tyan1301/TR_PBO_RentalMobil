package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {
    private String idCustomer;            // berisi NIK
    private String namaCustomer;
    private String noHp;
    private String alamat;
    private LocalDate tglPromoTerakhir;   // null = belum pernah memakai promo
    private ArrayList<TransaksiSewa> riwayat = new ArrayList<>();

    public Customer(String idCustomer, String namaCustomer, String noHp, String alamat) {
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.noHp = noHp;
        this.alamat = alamat;
    }

    public static boolean nikValid(String nik) {
        return nik != null && nik.matches("\\d{16}");   // tepat 16 digit angka
    }

    public String getIdCustomer() { return idCustomer; }
    public String getNamaCustomer() { return namaCustomer; }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer = namaCustomer; }
    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public LocalDate getTglPromoTerakhir() { return tglPromoTerakhir; }
    public void setTglPromoTerakhir(LocalDate tgl) { this.tglPromoTerakhir = tgl; }
    public ArrayList<TransaksiSewa> getRiwayat() { return riwayat; }
}