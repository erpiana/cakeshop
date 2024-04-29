/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by_22343004 Erpiana
 */


public class DataProduk {
    // Atribut-atribut data produk
    private int id_produk;
    private String nama_produk;
    private String jenis_produk;
    private int harga_produk;
    private String status_produk;
    
    // Konstruktor default untuk objek DataProduk
    public DataProduk() {
        this.id_produk = 0;
        this.nama_produk = null;
        this.jenis_produk = null;
        this.harga_produk = 0;
        this.status_produk = null;
    }
    
    public DataProduk(int id_produk, String nama_produk, String jenis_produk, int harga_produk, String status_produk) {
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.jenis_produk = jenis_produk;
        this.harga_produk = harga_produk;
        this.status_produk = status_produk;
    }
    
    // Metode-metode getter untuk mengakses nilai atribut
    public int getIdProduk() {
        return id_produk;
    }

    public String getNamaProduk() {
        return nama_produk;
    }
    
    public String getJenisProduk() {
        return jenis_produk;
    }
    
    public int getHargaProduk() {
        return harga_produk;
    }

    public String getStatusProduk() {
        return status_produk;
    }
    
   //Menambahkan data produk ke dalam database. 
   public void tambahDataProduk(int inputId, String inputNama, String inputJenis, int inputHarga, String inputStatus) {
    Connection koneksi = KoneksiDatabase.hubungkan();
    
        try {
            // nama id pada database mysql
            String NamaTabel = "produk";
            String kolom1 = "id_produk";
            String kolom2 = "nama_produk";
            String kolom3 = "jenis_produk";
            String kolom4 = "harga_produk";
            String kolom5 = "status_produk";

            String query = "INSERT INTO "+ NamaTabel+" (" + kolom1 + ", " + kolom2 + ", " + kolom3 + ", " + kolom4 + ", " + kolom5 + ") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement tambahData = koneksi.prepareStatement(query);

            tambahData.setInt(1, inputId);
            tambahData.setString(2, inputNama);
            tambahData.setString(3, inputJenis);
            tambahData.setInt(4, inputHarga);
            tambahData.setString(5, inputStatus);

            int affectedRows = tambahData.executeUpdate();

            // Menampilkan pesan berhasil atau gagal
            if (affectedRows > 0) {
                System.out.println("Data berhasil ditambahkan.");
            } else {
                System.out.println("Penambahan data gagal.");
            }
        } catch (SQLException e) {}
    }

   //Menghapus data produk dari database berdasarkan ID
   public void deleteDataProduk(int id) {
    // Melakukan koneksi ke database
    Connection koneksi = KoneksiDatabase.hubungkan();
    
        try {
            // nama id pada database mysql
            String kolom1 = "id_produk";
            String NamaTabel = "produk";
            
            String query = "DELETE FROM "+ NamaTabel + " WHERE " + kolom1 + " = ?";
            PreparedStatement deleteData = koneksi.prepareStatement(query);

            deleteData.setInt(1, id);

            // Menggunakan executeUpdate untuk operasi DELETE
            int affectedRows = deleteData.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Data dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("Gagal menghapus data atau data tidak ditemukan.");
            }
        } catch (SQLException e) {}
    }

   //Memperbarui data produk di database berdasarkan ID
    public void perbaruiDataProduk(int id, String inputNamaBaru, String inputJenisBaru, int inputHargaBaru, String inputStatusBaru) {
    Connection koneksi = KoneksiDatabase.hubungkan();

        try {
            // Nama kolom pada database Produk
            String NamaTabel = "produk";
            String KolomId = "id_produk";
            String KolomNama = "nama_produk";
            String KolomJenis = "jenis_produk";
            String KolomHarga = "harga_produk";
            String KolomStatus = "status_produk";
            

            String query = "UPDATE "+ NamaTabel + " SET " + KolomNama + " = ?, " + KolomJenis + " = ?, " + KolomHarga + " = ?, " + KolomStatus + " = ? WHERE " + KolomId + " = ?";
            PreparedStatement perbaruiData = koneksi.prepareStatement(query);

            perbaruiData.setString(1, inputNamaBaru);
            perbaruiData.setString(2, inputJenisBaru);
            perbaruiData.setInt(3, inputHargaBaru);
            perbaruiData.setString(4, inputStatusBaru);
            perbaruiData.setInt(5, id);

            // Menggunakan executeUpdate untuk operasi UPDATE
            int affectedRows = perbaruiData.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Data dengan ID " + id + " berhasil diperbarui.");
            } else {
                System.out.println("Gagal memperbarui data atau data tidak ditemukan.");
            }

        } catch (SQLException e) {}
    }
}