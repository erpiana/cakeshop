/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package cakeshop;

import Database.AmbilDataAdmin;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by_22343004 Erpiana
 */

public class HalamanLoginKontroller implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button Tombol_Masuk;
    
    @FXML
    private TextField Input_nama;

    @FXML
    private PasswordField Input_sandi;
    
    //metode untuk menangani tombol masuk
    @FXML
    public void MasukHalamanUtama() {
        try {
            //Cek Login
            if(CekLogin()){
                //Langkah pertama : mendapatkan jendela saat ini.
                Stage Login = (Stage) Tombol_Masuk.getScene().getWindow();

                //Langkah Kedua : menyembunyikan jendela login
                Login.hide();

                //Langkah ketiga : membuat jendela baru untuk halaman utama
                FXMLLoader HalamanUtama = new FXMLLoader(getClass().getResource("HalamanUtamaCake.fxml"));
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
        } catch (IOException e) {}           
    }
     
    // Metode untuk melakukan cek login
    @FXML
    public boolean CekLogin(){
        AmbilDataAdmin ambilDataAdmin = new AmbilDataAdmin();

        String InputNama = GET_input_nama();
        String InputSandi = GET_input_sandi();

        boolean loginBerhasil = false;

        for (AmbilDataAdmin.DataAdmin adminData : AmbilDataAdmin.ambilSemuaDataAdmin()) {
            if (adminData.getNama().equals(InputNama) && adminData.getSandi().equals(InputSandi)) {
                pesan("Login Berhasil");
                loginBerhasil = false;
                return true;
            }
        }

        if (!loginBerhasil) {
            pesan("Maaf login gagal, periksa sandi atau nama pengguna");
            return false;
        }
        return true;
    }

    // Metode untuk menampilkan pesan dengan Alert
    public void pesan(String pesanText) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pesan Sistem");
        alert.setHeaderText(null);
        alert.setContentText(pesanText);
        
        DialogPane dialogPane = alert.getDialogPane();
        // Mengatur ukuran lebar dan tinggi jendela
        dialogPane.setPrefWidth(250);
        dialogPane.setPrefHeight(50);

        alert.showAndWait();
    }
    
    // Metode untuk menampilkan konfirmasi dengan Alert
    public static boolean konfirmasi(String pesan) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    
    // Metode untuk mendapatkan input nama
    private String GET_input_nama() {
        return Input_nama.getText();
    }    

    // Metode untuk mendapatkan input sandi
    private String GET_input_sandi() {
        return Input_sandi.getText();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
