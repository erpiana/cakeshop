/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cakeshop;

import Database.AmbilDataPemesanan;
import Database.AmbilDataProduk;
import Database.DataProduk;
import Database.KoneksiDatabase;
import static cakeshop.HalamanLoginKontroller.konfirmasi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Created by_22343004 Erpiana
 */

public class HalamanUtamaKontroller implements Initializable {
    //Fungsi Id untuk tampilan Grafik
    @FXML
    private AreaChart<String, Integer> Tampilan_Grafik;
    
    // Tombol-tombol pada halaman utama
    @FXML
    private Button Tombol_HU;

    @FXML
    private Button Tombol_DM;

    @FXML
    private Button Tombol_P;
    
    @FXML
    private Button Keluar_Akun;
    
    // AnchorPane pada halaman utama
    @FXML
    private AnchorPane Halaman_Utama;

    @FXML
    private AnchorPane Daftar_Menu;

    @FXML
    private AnchorPane Pemesanan;
    
    // Label untuk menampilkan informasi jumlah pemesanan dan pendapatan
    @FXML
    private Label Tampilan_dr_JumlahPemesanan;
    
    @FXML
    private Label Tampilan_dr_PendapatanHariIni;

    @FXML
    private Label Tampilan_dr_TotalPendapatan;
    
    // Input Pada Halaman Daftar Menu Cake
    @FXML
    private TextField Input_IdCake;

    @FXML
    private TextField Input_NamaCake;

    @FXML
    private TextField Input_HargaCake;

    @FXML
    private ComboBox<String> Input_StatusCake;

    @FXML
    private ComboBox<String> Input_JenisCake;
    
    // Tombol-tombol pada halaman daftar menu
    @FXML
    private Button Tombol_Tambah_Produk;

    @FXML
    private Button Tombol_Edit_Produk;

    @FXML
    private Button Tombol_Hapus_Produk;

    @FXML
    private Button Tombol_Bersihkan_Produk;
    
    // Tabel Daftar Menu Cake
    @FXML
    private TableView<AmbilDataProduk> Tabel_Menu_Cake;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Id;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Nama;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Jenis;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Harga;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Status;
    
    // fungsi untuk menuju halaman utama
    @FXML
    public void Tombol_HalamanUtama() {
        Halaman_Utama.setVisible(true);
        Daftar_Menu.setVisible(false);
        Pemesanan.setVisible(false);
        TampilanTabel();
        TampilanTabel2();
        HU_Tampilan_JumlahPemesanan();
        HU_Tampilan_PendapatanHariIni();
        HU_Tampilan_TotalPendapatan();
        HU_Tampilan_Grafik();
    }
    
    //fungsi untuk menuju halaman daftar 
    @FXML
    public void Tombol_DaftarMenu() {
        Halaman_Utama.setVisible(false);
        Daftar_Menu.setVisible(true);
        Pemesanan.setVisible(false);
        TampilanTabel();
        TampilanTabel2();
        HU_Tampilan_JumlahPemesanan();
        HU_Tampilan_PendapatanHariIni();
        HU_Tampilan_TotalPendapatan();
        HU_Tampilan_Grafik();
    }
    
    //fungsi untuk menuju halaman Pemesanan
    @FXML
    public void Tombol_Pemesanan() {
        Halaman_Utama.setVisible(false);
        Daftar_Menu.setVisible(false);
        Pemesanan.setVisible(true);
        TampilanTabel();
        TampilanTabel2();
        HU_Tampilan_JumlahPemesanan();
        HU_Tampilan_PendapatanHariIni();
        HU_Tampilan_TotalPendapatan();
        HU_Tampilan_Grafik();
    }
    
    //fungsi untuk keluar akun
    @FXML
    public void Tombol_Keluar_Akun(){
        try {
            //Langkah pertama : mendapatkan jendela saat ini.
            Stage Login = (Stage) Keluar_Akun.getScene().getWindow();
            
            //Langkah Kedua : menyembunyikan jendela login
            Login.hide();
            
            //Langkah ketiga : membuat jendela baru untuk halaman utama
            FXMLLoader HalamanUtama = new FXMLLoader(getClass().getResource("HalamanLogin.fxml"));
            Parent root = HalamanUtama.load();
            
            //Langkah keempat : membuat stage baru untuk jendela baru
            Stage Tampil = new Stage();
            
            //Langkah Kelima : membuat jendela halaman utama yang ditampilkan(menjadi root)
            Tampil.setResizable(false);//agar jendela tidak bisa diperbesar
            Scene JendelaSatu = new Scene (root);
            Tampil.setScene(JendelaSatu);
            
            //Langkah Keenam : menampilkan jendela utama
            Tampil.show();
        } 
        
        catch (IOException e) {
            e.printStackTrace(); // Tangani pengecualian dengan mencetak jejak ke konsol
        }
    }
    
    // Fungsi untuk menampilkan jumlah pemesanan
    @FXML
    public void HU_Tampilan_JumlahPemesanan(){
        int totalJumlahPemesanan = 0;
        List<AmbilDataPemesanan> semuaDataPemesanan = AmbilDataPemesanan.ambilSemuaDataPemesanan();
        
        for (AmbilDataPemesanan dataPemesanan : semuaDataPemesanan) {
            totalJumlahPemesanan += dataPemesanan.getJumlahPemesanan();
        }
        String SUM_jumlah = String.valueOf(totalJumlahPemesanan);
        Tampilan_dr_JumlahPemesanan.setText(SUM_jumlah);
    }
    
    // Fungsi untuk menampilkan pendapatan hari ini
    @FXML
    public void HU_Tampilan_PendapatanHariIni(){
    List<AmbilDataPemesanan> semuaDataPemesanan = AmbilDataPemesanan.ambilSemuaDataPemesanan();
    
        int jumlahPemesananHariIni = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hariIni = dateFormat.format(Calendar.getInstance().getTime());

        for (AmbilDataPemesanan dataPemesanan : semuaDataPemesanan) {
            if (hariIni.equals(dataPemesanan.getTanggalPemesanan())) {
                jumlahPemesananHariIni += dataPemesanan.getJumlahPemesanan();
            }
        }
        String SUM_pendapatanHariIni = String.valueOf(jumlahPemesananHariIni);
        Tampilan_dr_PendapatanHariIni.setText(SUM_pendapatanHariIni);

    }
    
    // Fungsi untuk menampilkan total pendapatan
    @FXML
    public void HU_Tampilan_TotalPendapatan(){
        List<AmbilDataPemesanan> semuaDataPemesanan = AmbilDataPemesanan.ambilSemuaDataPemesanan();
        
        int totalHargaPemesanan = 0;
        for (AmbilDataPemesanan dataPemesanan : semuaDataPemesanan) {
            totalHargaPemesanan += dataPemesanan.getTotalHgPemesanan();
        }
        String SUM_total = String.valueOf(totalHargaPemesanan);
        Tampilan_dr_TotalPendapatan.setText(SUM_total);

    }
    
    // Fungsi untuk menampilkan grafik
    @FXML
    public void HU_Tampilan_Grafik() {
        Tampilan_Grafik.getData().clear();

        Connection koneksi = KoneksiDatabase.hubungkan();

        String query = "SELECT tanggal_pemesanan, jumlah_pemesanan FROM pemesanan"
                + " GROUP BY tanggal_pemesanan ORDER BY TIMESTAMP(tanggal_pemesanan) ASC LIMIT 9";

        try {
            XYChart.Series<String, Integer> dataGrafik = new XYChart.Series<>();

            PreparedStatement tambahData = koneksi.prepareStatement(query);
            ResultSet result = tambahData.executeQuery(); // Eksekusi query dan dapatkan ResultSet

            while (result.next()) {
                // Ambil data dari setiap baris result set
                String tanggalPembelian = result.getString("tanggal_pemesanan");
                int jumlahPembelian = result.getInt("jumlah_pemesanan");

                // Tambahkan data ke objek Series
                dataGrafik.getData().add(new XYChart.Data<>(tanggalPembelian, jumlahPembelian));
            }

            // Tambahkan objek Series ke grafik
            Tampilan_Grafik.getData().add(dataGrafik);
        } catch (SQLException e) {}
    }
    
    // Fungsi untuk mendapatkan input IdCake
    private int GET_input_IdCake() {
        String inputText = Input_IdCake.getText();
        int idCake = Integer.parseInt(inputText);
        return idCake;
    }   

    // Fungsi untuk mendapatkan input NamaCake
    private String GET_input_NamaCake() {
        return Input_NamaCake.getText();  
    }
    
    // Fungsi untuk mendapatkan input JenisCake
    private String GET_input_JenisCake() {
        return (String) Input_JenisCake.getSelectionModel().getSelectedItem();  
    }
    
    // Fungsi untuk mendapatkan input HargaCake
    private int GET_input_HargaCake() {
        String inputText = Input_HargaCake.getText();
        int hargaCake = Integer.parseInt(inputText);
        return hargaCake;
    }    

    // Fungsi untuk mendapatkan input StatusCake
    private String GET_input_StatusCake() {
        return (String) Input_StatusCake.getSelectionModel().getSelectedItem();  
    }
    
    // Fungsi untuk menampilkan pilihan status produk
    private final String[] statusProduk = {"Tersedia", "Tidak Tersedia"};
    @FXML
    public void MenuPilihanStatusProduk(){
        List<String> listT = new ArrayList<>(); 
       
        listT.addAll(Arrays.asList(statusProduk));
        
        ObservableList<String> listData = FXCollections.observableArrayList(listT);
        Input_StatusCake.setItems(listData);
    }
    
    // Fungsi untuk menampilkan pilihan jenis produk
    private final String[] jenisProduk = {"Butter", "Sponge", "Chiffon"};
    @FXML
    public void MenuPilihanJenisProduk(){
        List<String> listT = new ArrayList<>(); 
       
        listT.addAll(Arrays.asList(jenisProduk));
        
        ObservableList<String> listData = FXCollections.observableArrayList(listT);
        Input_JenisCake.setItems(listData);
    }
    
    // Fungsi untuk menampilkan tabel
    @FXML
    public void TampilanTabel(){
            
        // Tabel menggunakan tipe data AmbilDataProduk    
        List<AmbilDataProduk> DaftarDataProduk = AmbilDataProduk.ambilSemuaDataProduk();
        ObservableList<AmbilDataProduk> observableDataProduk = FXCollections.observableArrayList();

        // Menambahkan data ke dalam observableDataProduk
        for (AmbilDataProduk ListDataProduk : DaftarDataProduk) {
            observableDataProduk.add(ListDataProduk);
        }

        // Mengatur kolom id, nama, jenis, harga dan status
        Kolom_Id.setCellValueFactory(new PropertyValueFactory<>("IdProduk"));
        Kolom_Nama.setCellValueFactory(new PropertyValueFactory<>("NamaProduk"));
	Kolom_Jenis.setCellValueFactory(new PropertyValueFactory<>("JenisProduk"));
	Kolom_Harga.setCellValueFactory(new PropertyValueFactory<>("HargaProduk"));
	Kolom_Status.setCellValueFactory(new PropertyValueFactory<>("StatusProduk"));

        // Set ObservableList sebagai sumber data untuk tabel
        Tabel_Menu_Cake.setItems(observableDataProduk);
    }
    
    // Fungsi untuk menambahkan data
    @FXML
    public void TombolTambahData(){
        Database.DataProduk NewInputData = new Database.DataProduk(GET_input_IdCake(), GET_input_NamaCake(), GET_input_JenisCake(), GET_input_HargaCake(), GET_input_StatusCake());
        if(konfirmasi("Yakin ingin menambah data")){
            pesan("Data berhasil ditambahkan");
            NewInputData.tambahDataProduk(GET_input_IdCake(), GET_input_NamaCake(), GET_input_JenisCake(), GET_input_HargaCake(), GET_input_StatusCake());
            TampilanTabel();
        }
    }
    
    // Fungsi untuk menghapus data
    @FXML
    public void TombolHapusData(){
        Database.DataProduk HapusData = new Database.DataProduk(GET_input_IdCake(), GET_input_NamaCake(), GET_input_JenisCake(), GET_input_HargaCake(), GET_input_StatusCake());
        
        AmbilDataProduk PilihData = Tabel_Menu_Cake.getSelectionModel().getSelectedItem();
        int num = Tabel_Menu_Cake.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < - 1){return;}
        
        if(konfirmasi("Yakin menghapus?")){
            HapusData.deleteDataProduk(PilihData.getIdProduk());
            pesan("Data berhasil dihapus");
            TampilanTabel();
        }
    }
    
    // Fungsi untuk membersihkan input
    @FXML
    public void TombolBersihkanData(){
        Input_IdCake.setText("");
        Input_NamaCake.setText("");
        Input_HargaCake.setText("");
        Input_JenisCake.getSelectionModel().clearSelection();
        Input_StatusCake.getSelectionModel().clearSelection();
    }
    
    // Fungsi untuk memperbarui data
    @FXML
    public void TombolUpdateData(){
        Database.DataProduk DataBaru = new Database.DataProduk();
        
        AmbilDataProduk PilihData = Tabel_Menu_Cake.getSelectionModel().getSelectedItem();
        int num = Tabel_Menu_Cake.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < - 1){return;}
        
        // Konfirmasi pesan
        if(konfirmasi("Konfirmasi Update")){
            DataBaru.perbaruiDataProduk(PilihData.getIdProduk(), GET_input_NamaCake(), GET_input_JenisCake(), GET_input_HargaCake(), GET_input_StatusCake());
            pesan("Data berhasil di update");
            TampilanTabel();
            TombolBersihkanData();
        }  
    }
    
    // Fungsi untuk menampilkan pesan
     public void pesan(String pesanText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pesan Sistem");
        alert.setHeaderText(null);
        alert.setContentText(pesanText);
        
        DialogPane dialogPane = alert.getDialogPane();
        // Mengatur ukuran lebar dan tinggi jendela
        dialogPane.setPrefWidth(250);
        dialogPane.setPrefHeight(50);

        alert.showAndWait();
    }
    
    public static boolean konfirmasi(String pesan) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pesan Sistem");
        alert.setHeaderText(null);
        alert.setContentText(pesan);

        // Menambahkan tombol OK dan Batal ke dalam alert
        ButtonType tombolOK = new ButtonType("OK");
        ButtonType tombolBatal = new ButtonType("Batal", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(tombolOK, tombolBatal);

        // Mengakses DialogPane
        DialogPane dialogPane = alert.getDialogPane();

        // Mengatur ukuran lebar dan tinggi jendela
        dialogPane.setPrefWidth(50);
        dialogPane.setPrefHeight(50);

        // Menampilkan alert dan mendapatkan jawaban dari pengguna
        Optional<ButtonType> hasil = alert.showAndWait();

        // Mengembalikan nilai berdasarkan pilihan pengguna
        return hasil.isPresent() && hasil.get() == tombolOK;
    }
    
    // Fungsi untuk memilih data dari tabel
    @FXML
    public void PilihDataTabel(){
        AmbilDataProduk PilihData = Tabel_Menu_Cake.getSelectionModel().getSelectedItem();
        int num = Tabel_Menu_Cake.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < - 1){return;}
        
        String IdString = Integer.toString(PilihData.getIdProduk());
        String HargaString = Integer.toString(PilihData.getHargaProduk());
        
        Input_IdCake.setText(IdString);
        Input_NamaCake.setText(PilihData.getNamaProduk());
        Input_HargaCake.setText(HargaString);
        Input_JenisCake.setValue(PilihData.getJenisProduk());
        Input_StatusCake.setValue(PilihData.getStatusProduk());
    }
    
    // Id Pada Halaman Ketiga
    @FXML
    private TableView<AmbilDataProduk> Tabel_Menu_Cake2;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Id2;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Nama2;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Jenis2;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Harga2;

    @FXML
    private TableColumn<DataProduk, String> Kolom_Status2;

    @FXML
    private Spinner<Integer> Kuantitas_Pemesanan;

    @FXML
    private Button Tombol_Tambah_Pemesanan;

    @FXML
    private Label Total_Harga_Pemesanan;

    @FXML
    private TextField Input_Jumlah_Uang;

    @FXML
    private Label Kembalian;

    @FXML
    private Button Tombol_Batal_Pesanan;

    @FXML
    private Button Tombol_Bayar_Pesanan;
    
    // Fungsi untuk menampilkan tabel pada halaman ketiga
    @FXML
    public void TampilanTabel2(){
            
        // Tabel menggunakan tipe data AmbilDataProduk    
        List<AmbilDataProduk> DaftarDataProduk = AmbilDataProduk.ambilSemuaDataProduk();
        ObservableList<AmbilDataProduk> observableDataProduk = FXCollections.observableArrayList();

        // Menambahkan data ke dalam observableDataProduk
        for (AmbilDataProduk ListDataProduk : DaftarDataProduk) {
            observableDataProduk.add(ListDataProduk);
        }

        // Mengatur kolom id, nama, jenis, harga dan status
        Kolom_Id2.setCellValueFactory(new PropertyValueFactory<>("IdProduk"));
        Kolom_Nama2.setCellValueFactory(new PropertyValueFactory<>("NamaProduk"));
	Kolom_Jenis2.setCellValueFactory(new PropertyValueFactory<>("JenisProduk"));
	Kolom_Harga2.setCellValueFactory(new PropertyValueFactory<>("HargaProduk"));
	Kolom_Status2.setCellValueFactory(new PropertyValueFactory<>("StatusProduk"));

        // Set ObservableList sebagai sumber data untuk tabel
        Tabel_Menu_Cake2.setItems(observableDataProduk);
    }
    
    // Fungsi untuk menambahkan pemesanan
    @FXML
    public void Tombol_Tambah_Pemesanan(){
        AmbilDataProduk PilihData = Tabel_Menu_Cake2.getSelectionModel().getSelectedItem();
        int num = Tabel_Menu_Cake2.getSelectionModel().getSelectedIndex();

        if((num - 1) < - 1){return;}

        // Menjumlahkan total harga
        int harga = PilihData.getHargaProduk();
        int Hasil = harga * kuantitas_pemesanan;

        //Konversi int to string
        String nilaiString = Integer.toString(Hasil);
        Total_Harga_Pemesanan.setText(nilaiString);
    }
    
    // Fungsi untuk mendapatkan kuantitas pemesanan
    private int kuantitas_pemesanan;
    @FXML
    public void GET_Kuantitas(){
        kuantitas_pemesanan = (int) Kuantitas_Pemesanan.getValue();
    }

    // Fungsi untuk menampilkan kuantitas pemesanan
    private SpinnerValueFactory<Integer> spinner;
    @FXML
    public void TampilkanKuantitasPemesanan(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        Kuantitas_Pemesanan.setValueFactory(spinner);
    }
    
    // Fungsi untuk membayar pemesanan
    @FXML
    public void Tombol_Bayar_Pemesanan(){
        int UangBayar = 0;
        String InputUang = Input_Jumlah_Uang.getText();
        
        // Mengecek apakah InputUang berisi angka atau tidak sebelum mengonversi
        try {
            UangBayar = Integer.parseInt(InputUang);
            // Gunakan nilai UangBayar sesuai kebutuhan
        } catch (NumberFormatException e) {
            System.err.println("Input tidak valid. Pastikan input hanya berisi angka.");
        }
        
        // Ambil total harga
        
        int UangTotalHarga = Integer.parseInt(Total_Harga_Pemesanan.getText());
        
        
        if(UangBayar < UangTotalHarga){
            pesan("Maaf Uang yang anda berikan kurang");
        } else{
            pesan("Pesanan berhasil dibayar");
            BersihkanInputPesanan();
            AmbilDataPemesanan.tambahDataPemesanan(kuantitas_pemesanan, UangTotalHarga);
        }
    }
    
    // Fungsi untuk membersihkan input pemesanan
    @FXML
    public void BersihkanInputPesanan(){
        Input_Jumlah_Uang.setText("");
        Total_Harga_Pemesanan.setText("");
        Kuantitas_Pemesanan.getValueFactory().setValue(0);
        Kembalian.setText("");
    }
    
    // Fungsi untuk menampilkan kembalian
    @FXML
    public void Kembalian(){
        int UangBayar = 0;
        String InputUang = Input_Jumlah_Uang.getText();
        
        // Mengecek apakah InputUang berisi angka atau tidak sebelum mengonversi
        try {
            UangBayar = Integer.parseInt(InputUang);
            // Gunakan nilai UangBayar sesuai kebutuhan
        } catch (NumberFormatException e) {
            System.err.println("Input tidak valid. Pastikan input hanya berisi angka.");
        }
        
        // Ambil total harga
        
        int UangTotalHarga = Integer.parseInt(Total_Harga_Pemesanan.getText());
        
        if (!(UangBayar < UangTotalHarga)) {
            int kembalian = UangBayar - UangTotalHarga;
            Kembalian.setText("Rp" + kembalian);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HU_Tampilan_JumlahPemesanan();
        HU_Tampilan_PendapatanHariIni();
        HU_Tampilan_TotalPendapatan();
        HU_Tampilan_Grafik();
        TampilanTabel();
        TampilanTabel2();
        TampilkanKuantitasPemesanan();
        
        
    }    
}
