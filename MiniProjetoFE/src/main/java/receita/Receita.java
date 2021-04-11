/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receita;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Receita {

    private ArrayList<String> ingredientes;
    private final String nome;
    private final String instrucoes;

    public Receita(String nome, String instrucoes) {
        this.nome = nome;
        this.instrucoes = instrucoes;
        this.ingredientes = new ArrayList<String>();
    }

    public void addIngrediente(String ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public String getNome() {
        return this.nome;
    }

    public String getInstrucoes() {
        return this.instrucoes;
    }

    public ArrayList<String> getIngredientes() {
        return this.ingredientes;
    }
}
