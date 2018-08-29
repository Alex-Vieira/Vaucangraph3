package vaucangraph.model;


import com.sun.javafx.geom.Edge;
import javafx.scene.canvas.GraphicsContext;

import java.awt.font.GraphicAttribute;

public class Aresta  {

    private Object pai;
    private String id;
    private String nome;
    private Vertice origem;
    private Vertice destino;
    private String estilo;
    private Integer peso;


    public Aresta(Object pai, String id, String nome, Vertice origem, Vertice destino) {
        this.pai = pai;
        this.id = id;
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
    }

    public Object getPai() {
        return pai;
    }

    public void setPai(Object pai) {
        this.pai = pai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}
