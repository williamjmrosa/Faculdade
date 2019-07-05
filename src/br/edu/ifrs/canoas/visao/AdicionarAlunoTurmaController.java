/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.controle.Logado;
import br.edu.ifrs.canoas.dao.NotaDAO;
import br.edu.ifrs.canoas.dao.TurmaAlunoDAO;
import br.edu.ifrs.canoas.modelo.Nota;
import br.edu.ifrs.canoas.modelo.Turma;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author William
 */
public class AdicionarAlunoTurmaController implements Initializable {

    @FXML
    private ListView<Turma> listaTurma;
    @FXML
    private TextField matricula;
    @FXML
    private TextField nome;
    @FXML
    private TextField turma;
    @FXML
    private TextField disciplina;
    private TurmaAlunoDAO tdDAO = new TurmaAlunoDAO();
    private Alert alert;
    private ButtonType sim = new ButtonType("Sim");
    private ButtonType nao = new ButtonType("Não");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            for (Turma t : tdDAO.buscar(Logado.getAluno().getCurso().getIdCurso())) {
                t.mostrar(1);
                listaTurma.getItems().add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        matricula.setText(String.valueOf(Logado.getAluno().getMatricula()));
        nome.setText(Logado.getAluno().getNome());

    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
    }

    @FXML
    private void matricular(ActionEvent event) {

        try {
            if (listaTurma.getSelectionModel().getSelectedIndex() == -1) {
                throw new Exception("Selecione uma Turma");
            }
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja Matricular o aluno na turma");
            alert.getButtonTypes().setAll(sim, nao);
            alert.showAndWait().ifPresent(b -> {
                if (b == sim) {
                    try {
                        tdDAO.insert(listaTurma.getSelectionModel().getSelectedItem(), Logado.getAluno());
                        NotaDAO nDAO = new NotaDAO();
                        Nota n = new Nota();
                        n.setNota1(0);
                        n.setNota2(0);
                        n.setNota3(0);
                        n.setAluno(Logado.getAluno());
                        n.setTurma(listaTurma.getSelectionModel().getSelectedItem());
                        nDAO.insert(n);
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informação");
                        alert.setHeaderText("Matricula na turma concluida");
                        alert.show();

                    } catch (SQLException ex) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText(ex.getMessage());
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
    private void selecionarTurma(MouseEvent event) {
        Turma t = listaTurma.getSelectionModel().getSelectedItem();
        turma.setText(t.getNome());
        disciplina.setText(t.getDisciplina().getNome());
    }

}
