/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.CursoDAO;
import br.edu.ifrs.canoas.modelo.Curso;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author William
 */
public class CadastroCursoController implements Initializable {

    @FXML
    private TextField nomeCurso;
    @FXML
    private TextArea descricaoCurso;
    @FXML
    private Label mensagem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarCurso(ActionEvent event) throws SQLException {
        CursoDAO cDAO = new CursoDAO();
        
        Curso c = new Curso();
        c.setNome(nomeCurso.getText());
        c.setDescricao(descricaoCurso.getText());
        
        if(cDAO.insert(c) != -1){
            mensagem.setText(" Curso Cadastrado");
        }else{
            mensagem.setText(" Erro no Cadastro do Curso");
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");    }
    
}
