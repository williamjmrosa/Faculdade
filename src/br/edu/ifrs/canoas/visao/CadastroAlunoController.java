/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.CursoDAO;
import br.edu.ifrs.canoas.modelo.Curso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author William
 */
public class CadastroAlunoController implements Initializable {

    @FXML
    private TextField matricula;
    @FXML
    private TextField nome;
    @FXML
    private TextField rg;
    @FXML
    private TextField cpf;
    @FXML
    private TextField email;
    @FXML
    private TextField senha;
    @FXML
    private TextField reSenha;
    @FXML
    private ComboBox<Curso> comboCurso;
    @FXML
    private TextField cep;
    @FXML
    private TextField rua;
    @FXML
    private TextField numero;
    @FXML
    private TextField bairro;
    @FXML
    private TextField cidade;
    @FXML
    private TextField estado;
    @FXML
    private TextField telTipo;
    @FXML
    private TextField telNumero;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listar();
        
    }    

    @FXML
    private void endereco(KeyEvent event) {
    }
    
    private void listar(){
        for(Curso c : CursoDAO.getALL()){
            comboCurso.getItems().add(c);
        }
    }
    
}
