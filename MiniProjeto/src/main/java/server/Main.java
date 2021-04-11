package server;

import javax.xml.ws.Endpoint;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Endpoint ep = Endpoint.create(new Ingredientes());
        ep.publish("http://127.0.0.1:9999/ingredientes");
        Endpoint ep1 = Endpoint.create(new Receitas());
        ep1.publish("http://127.0.0.1:9999/receitas");

    }
}
