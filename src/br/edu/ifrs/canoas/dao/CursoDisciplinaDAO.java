/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Curso;
import br.edu.ifrs.canoas.modelo.Disciplina;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William José
 */
public class CursoDisciplinaDAO {

    public Long insert(Curso o) {
        if(delete(o.getIdCurso())){
            Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql = "INSERT INTO FacCursoDisciplina(idDisciplina,idCurso) VALUES(?,?)";

            try{
                for(int i = 0;i< o.getL().size();i++){
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setLong(1, o.getL().get(i).getIdDisciplina());
                    ps.setLong(2, o.getIdCurso());

                    ps.executeUpdate();
                }            

            }catch(SQLException e){
                System.out.println("Erro cadastro CursoDisciplina");
                e.printStackTrace();
                return -1L;
            }
            return 0L;
        }else{
            return -1L;
        }
    }

    public boolean delete(Long id) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "DELETE FROM FacCursoDisciplina WHERE idCurso = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir disciplina do curso");
        }
        return true;
    }

    public boolean update(Curso o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Disciplina> buscar(Long id) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT d.idDisciplina id, d.NOME, d.DESCRICAO FROM FacCursoDisciplina cd inner join FacDisciplina d on cd.idDisciplina = d.idDisciplina WHERE cd.IDCURSO = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getLong("id"));
                d.setNome(rs.getString("nome"));
                d.setDescricao(rs.getString("descricao"));
                d.mostrar(1);
                disciplinas.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar");
            e.printStackTrace();
            return null;
        }
        return disciplinas;
    }
    
    public ArrayList<Curso> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Curso> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
