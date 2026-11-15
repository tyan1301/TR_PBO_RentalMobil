package util;

import manager.RentalManager;
import manager.RentalManager;
import model.Customer;

public class tesMana {
    public static void main(String[] args) {
        try {
            RentalManager rm = new RentalManager();

            System.out.println("Jumlah customer: " + rm.getDaftarCustomer().size());
            System.out.println("Jumlah mobil: " + rm.getDaftarMobil().size());

            Customer budi = rm.cariCustomer("3374010101900001");
            System.out.println("Cari Budi: " + (budi != null ? budi.getNamaCustomer() : "tidak ketemu"));

            System.out.println(rm.tambahCustomer(new Customer("123", "Tes", "0811", "Depok")));
            System.out.println(rm.tambahCustomer(new Customer("3374010101900001", "Tes", "0811", "Depok")));
            System.out.println(rm.tambahCustomer(new Customer("3374010303930003", "Andi", "083456789012", "Depok")));
        } catch (Exception e) {
            System.out.println("Gagal: " + e.getMessage());
        }
    }
}