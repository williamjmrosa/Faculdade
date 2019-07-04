/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPEvents;
import br.com.parg.viacep.ViaCEPException;
import br.edu.ifrs.canoas.dao.FuncionarioDAO;
import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Funcionario;
import br.edu.ifrs.canoas.modelo.Telefone;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author William
 */
public class CadastroFuncionarioController implements Initializable, ViaCEPEvents {

    @FXML
    private Label mensagem;
    @FXML
    private TextField matricula;
    @FXML
    private TextField nome;
    @FXML
    private TextField rg;
    @FXML
    private TextField cpf;
    @FXML
    private TextField email;
    @FXML
    private TextField senha;
    @FXML
    private TextField reSenha;
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
    private TextField telTipo;
    @FXML
    private TextField telNumero;
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
    private void cadastrarFuncionario(ActionEvent event) throws Exception {
        try {
            FuncionarioDAO fDAO = new FuncionarioDAO();
            Funcionario f = new Funcionario();
            f.setMatricula(Long.parseLong(matricula.getText()));
            f.setNome(nome.getText());
            f.setRg(Long.parseLong(rg.getText()));
            f.setCpf(cpf.getText());
            f.setEmail(email.getText());
            f.setSenha(senha.getText());
            Endereco e = new Endereco();
            e.setCep(cep.getText());
            e.setRua(rua.getText());
            e.setNumero(Integer.parseInt(numero.getText()));
            e.setBairro(bairro.getText());
            e.setCidade(cidade.getText());
            e.setEstado(estado.getText());
            f.setEndereco(e);
            Telefone t = new Telefone();
            t.setNumero(telNumero.getText());
            t.setTipo(telTipo.getText());
            f.setTelefone(t);
            f.setAcesso(0);

            fDAO.insert(f);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Cadastro concluido com sucesso!");
            alert.show();

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText(String.valueOf(e.getMessage()));
            alert.show();
        }
    }

    @FXML
    private void endereco(KeyEvent event) {
        if (event.getCode() == event.getCode().ENTER || event.getCode() == event.getCode().TAB) {
            ViaCEP viaCEP = new ViaCEP(this);
            try {
                viaCEP.buscar(cep.getText());
            } catch (ViaCEPException vce) {
                System.out.println("Erro na classe: " + vce);
            }
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
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

}
