/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author William José
 * @version 1.0
 */
public class TelaInicial extends Application {
    
    private static Stage st;
    
    
    public static void trocaTela(String caminho){
        Parent root = null;
        try {
            root = FXMLLoader.load(TelaInicial.class.getResource(caminho));
        } catch (IOException ex) {
            System.out.println("Erro ao carregar XML");
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        st.setScene(scene);
        st.show();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        st = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
