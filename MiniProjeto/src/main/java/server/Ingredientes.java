package server;

import client.FormIngredients;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;

public class Ingredientes {

    BaseDados bd = new BaseDados();
    Statement stmt = null;
    String createTableQuery = null;
    Connection c = null;

    @WebMethod
    public String adicionarIngrediente(@WebParam(name = "nomeIngrediente") String nomeIngrediente) {
        String mensagem = "";
        try {
            //verifica se existe ligação ao postgresql
            if (bd.conectarPostsgresql()) {
                System.out.println("Base dados conetada!");
                c.getMetaData().getTables(null, null, "ingredientes", null).next();
                createTableQuery = "INSERT INTO ingredientes(id,nome) VALUES (?,?);";
                PreparedStatement stmt = c.prepareStatement(createTableQuery);
                stmt.setString(1, nomeIngrediente);
                stmt.execute(); // Executa o PreparedStatement com o SQL jÃ¡ incluso e os valoes Setados
                mensagem = "Inserido com sucesso o ingrediente: " + nomeIngrediente;
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
    public String removerIngrediente(@WebParam(name = "nomeIngrediente") String nomeIngrediente) {
        String mensagem = "";
        try {
            if (bd.conectarPostsgresql()) {
                stmt = c.createStatement();
                String sql = "DELETE FROM ingredientes WHERE nome ilike '" + nomeIngrediente + "'";
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
    public void verTodosOsIngredientes() {
        try {
            if (bd.conectarPostsgresql()) {
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM ingredientes;");
                while (rs.next()) {
                    rs.getString("nome");
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
}
