/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPEvents;
import br.com.parg.viacep.ViaCEPException;
import br.edu.ifrs.canoas.dao.AlunoDAO;
import br.edu.ifrs.canoas.dao.CursoDAO;
import br.edu.ifrs.canoas.dao.ResponsavelDAO;
import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Curso;
import br.edu.ifrs.canoas.modelo.Data;
import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Responsavel;
import br.edu.ifrs.canoas.modelo.Telefone;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author William
 */
public class CadastroAlunoController implements Initializable, ViaCEPEvents {

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
    private ComboBox<Curso> comboCurso;
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
    @FXML
    private ToggleGroup groupR;
    @FXML
    private Pane pane;
    @FXML
    private TextField responsavelCpf;
    @FXML
    private TextArea mostrarResponsavel;
    @FXML
    private Button responsavel;
    @FXML
    private RadioButton cadRep;
    @FXML
    private RadioButton busRep;
    private static Aluno a = null;
    @FXML
    private TextField dataNasc;
    private Alert alert;
    private ButtonType sim = new ButtonType("Sim");
    private ButtonType nao = new ButtonType("Não");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listar();
        radioButto();
        recuperar();

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

    private void listar() {
        comboCurso.getItems().add(0, new Curso());
        comboCurso.getSelectionModel().select(0);
        for (Curso c : CursoDAO.getALL()) {
            comboCurso.getItems().add(c);
        }
    }

    @FXML
    private void radioButto() {
        if (groupR.getSelectedToggle() == cadRep) {
            pane.setVisible(false);
            responsavel.setVisible(true);
        } else {
            pane.setVisible(true);
            responsavel.setVisible(false);
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        ResponsavelDAO rDAO = new ResponsavelDAO();
        a = new Aluno();
        Responsavel r = rDAO.getOne(responsavelCpf.getText());
        a.setResponsavel(r);
        mostrarResponsavel.setText(a.getResponsavel().toString());
    }

    @FXML
    private void cadastrarResponsavel(ActionEvent event) {
        a = new Aluno();
        salvar();
        TelaInicial.trocaTela("CadastroResponsavel.fxml");
    }

    @FXML
    private void voltar(ActionEvent event) {
        TelaInicial.trocaTela("TelaInicial.fxml");
    }

    @FXML
    private void cadastrarAluno(ActionEvent event) {
        if (a != null) {
            a.setMatricula(Long.parseLong(matricula.getText()));
            a.setNome(nome.getText());
            a.setRg(Long.parseLong(rg.getText()));
            a.setCpf(cpf.getText());
            a.setEmail(email.getText());
            a.setDataNascimento(dataNasc.getText());
            a.setAcesso(2);
            a.setSenha(cpf.getText().substring(cpf.getText().length() - 4));
            Endereco e = new Endereco();
            e.setCep(cep.getText());
            e.setRua(rua.getText());
            e.setNumero(Integer.parseInt(numero.getText()));
            e.setBairro(bairro.getText());
            e.setCidade(cidade.getText());
            e.setEstado(estado.getText());
            a.setEndereco(e);
            Telefone t = new Telefone();
            t.setNumero(numero.getText());
            t.setTipo(telTipo.getText());
            a.setTelefone(t);
            if (comboCurso.getSelectionModel().getSelectedIndex() != 0) {
                a.setCurso(comboCurso.getSelectionModel().getSelectedItem());

                AlunoDAO aDAO = new AlunoDAO();
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação de Cadastro Aluno");
                alert.setHeaderText("Tem certeza que deseja fazer o cadastro");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(b -> {
                    if (b == sim) {
                        if (aDAO.insert(a) != -1) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Informação");
                            alert.setHeaderText("Cadastro concluido");
                            alert.showAndWait();
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erro");
                            alert.setHeaderText("Erro no cadastro");
                            alert.showAndWait();
                        }
                    }
                });
            }else{
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText("Selecione um Curso");
                alert.showAndWait();
            }
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Adicione um Responsavel");
            alert.showAndWait();
        }

    }

    public static void addAluno(Responsavel r) {
        a.setResponsavel(r);
    }

    public void salvar() {

        a.setNome(nome.getText());
        a.setRg(Long.parseLong(rg.getText()));
        a.setCpf(cpf.getText());
        a.setDataNascimento(dataNasc.getText());
        a.setCurso(comboCurso.getSelectionModel().getSelectedItem());
        a.setEmail(email.getText());
        a.setMatricula(Long.parseLong(matricula.getText()));
        Endereco e = new Endereco();
        e.setCep(cep.getText());
        e.setRua(rua.getText());
        e.setNumero(Integer.parseInt(numero.getText()));
        e.setBairro(bairro.getText());
        e.setCidade(cidade.getText());
        e.setEstado(estado.getText());
        a.setEndereco(e);
        Telefone t = new Telefone();
        t.setNumero(telNumero.getText());
        t.setTipo(telTipo.getText());
        a.setTelefone(t);
    }

    public void recuperar() {
        if (a != null) {
            nome.setText(a.getNome());
            rg.setText(String.valueOf(a.getRg()));
            cpf.setText(a.getCpf());
            email.setText(a.getEmail());
            cep.setText(a.getEndereco().getCep());
            rua.setText(a.getEndereco().getRua());
            numero.setText(String.valueOf(a.getEndereco().getNumero()));
            bairro.setText(a.getEndereco().getBairro());
            cidade.setText(a.getEndereco().getCidade());
            estado.setText(a.getEndereco().getEstado());
            telTipo.setText(a.getTelefone().getTipo());
            telNumero.setText(a.getTelefone().getNumero());
            dataNasc.setText(a.getDataNascimento().getDia()+"/"+a.getDataNascimento().getMes()+"/"+a.getDataNascimento().getAno());
            mostrarResponsavel.setText(a.getResponsavel().toString());
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
}
