package vaucangraph.model;

import vaucangraph.enumeration.TipoDeProjeto;

import java.awt.*;

public class Projeto {

    private String nome;
    private TipoDeProjeto tipoDeProjeto;
    private Dimension size;
    private String localArquivo;
    private boolean salvo;

    public Projeto(String nome, TipoDeProjeto tipoDeProjeto, Dimension size) {
        this.nome = nome;
        this.tipoDeProjeto = tipoDeProjeto;
        this.size = size;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDeProjeto getTipoDeProjeto() {
        return tipoDeProjeto;
    }

    public void setTipoDeProjeto(TipoDeProjeto tipoDeProjeto) {
        this.tipoDeProjeto = tipoDeProjeto;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public String getLocalArquivo() {
        return localArquivo;
    }

    public void setLocalArquivo(String localArquivo) {
        this.localArquivo = localArquivo;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }
}
