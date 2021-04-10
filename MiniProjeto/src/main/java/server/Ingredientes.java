package server;

import client.FormIngredients;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Ingredientes {

    BaseDados bd = new BaseDados();
    Statement stmt = null;
    String createTableQuery = null;
    Connection c = null;

    @WebMethod
    public String adicionarIngrediente(@WebParam(name = "nome") String nome) {
        String mensagem = "";
        try {
            if ((c = bd.conectarPostsgresql()) != null) {
                System.out.println("Base dados conetada, estou a adicionar...");
                createTableQuery = "INSERT INTO ingredientes(nome) VALUES(?)";
                PreparedStatement stmt = c.prepareStatement(createTableQuery);
                System.out.println("2");
                stmt.setString(1, nome);
                stmt.execute();
                System.out.println("Inserido aluno: " + nome);
                mensagem = "Inserido com sucesso o aluno: " + nome;
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
            if ((c = bd.conectarPostsgresql()) != null) {
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
            if ((c = bd.conectarPostsgresql()) != null) {
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
