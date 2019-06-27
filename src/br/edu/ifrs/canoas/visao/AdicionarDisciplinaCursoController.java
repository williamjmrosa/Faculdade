/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.CursoDAO;
import br.edu.ifrs.canoas.dao.DisciplinaDAO;
import br.edu.ifrs.canoas.modelo.Curso;
import br.edu.ifrs.canoas.modelo.Disciplina;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ComboBox<String> comboDisciplina;
    @FXML
    private TextField nome;
    @FXML
    private TextArea descricao;
    @FXML
    private Label mensagem;
    @FXML
    private TextField id;
    @FXML
    private ListView<Disciplina> listDisciplina;
    @FXML
    private ListView<Disciplina> listDisciplinaCurso;
    @FXML
    private Button pesquisaCurso;
    @FXML
    private Button pesquisarDisciplina;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboCurso.getItems().add("ID");
        ComboCurso.getItems().add("Nome");
        comboDisciplina.getItems().add("ID");
        comboDisciplina.getItems().add("Nome");
        
        ComboCurso.getSelectionModel().select(0);
        comboDisciplina.getSelectionModel().select(0);
        for (Curso c : CursoDAO.getALL()) {
            c.mostrar(1);
            listCurso.getItems().add(c);

        }
        
        for (Disciplina d : DisciplinaDAO.getALL()){
            d.mostrar(1);
            listDisciplina.getItems().add(d);
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
    private void filtrarCurso(ActionEvent event) {

        CursoDAO cDAO = new CursoDAO();
        listCurso.getItems().clear();
        if (filtroCurso.getText().isEmpty()) {
            for (Curso c : CursoDAO.getALL()) {
                c.mostrar(1);
                listCurso.getItems().add(c);
            }
        } else if(ComboCurso.getSelectionModel().getSelectedIndex() == 0){
            for (Curso c : cDAO.filtrar(Long.parseLong(filtroCurso.getText()))) {
                c.mostrar(1);
                listCurso.getItems().add(c);
            }
        } else{
            for (Curso c : cDAO.filtrar(filtroCurso.getText())) {
                c.mostrar(1);
                listCurso.getItems().add(c);
            }
        }

    }

    @FXML
    private void filtrarDisciplina(ActionEvent event) {
        DisciplinaDAO dDAO = new DisciplinaDAO();
        listDisciplina.getItems().clear();
        if(filtroDisciplina.getText().isEmpty()){
           for(Disciplina d: DisciplinaDAO.getALL()){
               d.mostrar(1);
               listDisciplina.getItems().add(d);
           }
        }else if(comboDisciplina.getSelectionModel().getSelectedIndex() == 0){
            for (Disciplina d : dDAO.filtrar(Long.parseLong(filtroDisciplina.getText()))){
                d.mostrar(1);
                listDisciplina.getItems().add(d);
            }
        }else{
            for (Disciplina d : dDAO.filtrar(filtroDisciplina.getText())){
                d.mostrar(1);
                listDisciplina.getItems().add(d);
            }
        }
        
    }
    
    @FXML
    private void remover(ActionEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
    }

}
