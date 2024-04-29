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

public class AmbilDataProduk {
    
    private int id_produk;
    private String nama_produk;
    private String jenis_produk;
    private int harga_produk;
    private String status_produk;
    
    // Konstruktor untuk inisialisasi objek AmbilDataProduk
    public AmbilDataProduk(int id_produk, String nama_produk, String jenis_produk, int harga_produk, String status_produk) {
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.jenis_produk = jenis_produk;
        this.harga_produk = harga_produk;
        this.status_produk = status_produk;
    }
    
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
    
    // Metode untuk mengambil data produk berdasarkan ID
    public static AmbilDataProduk ambilDataProdukById(int id) {
        Connection koneksi = KoneksiDatabase.hubungkan();
        AmbilDataProduk dataProduk = null;

        try {
            String query = "SELECT id_produk, nama_produk, jenis_produk, harga_produk, status_produk FROM produk WHERE id_produk = ?";
            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nama_produk = resultSet.getString("nama_produk");
                String jenis_produk = resultSet.getString("jenis_produk");
                int harga_produk = resultSet.getInt("harga_produk");
                String status_produk = resultSet.getString("status_produk");
                dataProduk = new AmbilDataProduk(id, nama_produk, jenis_produk, harga_produk, status_produk);
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data produk dari database. Kesalahan: " + e.getMessage());
        } finally {
            KoneksiDatabase.putuskanKoneksi(koneksi);
        }

        return dataProduk;
    }

    // Metode untuk mengambil semua data produk
    public static List<AmbilDataProduk> ambilSemuaDataProduk() {
        Connection koneksi = KoneksiDatabase.hubungkan();
        List<AmbilDataProduk> daftarDataProduk = new ArrayList<>();

        try {
            String query = "SELECT id_produk, nama_produk, jenis_produk, harga_produk, status_produk FROM produk";
            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_produk = resultSet.getInt("id_produk");
                String nama_produk = resultSet.getString("nama_produk");
                String jenis_produk = resultSet.getString("jenis_produk");
                int harga_produk = resultSet.getInt("harga_produk");
                String status_produk = resultSet.getString("status_produk");
                AmbilDataProduk dataProduk = new AmbilDataProduk(id_produk, nama_produk, jenis_produk, harga_produk, status_produk);
                daftarDataProduk.add(dataProduk);
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data produk dari database. Kesalahan: " + e.getMessage());
        } finally {
            KoneksiDatabase.putuskanKoneksi(koneksi);
        }

        return daftarDataProduk;
    }
}
