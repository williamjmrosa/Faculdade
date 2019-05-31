/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Professor;
import br.edu.ifrs.canoas.modelo.Telefone;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author William José
 * @version 1.0
 */
public class TelaInicialController implements Initializable {
    
    @FXML
    private TextField matricula;
    @FXML
    private TextField nome;
    @FXML
    private TextField rg;
    @FXML
    private TextField cpf;
    @FXML
    private TextField cep;
    @FXML
    private TextField rua;
    @FXML
    private TextField numero;
    @FXML
    private TextField bairro;
    @FXML
    private TextField cidade;
    @FXML
    private TextField estado;
    @FXML
    private Label mensagem;
    @FXML
    private Button cadProfessor;
    @FXML
    private TextField email;
    @FXML
    private TextField senha;
    @FXML
    private TextField reSenha;
    @FXML
    private TextField telTipo;
    @FXML
    private TextField telNumero;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarProfessor(ActionEvent event) {
    
        Professor p = new Professor();
        p.setMatricula(Long.parseLong(matricula.getText()));
        p.setNome(nome.getText());
        p.setRg(Long.parseLong(rg.getText()));
        p.setCpf(cpf.getText());
        p.setEmail(email.getText());
        p.setSenha(senha.getText());
        p.setAcesso(0);
        Endereco e = new Endereco();
        e.setCep(cep.getText());
        e.setRua(rua.getText());
        e.setNumero(Integer.parseInt(numero.getText()));
        e.setBairro(bairro.getText());
        e.setCidade(cidade.getText());
        e.setEstado(estado.getText());
        Telefone t = new Telefone();
        t.setTipo(telTipo.getText());
        t.setNumero(telNumero.getText());
        p.setEndereco(e);
        p.setTelefone(t);
        
        mensagem.setText(p.toString());
        
    
    }
    
}
