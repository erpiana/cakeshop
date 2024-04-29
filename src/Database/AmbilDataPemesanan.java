package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmbilDataPemesanan {
    private int id_pemesanan;
    private int jumlah_pemesanan;
    private int total_hg_pemesanan;
    private String tanggal_pemesanan; 

    // Konstruktor untuk inisialisasi objek AmbilDataPemesanan
    public AmbilDataPemesanan(int id_pemesanan, int jumlah_pemesanan, int total_hg_pemesanan, String tanggal_pemesanan) {
        this.id_pemesanan = id_pemesanan;
        this.jumlah_pemesanan = jumlah_pemesanan;
        this.total_hg_pemesanan = total_hg_pemesanan;
        this.tanggal_pemesanan = tanggal_pemesanan;
    }

    public int getIdPemesanan() {
        return id_pemesanan;
    }

    public int getJumlahPemesanan() {
        return jumlah_pemesanan;
    }

    public int getTotalHgPemesanan() {
        return total_hg_pemesanan;
    }

    public String getTanggalPemesanan() {
        return tanggal_pemesanan;
    }

    // Metode untuk mengambil data pemesanan berdasarkan ID
    public static AmbilDataPemesanan ambilDataPemesananById(int id) {
        Connection koneksi = KoneksiDatabase.hubungkan();
        AmbilDataPemesanan dataPemesanan = null;

        try {
            // Query SQL untuk mengambil data pemesanan berdasarkan ID
            String query = "SELECT id_pemesanan, jumlah_pemesanan, total_hg_pemesanan, tanggal_pemesanan FROM pemesanan WHERE id_pemesanan = ?";
            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int jumlahPemesanan = resultSet.getInt("jumlah_pemesanan");
                int totalHgPemesanan = resultSet.getInt("total_hg_pemesanan");
                String tanggalPemesanan = resultSet.getString("tanggal_pemesanan");
                dataPemesanan = new AmbilDataPemesanan(id, jumlahPemesanan, totalHgPemesanan, tanggalPemesanan);
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data pemesanan dari database. Kesalahan: " + e.getMessage());
        } finally {
            KoneksiDatabase.putuskanKoneksi(koneksi);
        }

        return dataPemesanan;
    }

    // Metode untuk mengambil semua data pemesanan
    public static List<AmbilDataPemesanan> ambilSemuaDataPemesanan() {
        Connection koneksi = KoneksiDatabase.hubungkan();
        List<AmbilDataPemesanan> daftarDataPemesanan = new ArrayList<>();

        try {
            // Query SQL untuk mengambil semua data pemesanan
            String query = "SELECT id_pemesanan, jumlah_pemesanan, total_hg_pemesanan, tanggal_pemesanan FROM pemesanan";
            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPemesanan = resultSet.getInt("id_pemesanan");
                int jumlahPemesanan = resultSet.getInt("jumlah_pemesanan");
                int totalHgPemesanan = resultSet.getInt("total_hg_pemesanan");
                String tanggalPemesanan = resultSet.getString("tanggal_pemesanan");
                AmbilDataPemesanan dataPemesanan = new AmbilDataPemesanan(idPemesanan, jumlahPemesanan, totalHgPemesanan, tanggalPemesanan);
                daftarDataPemesanan.add(dataPemesanan);
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data pemesanan dari database. Kesalahan: " + e.getMessage());
        } finally {
            KoneksiDatabase.putuskanKoneksi(koneksi);
        }
        return daftarDataPemesanan;
    }
    
    // Metode untuk menambahkan data pemesanan baru
    public static void tambahDataPemesanan(int jumlahPemesanan, int totalHarga) {
        Connection koneksi = KoneksiDatabase.hubungkan();

        try {
            String query = "INSERT INTO pemesanan (jumlah_pemesanan, total_hg_pemesanan) VALUES (?, ?)";
            PreparedStatement preparedStatement = koneksi.prepareStatement(query);
            preparedStatement.setInt(1, jumlahPemesanan);
            preparedStatement.setInt(2, totalHarga);
            preparedStatement.executeUpdate();
            System.out.println("Data pemesanan berhasil ditambahkan.");
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan data pemesanan ke database. Kesalahan: " + e.getMessage());
        } finally {
            KoneksiDatabase.putuskanKoneksi(koneksi);
        }
    }
}