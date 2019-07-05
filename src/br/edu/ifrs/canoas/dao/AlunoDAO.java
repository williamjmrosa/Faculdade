/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.controle.Logado;
import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Curso;
import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Responsavel;
import br.edu.ifrs.canoas.modelo.Telefone;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class AlunoDAO extends AbstractDAO<Aluno> {

    private static EnderecoDAO eDAO = new EnderecoDAO();
    private static TelefoneDAO tDAO = new TelefoneDAO();
    private static CursoDAO cDAO = new CursoDAO();
    private static ResponsavelDAO rDAO = new ResponsavelDAO();

    @Override
    public Aluno getOne(Long id) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        Aluno a = new Aluno();
        String sql = "SELECT * FROM FacAluno WHERE matricula = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setMatricula(rs.getLong("matricula"));
                a.setNome(rs.getString("nome"));
                a.setRg(rs.getLong("rg"));
                a.setCpf(rs.getString("cpf"));
                //LocalDate ld = LocalDate.parse();
                String texto = rs.getString("dataNascimento");
                String[] partes = texto.substring(0,10).split("-");
                String data = partes[2]+"/"+partes[1]+"/"+partes[0]; 
                a.setDataNascimento(data);
                Endereco e = eDAO.getOne(rs.getLong("idEndereco"));
                a.setEndereco(e);
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                a.setTelefone(t);
                Curso cu = cDAO.getOne(rs.getLong("idCurso"));
                a.setCurso(cu);
                Responsavel r = rDAO.getOne(rs.getLong("idResponsavel"));
                a.setResponsavel(r);
                a.setEmail(rs.getString("email"));
                a.setAcesso(rs.getInt("acesso"));
                a.mostrar(0);
                
            }
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar aluno expecifico! \n"+e.getMessage());
        }
        return a;
    }

    @Override
    public Long insert(Aluno o) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();

        String sql = "INSERT INTO FacAluno(matricula,nome,rg,cpf,dataNascimento,idEndereco,idTelefone,idCurso,idResponsavel,email,senha,acesso)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Endereco e = o.getEndereco();
            e.setIdEndereco(eDAO.insert(e));
            o.setEndereco(e);
            Telefone t = o.getTelefone();
            t.setIdTelefone(tDAO.insert(t));
            o.setTelefone(t);

            if (t.getIdTelefone() != -1 && e.getIdEndereco() != -1) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, o.getMatricula());
                ps.setString(2, o.getNome());
                ps.setLong(3, o.getRg());
                ps.setString(4, o.getCpf());
                LocalDate ld = LocalDate.of(o.getDataNascimento().getAno(), o.getDataNascimento().getMes(), o.getDataNascimento().getDia());
                ps.setDate(5, Date.valueOf(ld.toString()));
                ps.setLong(6, o.getEndereco().getIdEndereco());
                ps.setLong(7, o.getTelefone().getIdTelefone());
                ps.setLong(8, o.getCurso().getIdCurso());
                ps.setLong(9, o.getResponsavel().getIdResponsavel());
                ps.setString(10, o.getEmail());
                ps.setString(11, o.getSenha());
                ps.setInt(12, o.getAcesso());

                ps.executeUpdate();
            }else{
                return -1L;
            }
        } catch (SQLException e) {
            System.out.println("Erro no cadastro de Aluno");
            e.printStackTrace();
            return -1L;
        }

        return 0L;
    }

    @Override
    public boolean delete(Aluno o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Aluno o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aluno> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aluno> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aluno> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static boolean login(Long matricula, String senha) throws SQLException,Exception{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacAluno where matricula = ? and senha = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, matricula);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Aluno a = new Aluno();
                a.setMatricula(rs.getLong("matricula"));
                a.setNome(rs.getString("nome"));
                a.setRg(rs.getLong("rg"));
                a.setCpf(rs.getString("cpf"));
                //LocalDate ld = LocalDate.parse();
                String texto = rs.getString("dataNascimento");
                String[] partes = texto.substring(0,10).split("-");
                
                
                String data = partes[2]+"/"+partes[1]+"/"+partes[0]; 
                a.setDataNascimento(data);
                Endereco e = eDAO.getOne(rs.getLong("idEndereco"));
                a.setEndereco(e);
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                a.setTelefone(t);
                Curso cu = cDAO.getOne(rs.getLong("idCurso"));
                a.setCurso(cu);
                Responsavel r = rDAO.getOne(rs.getLong("idResponsavel"));
                a.setResponsavel(r);
                a.setEmail(rs.getString("email"));
                a.setSenha(rs.getString("senha"));
                a.setAcesso(rs.getInt("acesso"));
                a.mostrar(0);
                Logado.setPessoa(a);
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro no login: \n"+e.getMessage());
            
        }
        return false;
        
    }

}
