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

    @WebMethod
    public boolean conectarPostsgresql() {
        boolean conetou;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/miniProjeto_TI", "postgres", "rafael");
            conetou = true;
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
}
