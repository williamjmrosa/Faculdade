/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.DisciplinaDAO;
import br.edu.ifrs.canoas.dao.ProfessorDAO;
import br.edu.ifrs.canoas.dao.TurmaDAO;
import br.edu.ifrs.canoas.modelo.Disciplina;
import br.edu.ifrs.canoas.modelo.Professor;
import br.edu.ifrs.canoas.modelo.Turma;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author William
 */
public class CadastroTurmaController implements Initializable {

    @FXML
    private TextField turma;
    @FXML
    private TextField matriculaPesquisa;
    @FXML
    private ListView<Professor> listProf;
    @FXML
    private TextField nomePesquisa;
    @FXML
    private ListView<Disciplina> listDis;
    @FXML
    private Label descricao;
    @FXML
    private TextField matricula;
    @FXML
    private TextField nome;
    private Alert alert;
    private ButtonType sim = new ButtonType("Sim");
    private ButtonType nao = new ButtonType("Não");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (Disciplina d : DisciplinaDAO.getALL()) {
            listDis.getItems().add(d);
        }

        for (Professor p : ProfessorDAO.getAll()) {
            listProf.getItems().add(p);
        }
    }

    @FXML
    private void pesquisarProfessor(ActionEvent event) {
        listProf.getItems().clear();
        if (matriculaPesquisa.getText().isEmpty()) {
            for (Professor p : ProfessorDAO.getAll()) {
                listProf.getItems().add(p);
            }
        } else {
            ProfessorDAO pDAO = new ProfessorDAO();
            Professor p = pDAO.getOne(Long.parseLong(matriculaPesquisa.getText()));
            listProf.getItems().add(p);
            matricula.setText(String.valueOf(p.getMatricula()));
            nome.setText(p.getNome());
            listProf.getSelectionModel().select(p);

        }
    }

    @FXML
    private void pesquisarDisciplina(ActionEvent event) {
        listDis.getItems().clear();
        if (nomePesquisa.getText().isEmpty()) {
            for (Disciplina d : DisciplinaDAO.getALL()) {
                listDis.getItems().add(d);
            }
        } else {
            DisciplinaDAO dDAO = new DisciplinaDAO();
            for (Disciplina d : dDAO.filtrar(nomePesquisa.getText())) {
                listDis.getItems().add(d);
            }
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
    }

    @FXML
    private void cadastrarTurma(ActionEvent event) {
        TurmaDAO tDAO = new TurmaDAO();
        Turma t = new Turma();
        t.setNome(turma.getText());

        try {
            if (listDis.getSelectionModel().getSelectedIndex() != -1) {
                t.setDisciplina(listDis.getSelectionModel().getSelectedItem());
            } else {
                throw new Exception("Selecione uma Disciplina");
            }

            if (listProf.getSelectionModel().getSelectedIndex() != -1) {
                t.setProfessor(listProf.getSelectionModel().getSelectedItem());
            } else {
                throw new Exception("Selecione um Professor");
            }
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja cadastrar a turma");
            alert.getButtonTypes().setAll(sim, nao);
            alert.showAndWait().ifPresent(b -> {
                if (b == sim) {
                    if (tDAO.insert(t) != -1) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informação");
                        alert.setHeaderText("Cadastro Concluido com sucesso!");
                        alert.show();
                    }
                }
            });
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void selecionarProfessor(MouseEvent event) {
        Professor p = listProf.getSelectionModel().getSelectedItem();
        matricula.setText(String.valueOf(p.getMatricula()));
        nome.setText(p.getNome());

    }

    @FXML
    private void selecionarDisciplina(MouseEvent event) {
        Disciplina d = listDis.getSelectionModel().getSelectedItem();
        d.mostrar(0);
        descricao.setText(d.toString());
    }

}
