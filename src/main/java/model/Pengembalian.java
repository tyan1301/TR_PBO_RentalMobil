package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pengembalian {
    public static final double DENDA_PER_HARI = 50000;

    private String idPengembalian;
    private TransaksiSewa transaksi;
    private LocalDate tanggalPengembalian;
    private String kondisiMobil;
    private double denda;

    public Pengembalian(String idPengembalian, TransaksiSewa transaksi,
                        LocalDate tanggalPengembalian, String kondisiMobil) {
        this.idPengembalian = idPengembalian;
        this.transaksi = transaksi;
        this.tanggalPengembalian = tanggalPengembalian;
        this.kondisiMobil = kondisiMobil;
        this.denda = hitungDenda();
    }

    public int hariTerlambat() {
        long hari = ChronoUnit.DAYS.between(transaksi.getJatuhTempo(), tanggalPengembalian);
        return (int) Math.max(0, hari);
    }

    public double dendaTerlambat() {
        return hariTerlambat() * DENDA_PER_HARI;
    }

    public double dendaKondisi() {
        if (kondisiMobil.equals("Rusak Ringan")) return 50000;
        if (kondisiMobil.equals("Rusak Berat")) return 500000;
        return 0;                                      // Baik
    }

    public double hitungDenda() {
        return dendaTerlambat() + dendaKondisi();
    }

    public String getIdPengembalian() { return idPengembalian; }
    public TransaksiSewa getTransaksi() { return transaksi; }
    public LocalDate getTanggalPengembalian() { return tanggalPengembalian; }
    public String getKondisiMobil() { return kondisiMobil; }
    public double getDenda() { return denda; }
}