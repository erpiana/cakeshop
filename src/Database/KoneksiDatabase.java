/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by_22343004 Erpiana
 */

public class KoneksiDatabase {
    // URL template untuk koneksi ke database MySQL
    private static final String URL_TEMPLATE = "jdbc:mysql://%s:%s/%s";
     // Konfigurasi koneksi ke database
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String NAMA_DATABASE = "cakeshop";
    private static final String PENGGUNA = "root";
    private static final String KATA_SANDI = "";

    //Metode untuk membuka koneksi ke database MySQL.
    public static Connection hubungkan() {
        Connection koneksi = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format(URL_TEMPLATE, HOST, PORT, NAMA_DATABASE);
            koneksi = DriverManager.getConnection(url, PENGGUNA, KATA_SANDI);

            // Menampilkan pesan jika koneksi berhasil
            if (koneksi != null) {
                System.out.println("Berhasil terhubung ke database.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Gagal terhubung ke database.");
        }

        return koneksi;
    }

    //Metode untuk menutup koneksi ke database.
    public static void putuskanKoneksi(Connection koneksi) {
        if (koneksi != null) {
            try {
                koneksi.close();
                System.out.println("Koneksi ke database ditutup.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Gagal menutup koneksi ke database.");
            }
        }
    }
}