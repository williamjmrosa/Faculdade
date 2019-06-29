/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.CursoDAO;
import br.edu.ifrs.canoas.dao.CursoDisciplinaDAO;
import br.edu.ifrs.canoas.dao.DisciplinaDAO;
import br.edu.ifrs.canoas.modelo.Curso;
import br.edu.ifrs.canoas.modelo.Disciplina;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private Curso c = null;
    private Alert alert;
    private ButtonType sim = new ButtonType("Sim");
    private ButtonType nao = new ButtonType("Não");

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
            //c.mostrar(1);
            listCurso.getItems().add(c);

        }

        for (Disciplina d : DisciplinaDAO.getALL()) {
            //d.mostrar(1);
            listDisciplina.getItems().add(d);
        }

    }

    @FXML
    private void ConfirmarDisciplina(ActionEvent event) {
        CursoDisciplinaDAO cdDAO = new CursoDisciplinaDAO();
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Alterações");
        alert.setHeaderText("Confirmação");
        alert.setContentText("Tem certeza que deseja confirmar as modifições!");
        alert.getButtonTypes().setAll(sim, nao);
        alert.showAndWait().ifPresent(b -> {
            if (b == sim) {
                if (cdDAO.insert(c) == 0) {
                    alert  = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText("Disciplinas adicionadas ao curso");
                    alert.showAndWait();
                    //mensagem.setText("Disciplinas adicionadas ao curso");
                } else {
                    mensagem.setText("Erro ao adicionar disciplina");
                }
            }else if(b == nao){
                listDisciplinaCurso.getItems().clear();
                for(Disciplina d : cdDAO.buscar(c.getIdCurso())){
                    listDisciplinaCurso.getItems().add(d);
                }
            }
        });

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
                listCurso.getItems().add(c);
            }
        } else if (ComboCurso.getSelectionModel().getSelectedItem().equalsIgnoreCase("ID")) {
            for (Curso c : cDAO.filtrar(Long.parseLong(filtroCurso.getText()))) {
                listCurso.getItems().add(c);
            }
        } else {
            for (Curso c : cDAO.filtrar(filtroCurso.getText())) {
                listCurso.getItems().add(c);
            }
        }

    }

    @FXML
    private void filtrarDisciplina(ActionEvent event) {
        DisciplinaDAO dDAO = new DisciplinaDAO();
        listDisciplina.getItems().clear();
        if (filtroDisciplina.getText().isEmpty()) {
            for (Disciplina d : DisciplinaDAO.getALL()) {
                listDisciplina.getItems().add(d);
            }
        } else if (comboDisciplina.getSelectionModel().getSelectedItem().equalsIgnoreCase("ID")) {
            for (Disciplina d : dDAO.filtrar(Long.parseLong(filtroDisciplina.getText()))) {
                listDisciplina.getItems().add(d);
            }
        } else {
            for (Disciplina d : dDAO.filtrar(filtroDisciplina.getText())) {
                listDisciplina.getItems().add(d);
            }
        }

    }

    @FXML
    private void remover(ActionEvent event) {
        Disciplina d = listDisciplinaCurso.getSelectionModel().getSelectedItem();
        c.removeDisciplina(d);
        listDisciplinaCurso.getItems().remove(d);
    }

    @FXML
    private void add(ActionEvent event) {
        if (c == null) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione Primeiro um curso");
            alert.showAndWait();
        } else {
            Disciplina d = listDisciplina.getSelectionModel().getSelectedItem();
            if (c.getL().indexOf(d) == -1) {
                listDisciplinaCurso.getItems().add(d);
                c.addDisciplina(d);
            }
        }
    }

    @FXML
    private void selecionaCurso(MouseEvent event) {
        c = listCurso.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(c.getIdCurso()));
        nome.setText(c.getNome());
        descricao.setText(c.getDescricao());
        CursoDisciplinaDAO cdDAO = new CursoDisciplinaDAO();
        listDisciplinaCurso.getItems().clear();
        for (Disciplina d : cdDAO.buscar(c.getIdCurso())) {
            listDisciplinaCurso.getItems().add(d);
            c.addDisciplina(d);
        }
    }

}
