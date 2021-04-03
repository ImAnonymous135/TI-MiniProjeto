package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class BaseDados {

    Connection c = null;
    Statement stmt = null;
    String createTableQuery = null;
    int id_aluno = 1;
    String nomeTabela = "a";

    @WebMethod
    public boolean conectarPostsgresql() {
        boolean conetou;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
            conetou = true;
            //System.out.println("Base dados conetada...");
        } catch (Exception ex) {
            conetou = false;
            Logger.getLogger(BaseDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conetou;
    }

    @WebMethod
    public String desconectaPostgresql() {
        String mensagem = "";
        try {
            c.close();
            mensagem = "Base dados desconetada!";
            System.out.println("Database desconetada...");
        } catch (Exception e) {
            mensagem = "Erro dp: " + e.getMessage();
        }
        return mensagem;
    }

    @WebMethod
    public String adicionar(@WebParam(name = "nome") String nome, @WebParam(name = "disciplina") String disciplina, @WebParam(name = "cursos") String cursos) {
        String mensagem = "";
        try {
            //verifica se existe ligação ao postgresql
            if (conectarPostsgresql()) {
                //verificar se a tabela existe

                System.out.println("Nome da tabela:" + nomeTabela);
                boolean exists = c.getMetaData().getTables(null, null, nomeTabela, null).next();
                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
                System.out.println("Base dados conetada, estou a adicionar...");
                createTableQuery = "INSERT INTO \"" + nomeTabela + "\" (id_aluno,nome, disciplinas, cursos) VALUES (?,?,?,?);";
                PreparedStatement stmt = c.prepareStatement(createTableQuery);
                stmt.setInt(1, id_aluno);
                stmt.setString(2, nome);
                stmt.setString(3, disciplina);
                stmt.setString(4, cursos);
                System.out.println("Id do aluno: " + id_aluno);
                id_aluno++;
                stmt.execute(); // Executa o PreparedStatement com o SQL já incluso e os valoes Setados
                //stmt.close();
                System.out.println("Inserido aluno: " + nome);
                mensagem = "Inserido com sucesso o aluno: " + nome;
                stmt.close();
                //c.close();
            } else {
                mensagem = "Base dados desligada, em primeiro lugar ligue-a!";
            }

        } catch (Exception e) {
            mensagem = "Erro a: " + e.getMessage();
        }
        return mensagem;
    }

    @WebMethod
    public String editar(@WebParam(name = "tabela") String tabela, @WebParam(name = "dados") String[] dados) {

        /*if(tabela.equals("blahblah")){
            UPDATE tabela SET (dados,dados,dados) VALUES (?,?,?)
            stmt.setString(2, dados[0]);
        }*/

        String mensagem = "";
        try {
            //verifica se existe ligação ao postgresql
            if (conectarPostsgresql()) {
                //verificar se a tabela existe

                System.out.println("Nome da tabela:" + nomeTabela);
                boolean exists = c.getMetaData().getTables(null, null, nomeTabela, null).next();
                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
                System.out.println("Base dados conetada, estou a adicionar...");
                createTableQuery = "UPDATE \"" + nomeTabela + "SET \" (nome, disciplinas, cursos) VALUES (?,?,?);";
                PreparedStatement stmt = c.prepareStatement(createTableQuery);
                //stmt.setString(2, nome);
                //stmt.setString(3, disciplina);
                //stmt.setString(4, cursos);
                System.out.println("Id do aluno: " + id_aluno);
                stmt.execute(); // Executa o PreparedStatement com o SQL já incluso e os valoes Setados
                //stmt.close();
                //System.out.println("Inserido aluno: " + nome);
                //mensagem = "Inserido com sucesso o aluno: " + nome;
                stmt.close();
                //c.close();
            } else {
                mensagem = "Base dados desligada, em primeiro lugar ligue-a!";
            }

        } catch (Exception e) {
            mensagem = "Erro a: " + e.getMessage();
        }
        return mensagem;
    }

    @WebMethod
    public String ver(@WebParam(name = "nome") String nome) {
        String mensagem = "";
        try {
            if (conectarPostsgresql()) {
                //verificar se a tabela existe
                System.out.println("Nome da tabela:" + nomeTabela);
                boolean exists = c.getMetaData().getTables(null, null, nomeTabela, null).next();

                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
                System.out.println("Base dados conetada, a preparar a informação pedida...");
                stmt = c.createStatement();
                //createTableQuery = "SELECT disciplinas FROM alunos where nome ilike '" + nome + "';";
                //PreparedStatement stmt = c.prepareStatement(createTableQuery);
                //stmt.execute();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + nomeTabela + "\" where nome ilike '" + nome + "';");

                while (rs.next()) {
                    String n = rs.getString("nome");
                    String d = rs.getString("disciplinas");
                    String c = rs.getString("cursos");
                    //System.out.println(n);
                    mensagem = "Nome: " + n + "; Disciplina: " + d + "; Curso: " + c + "\n------------\n";
                }
                stmt.close();
                //c.close();
            } else {
                mensagem = "Base dados desligada, em primeiro lugar ligue-a!";
            }

        } catch (Exception e) {
            mensagem = "Erro v: " + e.getMessage();
        }
        return mensagem;
    }

    @WebMethod
    public String verTodaInformacao() {
        String mensagem = "";
        try {
            if (conectarPostsgresql()) {

                //verificar se a tabela existe
                System.out.println("Nome da tabela:" + nomeTabela);
                boolean exists = c.getMetaData().getTables(null, null, nomeTabela, null).next();

                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
                System.out.println("Database Connected, a prepara a informação do aluno...");
                stmt = c.createStatement();
                //createTableQuery = "SELECT disciplinas FROM alunos where nome ilike '" + nome + "';";
                //PreparedStatement stmt = c.prepareStatement(createTableQuery);
                //stmt.execute();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + nomeTabela + "\" ;");

                while (rs.next()) {
                    String n = rs.getString("nome");
                    String d = rs.getString("disciplinas");
                    String c = rs.getString("cursos");
                    //System.out.println(n);
                    mensagem += "Nome: " + n + "; Disciplina: " + d + "; Curso: " + c + "\n------------\n";
                }

                stmt.close();
                //c.close();
            }

        } catch (Exception e) {
            mensagem = "Erro vti: " + e.getMessage();
        }
        return mensagem;
    }

    @WebMethod
    public void remover(@WebParam(name = "nome") String nome) {
        try {
            if (conectarPostsgresql()) {
                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
                System.out.println("A preparar a remoção...");
                stmt = c.createStatement();
                String sql = "DELETE FROM \"" + nomeTabela + "\" WHERE nome ilike '" + nome + "'";
                int i = stmt.executeUpdate(sql);
                if (i == 0) {
                    System.out.println("Erro ao remover!");
                } else {
                    System.out.println("Removido com sucesos!");
                }
                stmt.close();
                c.close();
            } else {
                System.out.println("Base dados desligada, em primeiro lugar ligue-a!");
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @WebMethod
    public void removeTudo() {
        try {
            if (conectarPostsgresql()) {
                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "rafael");
                System.out.println("A preparar a remoção...");
                stmt = c.createStatement();
                String sql = "DELETE FROM \"" + nomeTabela + "\";";
                int i = stmt.executeUpdate(sql);
                if (i == 0) {
                    System.out.println("Erro ao remover!");
                } else {
                    System.out.println("Tabela limpa!!");
                }
                stmt.close();
                c.close();
            } else {
                System.out.println("Base dados desligada, em primeiro lugar ligue-a!");
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
