package server;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        Endpoint ep = Endpoint.create(new BaseDados());
        ep.publish("http://127.0.0.1:9999/baseDados");
    }
}
