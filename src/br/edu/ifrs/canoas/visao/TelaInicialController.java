/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPEvents;
import br.com.parg.viacep.ViaCEPException;
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
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author William José
 * @version 1.0
 */
public class TelaInicialController implements Initializable , ViaCEPEvents {
    
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
    @FXML
    private TextField fomacao;
   
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
        p.setFormacao(fomacao.getText());
        p.setCpf(cpf.getText());
        p.setEmail(email.getText());
        p.setSenha(senha.getText());
        p.setAcesso(1);
        Endereco e = new Endereco();
        e.setIdEndereco(p.getMatricula());
        e.setCep(cep.getText());
        e.setRua(rua.getText());
        e.setNumero(Integer.parseInt(numero.getText()));
        e.setBairro(bairro.getText());
        e.setCidade(cidade.getText());
        e.setEstado(estado.getText());
        Telefone t = new Telefone();
        t.setIdTelefone(p.getMatricula());
        t.setTipo(telTipo.getText());
        t.setNumero(telNumero.getText());
        p.setEndereco(e);
        p.setTelefone(t);
        if(p.insert() == true){
            mensagem.setText("Professor Cadastrado");
        }
    
    }

    @Override
    public void onCEPSuccess(ViaCEP cepp) {
        cep.setText(cepp.getCep());
        rua.setText(cepp.getLogradouro());
        bairro.setText(cepp.getBairro());
        cidade.setText(cepp.getLocalidade());
        estado.setText(cepp.getUf());
    }

    @Override
    public void onCEPError(String cep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void endereco(KeyEvent event) {
        if(event.getCode() == event.getCode().ENTER || event.getCode() == event.getCode().TAB){
            ViaCEP viaCEP = new ViaCEP(this);
            try {
                viaCEP.buscar(cep.getText());
            } catch (ViaCEPException vce) {
                System.out.println("Erro na classe: "+vce);
            }
        }
    }
    
}
