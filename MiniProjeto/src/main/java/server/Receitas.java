/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Asus
 */

@WebService
public class Receitas {
    
    BaseDados bd = new BaseDados();
    Statement stmt = null;
    String createTableQuery = null;
    Connection c = null;
    
    @WebMethod
    public String adicionarReceitas(@WebParam(name = "nomeReceitas") String nomeReceitas) {
        String mensagem = "";
        try {
            if ((c = bd.conectarPostsgresql()) != null) {
                System.out.println("Base dados conetada!");
                createTableQuery = "INSERT INTO ingredientes(id,nome) VALUES (?,?);";
                PreparedStatement stmt = c.prepareStatement(createTableQuery);
                stmt.setString(1, nomeReceitas);
                stmt.execute(); // Executa o PreparedStatement com o SQL já incluso e os valoes Setados
                mensagem = "Inserido com sucesso a receita: " + nomeReceitas;
                stmt.close();
            } else {
                mensagem = "Base dados desligada, em primeiro lugar ligue-a!";
            }

        } catch (Exception e) {
            mensagem = "Erro a: " + e.getMessage();
        }
        return mensagem;
    }
    
    @WebMethod
    public String removerReceita(@WebParam(name = "nomeReceita") String nomeReceita) {
        String mensagem = "";
        try {
            if ((c = bd.conectarPostsgresql()) != null) {
                stmt = c.createStatement();
                String sql = "DELETE FROM ingredientes WHERE nome ilike '" + nomeReceita + "'";
                int i = stmt.executeUpdate(sql);
                if (i == 0) {
                    mensagem = "Erro ao remover!";
                } else {
                    mensagem = "Removido com sucesos!";
                }
                stmt.close();
                c.close();
            } else {
                mensagem = "Base dados desligada, em primeiro lugar ligue-a!";
            }
        } catch (Exception e) {
            mensagem = e.getMessage();
        }
        return mensagem;
    }

    @WebMethod
    public String[] verTodasAsReceitas() {
        int size = 0, i = 0;
        String[] ingredientes = null;
        try {
            if ((c = bd.conectarPostsgresql()) != null) {
                System.out.println("Aqui");
                PreparedStatement pstmt = c.prepareStatement("SELECT * FROM receitas;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = pstmt.executeQuery();
                if (rs != null) {
                    rs.last();
                    size = rs.getRow();
                    rs.beforeFirst();
                }
                ingredientes = new String[size];
                while (rs.next()) {
                    ingredientes[i] = rs.getString("nome");
                    i++;
                }
                pstmt.close();
            }
        } catch (Exception e) {
            return new String[]{e.getMessage()};
        }

        return ingredientes;
    }
    
    
}
