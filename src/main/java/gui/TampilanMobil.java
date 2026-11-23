package gui;

import dao.MobilDao;
import model.Mobil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class TampilanMobil extends javax.swing.JPanel {

    public TampilanMobil() {
        initComponents();

        panelDaftarMobil.setLayout(
                new BoxLayout(panelDaftarMobil, BoxLayout.Y_AXIS)
        );

        panelDaftarMobil.setBackground(new Color(35, 35, 35));
        scrollDaftarMobil.getViewport().setBackground(new Color(35, 35, 35));

        muatDataMobil("");
    }

    private void muatDataMobil(String keyword) {
        try {
            MobilDao dao = new MobilDao();
            ArrayList<Mobil> daftar;

            if (keyword == null || keyword.trim().isEmpty()) {
                daftar = dao.ambilSemua();
            } else {
                daftar = dao.cariMerk(keyword.trim());
            }

            panelDaftarMobil.removeAll();

            if (daftar.isEmpty()) {
                JLabel kosong = new JLabel("Data mobil tidak ditemukan");
                kosong.setForeground(Color.WHITE);
                panelDaftarMobil.add(kosong);
            } else {
                for (Mobil m : daftar) {
                    panelDaftarMobil.add(buatKartuMobil(m));
                    panelDaftarMobil.add(Box.createVerticalStrut(15));
                }
            }

            panelDaftarMobil.revalidate();
            panelDaftarMobil.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mengambil data mobil: " + e.getMessage());
        }
    }

    private JPanel buatKartuMobil(Mobil m) {

        JPanel kartu = new JPanel(new GridLayout(1, 5, 10, 5));

        kartu.setBackground(new Color(43, 43, 43));
        kartu.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        kartu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel nama = new JLabel(m.getNamaMobil());
        nama.setForeground(Color.WHITE);

        JLabel plat = new JLabel(m.getPlatNomor());
        plat.setForeground(Color.WHITE);

        JLabel harga = new JLabel(
                "Rp" + String.format("%,.0f", m.getHargaSewa()) + " / hari"
        );
        harga.setForeground(Color.WHITE);

        JLabel status = new JLabel(m.getStatusMobil());
        status.setForeground(Color.YELLOW);

        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");

        // Panel tombol
        JPanel panelTombol = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 8, 0)
        );
        panelTombol.setOpaque(false);

        // Ukuran tombol
        btnEdit.setPreferredSize(new Dimension(65, 27));
        btnHapus.setPreferredSize(new Dimension(65, 27));

        // Font tombol
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnHapus.setFont(new Font("Segoe UI", Font.BOLD, 10));

        // Warna tombol
        btnEdit.setBackground(Color.WHITE);
        btnHapus.setBackground(new Color(51, 51, 51));

        btnEdit.setForeground(Color.BLACK);
        btnHapus.setForeground(Color.WHITE);

        // Hilangkan border bawaan
        btnEdit.setFocusPainted(false);
        btnHapus.setFocusPainted(false);
        btnEdit.setBorderPainted(false);
        btnHapus.setBorderPainted(false);

        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);

        // EDIT DATA MOBIL
       btnEdit.addActionListener(e -> {

    JTextField txtPlat = new JTextField(m.getPlatNomor());
    txtPlat.setEditable(false);
    txtPlat.setBackground(new Color(220, 220, 220));

    JTextField txtNama = new JTextField(m.getNamaMobil());
    JTextField txtMerk = new JTextField(m.getMerk());
    JTextField txtHarga = new JTextField(
            String.valueOf(m.getHargaSewa())
    );

    JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

    form.add(new JLabel("Plat Nomor"));
    form.add(txtPlat);

    form.add(new JLabel("Nama Mobil"));
    form.add(txtNama);

    form.add(new JLabel("Merk"));
    form.add(txtMerk);

    form.add(new JLabel("Harga Sewa"));
    form.add(txtHarga);

    int pilihan = JOptionPane.showConfirmDialog(
            this,
            form,
            "Edit Data Mobil",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (pilihan == JOptionPane.OK_OPTION) {

        try {
            String platBaru = txtPlat.getText().trim();
            String namaBaru = txtNama.getText().trim();
            String merkBaru = txtMerk.getText().trim();
            String teksHarga = txtHarga.getText().trim();

            if (platBaru.isEmpty() || namaBaru.isEmpty()
                    || merkBaru.isEmpty() || teksHarga.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Semua data harus diisi!");
                return;
            }

            double hargaBaru = Double.parseDouble(teksHarga);

            if (hargaBaru <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Harga sewa harus lebih dari 0!");
                return;
            }

            Mobil mobilBaru = new Mobil(
                    platBaru,
                    namaBaru,
                    merkBaru,
                    hargaBaru
            );

            mobilBaru.setStatusMobil(m.getStatusMobil());

            MobilDao dao = new MobilDao();
            dao.updateData(m.getPlatNomor(), mobilBaru);

            JOptionPane.showMessageDialog(this,
                    "Data mobil berhasil diperbarui!");

            muatDataMobil("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Harga sewa harus berupa angka!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mengubah data mobil: " + ex.getMessage());
        }
    }
});


        // HAPUS DATA MOBIL
 
        btnHapus.addActionListener(e -> {

            int konfirmasi = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin menghapus mobil "
                    + m.getNamaMobil() + "?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );

            if (konfirmasi == JOptionPane.YES_OPTION) {
                try {
                    MobilDao dao = new MobilDao();
                    dao.hapus(m.getPlatNomor());

                    JOptionPane.showMessageDialog(this,
                            "Mobil berhasil dihapus!");

                    muatDataMobil("");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Gagal menghapus mobil: "
                            + ex.getMessage());
                }
            }
        });

        kartu.add(nama);
        kartu.add(plat);
        kartu.add(harga);
        kartu.add(status);
        kartu.add(panelTombol);

        return kartu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        scrollDaftarMobil = new javax.swing.JScrollPane();
        panelDaftarMobil = new javax.swing.JPanel();

        setBackground(new java.awt.Color(35, 35, 35));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(245, 245, 245));
        jLabel18.setText("Daftar Mobil");

        jPanel3.setBackground(new java.awt.Color(50, 50, 50));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cari Merk Mobil");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Cari");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDaftarMobilLayout = new javax.swing.GroupLayout(panelDaftarMobil);
        panelDaftarMobil.setLayout(panelDaftarMobilLayout);
        panelDaftarMobilLayout.setHorizontalGroup(
            panelDaftarMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 803, Short.MAX_VALUE)
        );
        panelDaftarMobilLayout.setVerticalGroup(
            panelDaftarMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        scrollDaftarMobil.setViewportView(panelDaftarMobil);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollDaftarMobil))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(scrollDaftarMobil, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
         muatDataMobil(jTextField1.getText());
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        muatDataMobil(jTextField1.getText());
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelDaftarMobil;
    private javax.swing.JScrollPane scrollDaftarMobil;
    // End of variables declaration//GEN-END:variables
}
