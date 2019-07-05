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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private Alert alert;
    private ButtonType sim = new ButtonType("Sim");
    private ButtonType nao = new ButtonType("Não");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cadastrarCurso(ActionEvent event) {
        try {
            CursoDAO cDAO = new CursoDAO();

            Curso c = new Curso();
            c.setNome(nomeCurso.getText());
            c.setDescricao(descricaoCurso.getText());
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja cadastrar Curso");
            alert.getButtonTypes().setAll(sim, nao);
            alert.showAndWait().ifPresent(b -> {
                if (b == sim) {
                    try {
                        cDAO.insert(c);

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informação");
                        alert.setHeaderText("Cadastro Concluido co Sucesso!");
                        alert.show();
                    } catch (SQLException ex) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERRO");
                        alert.setHeaderText(ex.getMessage());
                        alert.show();
                    }
                }

            });

        } catch (Exception ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
    }

}
