/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.CustomerDao;
import model.Customer;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class TampilanCustomer extends javax.swing.JPanel {

    CustomerDao customerDao = new CustomerDao();
    
    public TampilanCustomer() {
        initComponents();
        txtNIK.setEditable(true);
        aturTabel();
        tampilkanData();
    }

    private void tampilkanData() {
        DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();

        model.setRowCount(0);

        try {
            ArrayList<Customer> data = customerDao.ambilSemua();

            for (Customer cu : data) {
                model.addRow(new Object[]{
                    cu.getIdCustomer(),
                    cu.getNamaCustomer(),
                    cu.getNoHp(),
                    cu.getAlamat()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mengambil data: " + e.getMessage());
        }
    }
    
    private void aturForm() {
    txtNIK.setEditable(false);
    }
    
    private void aturTabel() {
        // Warna isi tabel
        tblCustomer.setBackground(new java.awt.Color(50, 50, 50));

        // Warna tulisan
        tblCustomer.setForeground(java.awt.Color.WHITE);

        // Warna baris yang dipilih
        tblCustomer.setSelectionBackground(new java.awt.Color(50, 50, 50));
        tblCustomer.setSelectionForeground(java.awt.Color.WHITE);

        // Warna garis tabel
        tblCustomer.setGridColor(new java.awt.Color(50, 50, 50));

        // Tinggi baris
        tblCustomer.setRowHeight(35);

        // Warna header
        tblCustomer.getTableHeader().setBackground(
            new java.awt.Color(50, 50, 50)
        );

        tblCustomer.getTableHeader().setForeground(
            java.awt.Color.WHITE
        );

        // Font header
        tblCustomer.getTableHeader().setFont(
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
        );

        // Font isi tabel
        tblCustomer.setFont(
            new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12)
        );
        
        tblCustomer.setShowHorizontalLines(true);
        tblCustomer.setShowVerticalLines(false);

        tblCustomer.setIntercellSpacing(
            new java.awt.Dimension(0, 1)
        );

        tblCustomer.getTableHeader().setReorderingAllowed(false);
        tblCustomer.getTableHeader().setResizingAllowed(true);
        
        // Warna area kosong tabel
        tblCustomer.setFillsViewportHeight(true);
        tblCustomer.setBackground(new java.awt.Color(50, 50, 50));
        
        tblCustomer.setBackground(new java.awt.Color(50, 50, 50));
        tblCustomer.setForeground(java.awt.Color.WHITE);

        jScrollPane2.getViewport().setBackground(
            new java.awt.Color(50, 50, 50)
        );

        jScrollPane2.setBackground(
            new java.awt.Color(50, 50, 50)
        );
        
        // Mengatur header tabel
        javax.swing.table.JTableHeader header =
                tblCustomer.getTableHeader();

        javax.swing.table.DefaultTableCellRenderer renderer =
                new javax.swing.table.DefaultTableCellRenderer();

        renderer.setBackground(new java.awt.Color(50, 50, 50));
        renderer.setForeground(java.awt.Color.WHITE);
        renderer.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        // Garis pemisah judul
        renderer.setBorder(
            javax.swing.BorderFactory.createMatteBorder(
                0, 0, 1, 1,
                new java.awt.Color(100, 100, 100)
            )
        );

        header.setDefaultRenderer(renderer);
        header.setOpaque(true);
        header.setBackground(new java.awt.Color(50, 50, 50));
        header.setForeground(java.awt.Color.WHITE);
        // Garis pemisah tabel
        tblCustomer.setShowHorizontalLines(true);
        tblCustomer.setShowVerticalLines(true);

        tblCustomer.setGridColor(
            new java.awt.Color(100, 100, 100)
        );

        // Jarak antar sel
        tblCustomer.setIntercellSpacing(
            new java.awt.Dimension(1, 1)
        );
    }
    
    private void resetForm() {
        txtNIK.setText("");
        txtNIK.setEditable(true);

        txtNama.setText("");
        txtNoHP.setText("");
        txtAlamat.setText("");
        txtCariNIK.setText("");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCariNIK = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNIK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jdlCustomer = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();

        setBackground(new java.awt.Color(25, 25, 25));

        jPanel3.setBackground(new java.awt.Color(50, 50, 50));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cari Customer");

        txtCariNIK.setBackground(new java.awt.Color(25, 25, 25));
        txtCariNIK.setForeground(new java.awt.Color(255, 255, 255));
        txtCariNIK.addActionListener(this::txtCariNIKActionPerformed);

        btnCari.setBackground(new java.awt.Color(255, 255, 255));
        btnCari.setForeground(new java.awt.Color(0, 0, 0));
        btnCari.setText("Cari");
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.addActionListener(this::btnCariActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(408, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCariNIK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari)
                        .addGap(10, 10, 10))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addGap(18, 18, 18))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCari, txtCariNIK});

        jPanel2.setBackground(new java.awt.Color(50, 50, 50));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Detail Customer");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Customer (NIK)");

        txtNIK.setBackground(new java.awt.Color(25, 25, 25));
        txtNIK.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama");

        txtNama.setBackground(new java.awt.Color(25, 25, 25));
        txtNama.setForeground(new java.awt.Color(255, 255, 255));
        txtNama.addActionListener(this::txtNamaActionPerformed);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nomor Handphone");

        txtNoHP.setBackground(new java.awt.Color(25, 25, 25));
        txtNoHP.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Alamat");

        txtAlamat.setBackground(new java.awt.Color(25, 25, 25));
        txtAlamat.setForeground(new java.awt.Color(255, 255, 255));
        txtAlamat.addActionListener(this::txtAlamatActionPerformed);

        jLabel11.setBackground(new java.awt.Color(50, 50, 50));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Progress Promo");

        jLabel12.setBackground(new java.awt.Color(45, 45, 45));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("10/10 Transaksi Selesai");

        jLabel13.setBackground(new java.awt.Color(35, 35, 35));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Promo terakhir: belum pernah dipakai");

        jLabel14.setBackground(new java.awt.Color(102, 255, 102));
        jLabel14.setForeground(new java.awt.Color(0, 204, 51));
        jLabel14.setText("Berhak gratis 1 hari pada transaksi berikutnya");

        btnHapus.setBackground(new java.awt.Color(51, 51, 51));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(51, 51, 51));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        btnTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        btnTransaksi.setText("Transaksi");

        btnTambah.setBackground(new java.awt.Color(51, 51, 51));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(txtNIK))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoHP)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtAlamat)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate)))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapus, btnUpdate});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnHapus)
                            .addComponent(btnTambah))
                        .addGap(5, 5, 5)
                        .addComponent(btnTransaksi)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapus, btnUpdate});

        jdlCustomer.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jdlCustomer.setForeground(new java.awt.Color(255, 255, 255));
        jdlCustomer.setText("Customer");

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Nomor HP", "Alamat"
            }
        ));
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jdlCustomer)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jdlCustomer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariNIKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNIKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNIKActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        String nik = txtCariNIK.getText().trim();
        if (nik.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Masukkan NIK terlebih dahulu!");
            return;
        }

        try {
            Customer cu = customerDao.cariById(nik);

            if (cu != null) {
                txtNIK.setText(cu.getIdCustomer());
                txtNama.setText(cu.getNamaCustomer());
                txtNoHP.setText(cu.getNoHp());
                txtAlamat.setText(cu.getAlamat());

            } else {
                JOptionPane.showMessageDialog(this,
                        "Customer dengan NIK tersebut tidak ditemukan!");

                txtNIK.setText("");
                txtNama.setText("");
                txtNoHP.setText("");
                txtAlamat.setText("");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
    String nik = txtNIK.getText().trim();
    String nama = txtNama.getText().trim();
    String noHP = txtNoHP.getText().trim();
    String alamat = txtAlamat.getText().trim();

    // Validasi input kosong
    if (nik.isEmpty() || nama.isEmpty()
            || noHP.isEmpty() || alamat.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Semua data harus diisi!");
        return;
    }

    try {
        Customer cu = new Customer(
                nik, nama, noHP, alamat
        );

        customerDao.simpan(cu);

        JOptionPane.showMessageDialog(this,
                "Data customer berhasil ditambahkan!");

        tampilkanData();
        resetForm();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal menambahkan data: " + e.getMessage());
    }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
    int baris = tblCustomer.getSelectedRow();

    if (baris != -1) {
        txtNIK.setText(tblCustomer.getValueAt(baris, 0).toString());
        txtNIK.setEditable(false);
        txtNama.setText(tblCustomer.getValueAt(baris, 1).toString());
        txtNoHP.setText(tblCustomer.getValueAt(baris, 2).toString());
        txtAlamat.setText(tblCustomer.getValueAt(baris, 3).toString());
    }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    String nik = txtNIK.getText().trim();
    String nama = txtNama.getText().trim();
    String noHP = txtNoHP.getText().trim();
    String alamat = txtAlamat.getText().trim();

    if (nik.isEmpty() || nama.isEmpty()
            || noHP.isEmpty() || alamat.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Pilih data dan lengkapi semua form terlebih dahulu!");
        return;
    }

    try {
        Customer cu = new Customer(nik, nama, noHP, alamat);

        customerDao.update(cu);

        JOptionPane.showMessageDialog(this,
                "Data customer berhasil diperbarui!");

        tampilkanData();
        resetForm();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal memperbarui data: " + e.getMessage());
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
    String nik = txtNIK.getText().trim();

    if (nik.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Pilih customer yang ingin dihapus terlebih dahulu!");
        return;
    }

    int konfirmasi = JOptionPane.showConfirmDialog(this,
            "Apakah kamu yakin ingin menghapus customer dengan NIK " + nik + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

    if (konfirmasi == JOptionPane.YES_OPTION) {
        try {
            customerDao.hapus(nik);

            JOptionPane.showMessageDialog(this,
                    "Data customer berhasil dihapus!");

            tampilkanData();
            resetForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menghapus data: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnHapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jdlCustomer;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCariNIK;
    private javax.swing.JTextField txtNIK;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    // End of variables declaration//GEN-END:variables
}
