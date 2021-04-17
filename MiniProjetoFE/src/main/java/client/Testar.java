/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import receita.Receita;

public class Testar {

    MessageFactory mf;
    SOAPMessage sm;
    SOAPHeader sh;
    SOAPBody sb;
    QName bodyName;
    SOAPBodyElement be;
    URL ep;
    SOAPConnectionFactory scf;
    SOAPConnection sc;
    SOAPBody respBody;
    SOAPMessage response;
    Iterator it;
    SOAPBodyElement el;
    SOAPBodyElement sbe;

    // Ingredientes
    public String adicionarIngrediente(String nome) {
        String mensagem = "";
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            sm = mf.createMessage();
            //Obtenção da instância do Header
            sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            bodyName = new QName("http://server/", "adicionarIngrediente", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nome");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(nome);
            //Identificação do Endpoint
            ep = new URL(String.format("http://localhost:9999/ingredients"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            it = respBody.getChildElements();
            //Acede ao elemento seguinte
            el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            mensagem = sbe.getTextContent();
        } catch (Exception e) {
            e.getMessage();
        }
        return mensagem;
    }

    public String editarIngrediente(String nome, String novoNome) {
        String mensagem = "";
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            sm = mf.createMessage();
            //Obtenção da instância do Header
            sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            bodyName = new QName("http://server/", "editarIngrediente", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nome");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(nome);

            //Criação de um objeto do tipo QName
            QName name1 = new QName("novoNome");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg1 = be.addChildElement(name1);
            //Adiciona um valor ao subelemento criado
            arg1.addTextNode(novoNome);

            //Identificação do Endpoint
            ep = new URL(String.format("http://localhost:9999/ingredients"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            it = respBody.getChildElements();
            //Acede ao elemento seguinte
            el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            mensagem = sbe.getTextContent();
        } catch (Exception e) {
            e.getMessage();
        }
        return mensagem;
    }

    public ArrayList<String> retornaIngrediente() {
        ArrayList<String> ingredientes = new ArrayList<String>();
        try {
            // Tem que se criar uma nova mensagem sempre que usamos um metodo diferente
            mf = MessageFactory.newInstance();
            sm = mf.createMessage();
            //Obtenção da instância do Header
            sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            sb = sm.getSOAPBody();
            // Namespace URI, localpart, namespace prefix
            bodyName = new QName("http://server/", "verTodosOsIngredientes", "m");
            // Associação do objeto QName ao elemento Body
            be = sb.addBodyElement(bodyName);
            // Identificação do Endpoint
            ep = new URL(String.format("http://127.0.0.1:9999/ingredients"));
            // Criar um objeto SOAPConnec tionFactory para instanciar uma ligação
            scf = SOAPConnectionFactory.newInstance();
            // Cria o objeto para estabeleceraligação
            sc = scf.createConnection();
            // Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            response = sc.call(sm, ep);
            // Termina a ligação
            sc.close();
            // Obtém a referência para o elemento Body da resposta
            respBody = response.getSOAPBody();
            // Obtenção do iterador para percorrer os subelementos do 
            // elemento Body
            it = respBody.getChildElements();
            // Acede ao elemento seguinte
            el = (SOAPBodyElement) it.next();
            // Obtém os subelementos do elemento el
            it = el.getChildElements();
            // Acede ao elemento seguinte

            while ((sbe = (SOAPBodyElement) it.next()) != null) {
                ingredientes.add(sbe.getTextContent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ingredientes;
    }

    public String removerIngrediente(String nome) {
        String mensagem = "";
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            MessageFactory mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            SOAPMessage sm = mf.createMessage();
            //Obtenção da instância do Header
            SOAPHeader sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            SOAPBody sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            QName bodyName = new QName("http://server/", "removerIngrediente", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            SOAPBodyElement be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nome");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(nome);
            //Identificação do Endpoint
            URL ep = new URL(String.format("http://localhost:9999/ingredients"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            SOAPConnection sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            SOAPMessage response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            SOAPBody respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            Iterator it = respBody.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            mensagem = sbe.getTextContent();
        } catch (Exception e) {
            e.getMessage();
        }
        return mensagem;
    }

    // RECEITAS
    public void editarReceita(Receita receita, Receita receitaAntiga) {
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            MessageFactory mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            SOAPMessage sm = mf.createMessage();
            //Obtenção da instância do Header
            SOAPHeader sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            SOAPBody sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            QName bodyName = new QName("http://server/", "atualizarInstrucoes", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            SOAPBodyElement be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nomeReceita");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(receita.getNome());

            //Criação de um objeto do tipo QName
            QName name1 = new QName("instrucoes");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg1 = be.addChildElement(name1);
            //Adiciona um valor ao subelemento criado
            arg1.addTextNode(receita.getInstrucoes());

            //Identificação do Endpoint
            URL ep = new URL(String.format("http://localhost:9999/receitas"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            SOAPConnection sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            SOAPMessage response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            SOAPBody respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            Iterator it = respBody.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            System.out.println("Valor: " + sbe.getTextContent());
            for (String ingrediente : receitaAntiga.getIngredientes()) {
                eliminarIngredienteR(receita.getNome(), ingrediente);
            }
           
            for (String ingrediente : receita.getIngredientes()) {
                adicionarIngredienteR(receita.getNome(), ingrediente);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void adicionarReceita(Receita receita) {
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            MessageFactory mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            SOAPMessage sm = mf.createMessage();
            //Obtenção da instância do Header
            SOAPHeader sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            SOAPBody sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            QName bodyName = new QName("http://server/", "adicionarReceitas", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            SOAPBodyElement be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nomeReceitas");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(receita.getNome());

            //Criação de um objeto do tipo QName
            QName name1 = new QName("instrucoes");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg1 = be.addChildElement(name1);
            //Adiciona um valor ao subelemento criado
            arg1.addTextNode(receita.getInstrucoes());

            //Identificação do Endpoint
            URL ep = new URL(String.format("http://localhost:9999/receitas"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            SOAPConnection sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            SOAPMessage response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            SOAPBody respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            Iterator it = respBody.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            System.out.println("Valor: " + sbe.getTextContent());
            for (String ingrediente : receita.getIngredientes()) {
                adicionarIngredienteR(receita.getNome(), ingrediente);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    private void adicionarIngredienteR(String receita, String ingrediente) {
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            MessageFactory mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            SOAPMessage sm = mf.createMessage();
            //Obtenção da instância do Header
            SOAPHeader sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            SOAPBody sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            QName bodyName = new QName("http://server/", "adicionarIngredienteR", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            SOAPBodyElement be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("receita");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(receita);
            QName name1 = new QName("ingrediente");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg1 = be.addChildElement(name1);
            //Adiciona um valor ao subelemento criado
            arg1.addTextNode(ingrediente);

            //Identificação do Endpoint
            URL ep = new URL(String.format("http://localhost:9999/receitas"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            SOAPConnection sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            SOAPMessage response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            SOAPBody respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            Iterator it = respBody.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            System.out.println("Valor: " + sbe.getTextContent());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void eliminarIngredienteR(String receita, String ingrediente) {
        try {
            //Instanciação do objeto que permiteacriação de mensagens
            MessageFactory mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            SOAPMessage sm = mf.createMessage();
            //Obtenção da instância do Header
            SOAPHeader sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            SOAPBody sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            QName bodyName = new QName("http://server/", "removerIngredienteR", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            SOAPBodyElement be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nomeReceita");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(receita);
            QName name1 = new QName("ingrediente");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg1 = be.addChildElement(name1);
            //Adiciona um valor ao subelemento criado
            arg1.addTextNode(ingrediente);

            //Identificação do Endpoint
            URL ep = new URL(String.format("http://localhost:9999/receitas"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            SOAPConnection sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            SOAPMessage response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            SOAPBody respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            Iterator it = respBody.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            System.out.println("Valor: " + sbe.getTextContent());
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void eliminarReceita(Receita receita) {
        try {
            System.out.println(receita.getNome());
            //Instanciação do objeto que permiteacriação de mensagens
            MessageFactory mf = MessageFactory.newInstance();

            //Cria a mensagem SOAP com Header e Body
            SOAPMessage sm = mf.createMessage();
            //Obtenção da instância do Header
            SOAPHeader sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            SOAPBody sb = sm.getSOAPBody();
            //Remove o header da mensagem
            sh.detachNode();
            //Namespace URI , localpart , namespace prefix
            QName bodyName = new QName("http://server/", "removerReceita", "nome");//o m é o que envia do genero <m></m>
            //Associação do obje to QName ao elemento Body
            SOAPBodyElement be = sb.addBodyElement(bodyName);
            //Criação de um objeto do tipo QName
            QName name = new QName("nomeReceita");
            //Adiciona um novo subelemento ao elemento Body
            SOAPElement arg0 = be.addChildElement(name);
            //Adiciona um valor ao subelemento criado
            arg0.addTextNode(receita.getNome());
            //Identificação do Endpoint
            URL ep = new URL(String.format("http://localhost:9999/receitas"));
            //Criar um objeto SOAPConnectionFactory para instanciar uma ligação
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            //Criao objeto para estabelecer a ligação
            SOAPConnection sc = scf.createConnection();
            //Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            SOAPMessage response = sc.call(sm, ep);
            //Termina a ligação
            sc.close();
            //Obtém a referência para o elemento Body da resposta
            SOAPBody respBody = response.getSOAPBody();
            //Obtenção do iterador para percorrer os subelementos do elemento Body
            Iterator it = respBody.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement el = (SOAPBodyElement) it.next();
            //Obtém os subelementos do elemento el
            it = el.getChildElements();
            //Acede ao elemento seguinte
            SOAPBodyElement sbe = (SOAPBodyElement) it.next();
            //Escreve o valor
            System.out.println("Valor: " + sbe.getTextContent());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private ArrayList<Receita> retornaReceita() {
        ArrayList<Receita> receitas = new ArrayList<Receita>();
        try {
            // Tem que se criar uma nova mensagem sempre que usamos um metodo diferente
            mf = MessageFactory.newInstance();
            sm = mf.createMessage();
            //Obtenção da instância do Header
            sh = sm.getSOAPHeader();
            //Obtenção da instância do Body
            sb = sm.getSOAPBody();
            // Namespace URI, localpart, namespace prefix
            bodyName = new QName("http://server/", "verTodasAsReceitas", "m");
            // Associação do objeto QName ao elemento Body
            be = sb.addBodyElement(bodyName);
            // Identificação do Endpoint
            ep = new URL(String.format("http://127.0.0.1:9999/receitas"));
            // Criar um objeto SOAPConnec tionFactory para instanciar uma ligação
            scf = SOAPConnectionFactory.newInstance();
            // Cria o objeto para estabeleceraligação
            sc = scf.createConnection();
            // Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
            response = sc.call(sm, ep);
            // Termina a ligação
            sc.close();
            // Obtém a referência para o elemento Body da resposta
            respBody = response.getSOAPBody();
            // Obtenção do iterador para percorrer os subelementos do 
            // elemento Body
            it = respBody.getChildElements();
            // Acede ao elemento seguinte
            el = (SOAPBodyElement) it.next();
            // Obtém os subelementos do elemento el
            it = el.getChildElements();
            // Acede ao elemento seguinte
            it = el.getChildElements();

            while ((sbe = (SOAPBodyElement) it.next()) != null) {

                String nome = sbe.getTextContent();
                String[] newNome = nome.split("-");
                receitas.add(new Receita(newNome[0], newNome[1]));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return receitas;
    }

    public ArrayList<Receita> retornaIngredienteR() {
        ArrayList<Receita> receitas = retornaReceita();
        for (Receita receita : receitas) {
            try {
                // Tem que se criar uma nova mensagem sempre que usamos um metodo diferente
                mf = MessageFactory.newInstance();
                sm = mf.createMessage();
                //Obtenção da instância do Header
                sh = sm.getSOAPHeader();
                //Obtenção da instância do Body
                sb = sm.getSOAPBody();
                // Namespace URI, localpart, namespace prefix
                bodyName = new QName("http://server/", "listarIngredientes", "m");
                // Associação do objeto QName ao elemento Body
                be = sb.addBodyElement(bodyName);
                //Criação de um objeto do tipo QName
                QName name1 = new QName("receita");
                //Adiciona um novo subelemento ao elemento Body
                SOAPElement arg1 = be.addChildElement(name1);
                //Adiciona um valor ao subelemento criado
                arg1.addTextNode(receita.getNome());
                // Identificação do Endpoint
                ep = new URL(String.format("http://127.0.0.1:9999/receitas"));
                // Criar um objeto SOAPConnec tionFactory para instanciar uma ligação
                scf = SOAPConnectionFactory.newInstance();
                // Cria o objeto para estabeleceraligação
                sc = scf.createConnection();
                // Realiza a invocação do serviço enviado a mensagem SOAP (sm) para o endpoint ( ep )
                response = sc.call(sm, ep);
                // Termina a ligação
                sc.close();
                // Obtém a referência para o elemento Body da resposta
                respBody = response.getSOAPBody();
                // Obtenção do iterador para percorrer os subelementos do 
                // elemento Body
                it = respBody.getChildElements();
                // Acede ao elemento seguinte
                el = (SOAPBodyElement) it.next();
                // Obtém os subelementos do elemento el
                it = el.getChildElements();
                // Acede ao elemento seguinte

                while ((sbe = (SOAPBodyElement) it.next()) != null) {
                    receita.addIngrediente(sbe.getTextContent());
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return receitas;
    }

}
