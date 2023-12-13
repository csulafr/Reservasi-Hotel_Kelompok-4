/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import javax.swing.*;
import controller.Koneksi;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class CheckIn extends javax.swing.JFrame {
    Connection conn = Koneksi.getKoneksi();
    ResultSet rs = null;
    PreparedStatement pst = null;
    private String Tipe_Kamar;
    private String Kasur;

    public ResultSet ambilHarga() throws SQLException {
        String sql = "SELECT harga FROM kamar WHERE no_kamar=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, txNoKamar.getText()); // Mengatur parameter nomor kamar

        return pst.executeQuery();
    }

    public void simpan() throws SQLException {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);

        // Set parameter lainnya
        pst.setString(1, txNama.getText());
        try {
            int nikValue = Integer.parseInt(txNIK.getText());
            pst.setInt(2, nikValue);
        } catch (NumberFormatException e) {
            // Handle the case where txNIK.getText() is not a valid integer
            JOptionPane.showMessageDialog(null, "NIK must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            // Optionally, you might want to clear the txNIK field or take other actions
            txNIK.setText("");
            return; // Stop further processing
        }

        pst.setString(3, txNoKamar.getText());
        pst.setString(4, tanggal.getText());

        // Ambil harga dari metode ambilHarga()
        ResultSet hasilQuery = ambilHarga();

        if (hasilQuery.next()) {
            String harga = hasilQuery.getString("harga");

            // Set harga dalam PreparedStatement
            pst.setString(5, harga);

            // Eksekusi pernyataan update

        } else {
            // Tangani jika tidak ada hasil yang ditemukan
            System.out.println("Tidak ada hasil ditemukan untuk nomor kamar yang spesifik.");
        }
        pst.setString(6, "Belum");
        pst.executeUpdate();
    }

    public void ubahStatus() throws SQLException {
        try {
            String sql = "update kamar set status=? where no_kamar=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "Booked"); // Parameter pertama: status
            pst.setString(2, txNoKamar.getText()); // Parameter kedua: nomor kamar
            int rowsAffected = pst.executeUpdate(); // Menggunakan executeUpdate, bukan executeQuery
            System.out.println(rowsAffected + " baris berhasil diubah.");
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Booking berhasil.",
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
                Home HM = new Home();
                HM.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal melakukan booking.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat melakukan booking.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ResultSet ambilHarga(String nomorKamar) throws SQLException {
        String sql = "SELECT harga FROM kamar WHERE no_kamar=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nomorKamar); // Set the room number as a parameter

        return pst.executeQuery();
    }

    /**
     * Creates new form CheckIn
     */
    public CheckIn() {
        initComponents();
        SimpleDateFormat dat = new SimpleDateFormat("yyyy/MM/dd ");
        Date d = new Date();
        tanggal.setText(dat.format(d));
        // tanggal.setIndex(cbTipeKamar.getSelectedIndex());
        Tipe_Kamar = "--Pilih";
        Kasur = "--Pilih";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txNama = new javax.swing.JTextField();
        txNIK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbTipeKamar = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbKasur = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tKosong = new javax.swing.JTable();
        txNoKamar = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tanggal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bBoking = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1030, 550));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(536, 500));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home (2).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton1)
                                .addContainerGap(32, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jButton1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jLabel1.setFont(new java.awt.Font("Catamaran Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHECK IN");

        txNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        txNIK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        jLabel2.setText("Nama");

        jLabel3.setText("NIK");

        cbTipeKamar.setBackground(new java.awt.Color(0, 0, 255));
        cbTipeKamar.setForeground(new java.awt.Color(255, 255, 255));
        cbTipeKamar
                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih", "Standard", "Superior" }));
        cbTipeKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipeKamarActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipe Kamar");

        cbKasur.setBackground(new java.awt.Color(0, 51, 255));
        cbKasur.setForeground(new java.awt.Color(255, 255, 255));
        cbKasur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih", "Single", "Double" }));
        cbKasur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKasurActionPerformed(evt);
            }
        });

        jLabel5.setText("Kasur");

        tKosong.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "No. Kamar", "Tipe Kamar", "Kasur", "Harga"
                }));
        jScrollPane1.setViewportView(tKosong);

        txNoKamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        bCari.setBackground(new java.awt.Color(0, 0, 255));
        bCari.setForeground(new java.awt.Color(255, 255, 255));
        bCari.setText("CARI");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        jLabel6.setText("No Kamar");

        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tanggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });

        jLabel7.setText("Tanggal");

        bBoking.setBackground(new java.awt.Color(0, 51, 255));
        bBoking.setForeground(new java.awt.Color(255, 255, 255));
        bBoking.setText("BOOKING");
        bBoking.setAutoscrolls(true);
        bBoking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBokingActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cross-small (1).png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(jLabel4)
                                                                .addComponent(jLabel2)
                                                                .addComponent(txNIK)
                                                                .addComponent(txNama)
                                                                .addComponent(jLabel3)
                                                                .addComponent(cbTipeKamar, 0, 242, Short.MAX_VALUE)
                                                                .addComponent(jLabel5)
                                                                .addComponent(cbKasur, 0,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                        .addComponent(bCari, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txNoKamar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                202,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel6))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(tanggal)))
                                                        .addComponent(bBoking, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 511,
                                                                Short.MAX_VALUE))
                                                .addContainerGap(90, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(261, 261, 261)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8)
                                                .addContainerGap()))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel8)))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txNama, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addGap(4, 4, 4)
                                                .addComponent(txNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbTipeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbKasur, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                Short.MAX_VALUE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bCari, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(bBoking, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE)));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBokingActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bBokingActionPerformed
        // kalo kode error
        try {
            if (txNoKamar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nomor Kamar harus diisi terlebih dahulu.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                simpan();
                ubahStatus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_bBokingActionPerformed

    private void cbTipeKamarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbTipeKamarActionPerformed
        Tipe_Kamar = (String) cbTipeKamar.getSelectedItem();

        // switch (cbTipeKamar.getSelectedIndex()){
        // case 0:
        // Tipe_Kamar = "Standard";
        // System.out.println("Indeks Terpilih untuk Tipe Kamar: 0" +
        // cbTipeKamar.getSelectedIndex());
        // break;
        // case 1:
        // Tipe_Kamar = "Superior";
        // System.out.println("Indeks Terpilih untuk Tipe Kamar: 1" +
        // cbTipeKamar.getSelectedIndex());
        // tanggal.setText(Tipe_Kamar);
        // break;
        // }
    }// GEN-LAST:event_cbTipeKamarActionPerformed

    public ResultSet cekKamar() {
        String sql = "SELECT no_kamar, tipe_kamar, kasur, harga FROM kamar WHERE tipe_kamar=? and kasur=? and status=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, Tipe_Kamar);
            pst.setString(2, Kasur);
            pst.setString(3, "Not Booking");
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // Modify ViewData to directly use ResultSet
    public void ViewData() {
        try {
            ResultSet resultSet = cekKamar();
            DefaultTableModel model = (DefaultTableModel) tKosong.getModel(); // Ambil model yang sudah ada

            // Kosongkan model yang ada sebelumnya
            model.setRowCount(0);

            while (resultSet.next()) {
                model.addRow(new Object[] {
                        resultSet.getString("no_kamar"),
                        resultSet.getString("tipe_kamar"),
                        resultSet.getString("kasur"),
                        resultSet.getString("harga")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bCariActionPerformed
        ViewData();
    }// GEN-LAST:event_bCariActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tanggalActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tanggalActionPerformed

    private void cbKasurActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbKasurActionPerformed
        Kasur = (String) cbKasur.getSelectedItem();
        System.out.println(Kasur);
        // switch (cbKasur.getSelectedIndex()) {
        // case 0:
        // Kasur = "Single";
        // System.out.println("Indeks Terpilih untuk Kasur: 0");
        // break;
        // case 1:
        // Kasur = "Double";
        // System.out.println("Indeks Terpilih untuk Kasur: 1");
        // break;
        // // Tambahkan lebih banyak kasus jika diperlukan
        // }
    }// GEN-LAST:event_cbKasurActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        Home HM = new Home();
        HM.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jButton1ActionPerformed

    int xx, xy;

    private void formMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }// GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }// GEN-LAST:event_formMouseDragged

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel8MouseClicked
        dispose();
    }// GEN-LAST:event_jLabel8MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckIn().setVisible(true);
            }
        });
    }
