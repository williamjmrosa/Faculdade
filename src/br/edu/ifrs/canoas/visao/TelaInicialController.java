/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author William José
 * @version 1.0
 */
public class TelaInicialController implements Initializable {
    
    
    private TextField nomeCurso;
    private TextArea descricaoCurso;
    private Label mensagem;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void telaCadProfessor(ActionEvent event) {
        TelaInicial.trocaTela("CadastroProfessor.fxml");
    }

    @FXML
    private void telaCadCurso(ActionEvent event) {
        TelaInicial.trocaTela("CadastroCurso.fxml");
    }

    @FXML
    private void telaCadDisciplina(ActionEvent event) {
        TelaInicial.trocaTela("CadastroDisciplina.fxml");
    }

    @FXML
    private void telaAddDisciplinaCurso(ActionEvent event) {
        TelaInicial.trocaTela("AdicionarDisciplinaCurso.fxml");
    }

    @FXML
    private void telaCadAluno(ActionEvent event) {
        TelaInicial.trocaTela("CadastroAluno.fxml");
    }

    @FXML
    private void telaCadTurma(ActionEvent event) {
        TelaInicial.trocaTela("CadastroTurma.fxml");
    }

    @FXML
    private void telaCadFuncionario(ActionEvent event) {
        TelaInicial.trocaTela("CadastroFuncionario.fxml");
    }

    
    
}
