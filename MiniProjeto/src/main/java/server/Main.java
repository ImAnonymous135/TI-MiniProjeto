package server;

import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        /*Endpoint ep = Endpoint.create(new Ingredientes());
        ep.publish("http://127.0.0.1:9999/ingredients");
        Endpoint ep1 = Endpoint.create(new Receitas());
        ep1.publish("http://127.0.0.1:9999/receitas");*/

        Receitas r = new Receitas();

        ArrayList<Receita> receitas = r.verTodasAsReceitas();

        for (Receita t : receitas){
            System.out.println(t.getNome());
            System.out.println(t.getInstrucoes());
            for(String e : t.getIngredientes()){
                System.out.println(e);
            }
            System.out.println("-------------------------------------------------");
        }

    }
}
