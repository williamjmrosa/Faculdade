/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.visao;

import br.edu.ifrs.canoas.controle.Logado;
import br.edu.ifrs.canoas.dao.NotaDAO;
import br.edu.ifrs.canoas.dao.TurmaDAO;
import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Funcionario;
import br.edu.ifrs.canoas.modelo.Nota;
import br.edu.ifrs.canoas.modelo.Professor;
import br.edu.ifrs.canoas.modelo.Responsavel;
import br.edu.ifrs.canoas.modelo.Turma;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author William José
 * @version 1.0
 */
public class TelaInicialController implements Initializable {
    
    
    @FXML
    private Label descricao;
    @FXML
    private TableView<Nota> table;
    @FXML
    private TableColumn<?, ?> colNome;
    @FXML
    private TableColumn<?, ?> colNota1;
    @FXML
    private TableColumn<?, ?> colNota2;
    @FXML
    private TableColumn<?, ?> colNota3;
    @FXML
    private TableColumn<?, ?> colMedia;
    @FXML
    private Pane paneTurma;
    @FXML
    private ListView<Turma> listTurma;
    private ObservableList listNota;
    private TurmaDAO tDAO = new TurmaDAO();
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TextField n1;
    @FXML
    private TextField n2;
    @FXML
    private TextField n3;
    @FXML
    private TextField aluno;
    @FXML
    private Button AtualizarNota;
    private Alert alert;
    private ButtonType sim = new ButtonType("Sim");
    private ButtonType nao = new ButtonType("Não");
    @FXML
    private Pane paneNota;
    @FXML
    private MenuItem telaAddAlunoTurma;
    @FXML
    private MenuItem professor;
    @FXML
    private MenuItem curso;
    @FXML
    private MenuItem disciplina;
    @FXML
    private MenuItem disciplinaCurso;
    @FXML
    private MenuItem turma;
    @FXML
    private MenuItem funcionario;
    @FXML
    private MenuItem alunoMenu;
    Nota n = new Nota();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if(Logado.getPessoa() == null && Logado.getResponsavel() == null){
                TelaInicial.trocaTela("Login.fxml");
            }
            // TODO
            listNota = table.getItems();
            colNome.setCellValueFactory(new PropertyValueFactory("nome"));
            colId.setCellValueFactory(new PropertyValueFactory("idNota"));
            colNota1.setCellValueFactory(new PropertyValueFactory("nota1"));
            colNota2.setCellValueFactory(new PropertyValueFactory("nota2"));
            colNota3.setCellValueFactory(new PropertyValueFactory("nota3"));
            colMedia.setCellValueFactory(new PropertyValueFactory("media"));
            switch (Logado.getTipo()) {
                case 0:
                    Funcionario f = Logado.getFuncionario();
                    descricao.setText(f.toString());
                    professor.setVisible(true);
                    curso.setVisible(true);
                    disciplina.setVisible(true);
                    disciplinaCurso.setVisible(true);
                    alunoMenu.setVisible(true);
                    turma.setVisible(true);
                    funcionario.setVisible(true);
                    
                    break;
                case 1:
                    Professor p = Logado.getProfessor();
                    paneTurma.setVisible(true);
                    paneNota.setVisible(true);
                    for(Turma t : tDAO.buscar(p.getMatricula())){
                        t.mostrar(0);
                        listTurma.getItems().add(t);
                    }
                    
                    descricao.setText(p.toString());
                    break;
                case 2:
                    Aluno a = Logado.getAluno();
                    descricao.setText(a.toString());
                    telaAddAlunoTurma.setVisible(true);
                    break;
                case 3:
                    Responsavel r = Logado.getResponsavel();
                    descricao.setText(r.toString());
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
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

    @FXML
    private void telaAddAlunoTurma(ActionEvent event) {
        TelaInicial.trocaTela("AdicionarAlunoTurma.fxml");
    }

    @FXML
    private void telaLogin(ActionEvent event) {
        Logado.setPessoa(null);
        Logado.setResponsavel(null);
        TelaInicial.trocaTela("Login.fxml");
    }

    @FXML
    private void selecionar(MouseEvent event) {
        NotaDAO nDAO = new NotaDAO();
        listNota.clear();
        try {
            for(Nota no :nDAO.buscar(listTurma.getSelectionModel().getSelectedItem().getIdTurma())){
                listNota.add(no);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void selecionaNota(MouseEvent event) {
        n = table.getSelectionModel().getSelectedItem();
        aluno.setText(n.getAluno().getNome());
        n1.setText(String.valueOf(n.getNota1()));
        n2.setText(String.valueOf(n.getNota2()));
        n3.setText(String.valueOf(n.getNota3()));
        
    }

    @FXML
    private void atualizarNota(ActionEvent event) {
        try {
            NotaDAO nDAO = new NotaDAO();
            //Nota n = table.getSelectionModel().getSelectedItem();
            n.setNota1(Double.parseDouble(n1.getText()));
            n.setNota2(Double.parseDouble(n2.getText()));
            n.setNota3(Double.parseDouble(n3.getText()));
            
            if(!nDAO.update(n)){
                throw new Exception("Não foi possivel atualizar");
            }
            
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nota atualizada com sucesso!");
            alert.show();
            
            table.refresh();
            
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    
    
}
