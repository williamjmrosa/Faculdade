/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.DisciplinaDAO;
import br.edu.ifrs.canoas.modelo.Disciplina;
import java.net.URL;
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
public class CadastroDisciplinaController implements Initializable {

    @FXML
    private TextField nome;
    @FXML
    private TextArea descricao;
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
    private void cadastrarDisciplina(ActionEvent event) {
        Disciplina d = new Disciplina();
        
        d.setNome(nome.getText());
        d.setDescricao(descricao.getText());
        
        DisciplinaDAO dDAO = new DisciplinaDAO();
        if(dDAO.insert(d) != -1){
            mensagem.setText("Disciplina Cadastrada");
        }else{
            mensagem.setText("Erro da Disciplina");
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
    }
    
}
