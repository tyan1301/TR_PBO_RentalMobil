package dao;

import dao.CustomerDao;
import dao.MobilDao;
import model.Customer;
import model.Mobil;

public class tes {
    public static void main(String[] args) {
        try {
            for (Customer c : new CustomerDao().ambilSemua()) {
                System.out.println(c.getIdCustomer() + " | " + c.getNamaCustomer()
                        + " | " + c.getAlamat());
            }
            for (Mobil m : new MobilDao().ambilSemua()) {
                System.out.println(m.getPlatNomor() + " | " + m.getNamaMobil()
                        + " | Rp" + m.getHargaSewa() + " | " + m.getStatusMobil());
            }
        } catch (Exception e) {
            System.out.println("Gagal: " + e.getMessage());
        }
    }
}