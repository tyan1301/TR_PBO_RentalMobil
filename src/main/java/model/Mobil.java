package model;

public class Mobil {
    public static final String TERSEDIA = "Tersedia";
    public static final String DISEWA = "Disewa";

    private String platNomor;
    private String namaMobil;
    private String merk;
    private double hargaSewa;
    private String statusMobil = TERSEDIA;

    public Mobil(String platNomor, String namaMobil, String merk, double hargaSewa) {
        this.platNomor = platNomor;
        this.namaMobil = namaMobil;
        this.merk = merk;
        this.hargaSewa = hargaSewa;
    }

    public String getPlatNomor() { return platNomor; }
    public String getNamaMobil() { return namaMobil; }
    public String getMerk() { return merk; }
    public double getHargaSewa() { return hargaSewa; }
    public String getStatusMobil() { return statusMobil; }
    public void setStatusMobil(String statusMobil) { this.statusMobil = statusMobil; }
}