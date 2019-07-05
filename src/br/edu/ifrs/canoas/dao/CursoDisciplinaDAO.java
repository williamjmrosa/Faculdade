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

    public Long insert(Curso o) throws SQLException{
        
            Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql = "INSERT INTO FacCursoDisciplina(idDisciplina,idCurso) VALUES(?,?)";

            try{
                delete(o.getIdCurso());
                for(int i = 0;i< o.getL().size();i++){
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setLong(1, o.getL().get(i).getIdDisciplina());
                    ps.setLong(2, o.getIdCurso());

                    ps.executeUpdate();
                }            
                return 0L;
            }catch(SQLException e){
                throw new SQLException("Erro no Curso Disciplina! "+e.getMessage());
                //System.out.println("Erro cadastro CursoDisciplina");
                //e.printStackTrace();
                //return -1L;
            }
    }

    public boolean delete(Long id) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "DELETE FROM FacCursoDisciplina WHERE idCurso = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
           throw new SQLException("Erro ao deletar disciplina do curso! "+e.getMessage());
            //System.out.println("Erro ao Excluir disciplina do curso");
        }finally{
            c.desconecta();
            con.close();
        }
        
    }

    public boolean update(Curso o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Disciplina> buscar(Long id) throws SQLException{
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
            return disciplinas;
        } catch (SQLException e) {
            throw new SQLException("Ero ao buscar! "+e.getMessage());
            //System.out.println("Erro ao Buscar");
            //e.printStackTrace();
            
        }finally{
            c.desconecta();
            con.close();
        }
        
    }
    
    public ArrayList<Curso> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Curso> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
