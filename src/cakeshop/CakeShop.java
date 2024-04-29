/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package cakeshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * Created by_22343004 Erpiana
 */
public class CakeShop extends Application {
    
    @Override
    // Metode start dijalankan saat aplikasi JavaFX dimulai
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Memulai aplikasi JavaFX
        launch(args);
    }
    
}
