/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.dao.ResponsavelDAO;
import br.edu.ifrs.canoas.modelo.Responsavel;
import br.edu.ifrs.canoas.modelo.Telefone;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author William
 */
public class CadastroResponsavelController implements Initializable {

    @FXML
    private TextField nome;
    @FXML
    private TextField rg;
    @FXML
    private TextField cpf;
    @FXML
    private TextField email;
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
    }    

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("CadastroAluno.fxml");
    }

    @FXML
    private void cadastrarResponsavel(ActionEvent event) {
        Responsavel r = new Responsavel();
        r.setNome(nome.getText());
        r.setCpf(cpf.getText());
        r.setEmail(email.getText());
        r.setRg(Long.parseLong(rg.getText()));
        r.setSenha(cpf.getText().substring(cpf.getText().length()-4));
        Telefone t = new Telefone();
        t.setTipo(telTipo.getText());
        t.setNumero(telNumero.getText());
        r.setTelefone(t);
        ResponsavelDAO rDAO = new ResponsavelDAO();
        r.setIdResponsavel(rDAO.insert(r));
        if(r.getIdResponsavel() != -1){
            CadastroAlunoController.addAluno(r);
            TelaInicial.trocaTela("CadastroAluno.fxml");
        }
        
    }
    
}
