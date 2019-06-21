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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author William
 */
public class AdicionarDisciplinaCursoController implements Initializable {

    @FXML
    private ListView<Curso> listCurso;
    @FXML
    private TextField filtroCurso;
    @FXML
    private TextField filtroDisciplina;
    @FXML
    private ComboBox<String> ComboCurso;
    @FXML
    private ComboBox<?> comboDisciplina;
    @FXML
    private TextField nome;
    @FXML
    private TextArea descricao;
    @FXML
    private Label mensagem;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboCurso.getItems().add("ID");
        ComboCurso.getItems().add("Nome");
        ComboCurso.getSelectionModel().select(0);
        for(Curso c : CursoDAO.getALL()){
            c.mostrar(1);
            listCurso.getItems().add(c);
            
        }
        
        
    }    

    @FXML
    private void AddicionarDisciplina(ActionEvent event) {
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
    }

    @FXML
    private void filtrarCurso(KeyEvent event) {
        CursoDAO cDAO = new CursoDAO();
        listCurso.getItems().clear();
        for(Curso c : cDAO.filtrar(filtroCurso.getText())){
            c.mostrar(1);
            listCurso.getItems().add(c);
        }
    }

    @FXML
    private void filtrarDisciplina(KeyEvent event) {
    }
    
}
