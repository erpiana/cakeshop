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

public class AmbilDataAdmin {
   // Metode untuk mengambil semua data admin
    public static List<DataAdmin> ambilSemuaDataAdmin() {
        List<DataAdmin> daftarAdmin = new ArrayList<>();

        try (Connection koneksi = KoneksiDatabase.hubungkan();
             PreparedStatement preparedStatement = koneksi.prepareStatement("SELECT * FROM admin");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Ambil data dari setiap baris
                String nama_pengguna = resultSet.getString("nama_pengguna");
                String kata_sandi = resultSet.getString("kata_sandi");
                // Tambahkan ke dalam daftarAdmin
                daftarAdmin.add(new DataAdmin(nama_pengguna, kata_sandi));
            }
        } catch (SQLException e) {}

        return daftarAdmin;
    }

    // Kelas untuk merepresentasikan data admin
    public static class DataAdmin {
        private final String nama_pengguna;
        private final String kata_sandi;

        public DataAdmin(String nama_pengguna, String kata_sandi) {
            this.nama_pengguna = nama_pengguna;
            this.kata_sandi = kata_sandi;
        }

        public String getNama() {
            return nama_pengguna;
        }

        public String getSandi() {
            return kata_sandi;
        }
    }
}