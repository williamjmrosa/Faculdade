/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.AlunoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author William
 */
public class LoginController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label usuario;
    @FXML
    private TextField matricula;
    @FXML
    private TextField senha;
    @FXML
    private ButtonBar listaBotao;
    @FXML
    private Button professor;
    @FXML
    private Button aluno;
    @FXML
    private Button responsavel;
    @FXML
    private Button funcionario;
    private int tipo = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logar(ActionEvent event) {
        try {
            if(tipo == 1){
                
            }else if(tipo == 2){
                
            }else if(tipo == 3){
                if(!AlunoDAO.login(Long.parseLong(matricula.getText()), senha.getText())){
                    throw new Exception("Matricula e senha esta incorreta");
                }
            }else if(tipo == 4){
                
            }else if(tipo == 5){
                
            }else{
                
            }
                
            
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(String.valueOf(ex.getMessage()));
            alert.show();
        }
    }

    @FXML
    private void cadastrarFuncionario(ActionEvent event) {
        tipo = 1;
        usuario.setText("Matricula");
    }

    @FXML
    private void professor(ActionEvent event) {
        tipo = 2;
        usuario.setText("Matricula");
    }

    @FXML
    private void aluno(ActionEvent event) {
        tipo = 3;
        usuario.setText("Matricula");
    }

    @FXML
    private void responsavel(ActionEvent event) {
        tipo = 4;
        usuario.setText("cpf");
    }

    @FXML
    private void funcionario(ActionEvent event) {
        tipo = 5;
        usuario.setText("Matricula");
    }
    
}
