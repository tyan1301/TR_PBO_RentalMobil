package model;

import java.time.LocalDate;

public class TransaksiSewa {
    public static final String BERJALAN = "Berjalan";
    public static final String SELESAI = "Selesai";
    public static final String DIBATALKAN = "Dibatalkan";

    private String idTransaksi;
    private Customer customer;
    private Mobil mobil;
    private LocalDate tanggalSewa;
    private int lamaSewa;
    private String status = BERJALAN;
    private double totalBiaya;
    private boolean isGratis;

    public TransaksiSewa(String idTransaksi, Customer customer, Mobil mobil,
                         LocalDate tanggalSewa, int lamaSewa) {
        this.idTransaksi = idTransaksi;
        this.customer = customer;
        this.mobil = mobil;
        this.tanggalSewa = tanggalSewa;
        this.lamaSewa = lamaSewa;
    }

    public LocalDate getJatuhTempo() {
        return tanggalSewa.plusDays(lamaSewa);
    }

    public double hitungBiaya() {
        int hariBayar = lamaSewa;
        if (isGratis) {
            hariBayar = Math.max(0, lamaSewa - 1);   // 1 hari pertama gratis
        }
        totalBiaya = hariBayar * mobil.getHargaSewa();
        return totalBiaya;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public Customer getCustomer() { return customer; }
    public Mobil getMobil() { return mobil; }
    public LocalDate getTanggalSewa() { return tanggalSewa; }
    public int getLamaSewa() { return lamaSewa; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalBiaya() { return totalBiaya; }
    public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }
    public boolean isGratis() { return isGratis; }
    public void setGratis(boolean gratis) { this.isGratis = gratis; }

    public void setLamaSewa(int lamaSewa) {
    this.lamaSewa = lamaSewa;
    }
}