package vaucangraph.model;



import java.util.ArrayList;

public class Vertice  {

    private String id;
    private String nome;
    private Integer tamanhoX;
    private Integer tamanhoY;

    private ArrayList<Aresta> Adjacencia = new ArrayList<>();

    //Caminhos Minimos
    private double distanciaMinima = Double.POSITIVE_INFINITY;
    private Vertice anterior;

    public int posvisita = -1;
    public int previsita = -1;

    public int compareTo(Vertice other) {
        return Double.compare(distanciaMinima, other.getDistanciaMinima());

    }

    public Vertice(String id, String nome, int tamanhoX, int tamanhoY) {
        this.id = id;
        this.nome = nome;
        this.tamanhoX = tamanhoX;
        this.tamanhoY = tamanhoY;
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

    public Integer getTamanhoX() {
        return tamanhoX;
    }

    public void setTamanhoX(Integer tamanhoX) {
        this.tamanhoX = tamanhoX;
    }

    public Integer getTamanhoY() {
        return tamanhoY;
    }

    public void setTamanhoY(Integer tamanhoY) {
        this.tamanhoY = tamanhoY;
    }

    public ArrayList<Aresta> getAdjacencia() {
        return Adjacencia;
    }

    public void setAdjacencia(ArrayList<Aresta> Adjacencia) {
        this.Adjacencia = Adjacencia;
    }

    public double getDistanciaMinima() {
        return distanciaMinima;
    }

    public void setDistanciaMinima(double distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    public Vertice getAnterior() {
        return anterior;
    }

    public void setAnterior(Vertice anterior) {
        this.anterior = anterior;
    }

    public int getPosvisita() {
        return posvisita;
    }

    public void setPosvisita(int posvisita) {
        this.posvisita = posvisita;
    }

    public int getPrevisita() {
        return previsita;
    }

    public void setPrevisita(int previsita) {
        this.previsita = previsita;
    }


    
}