package vaucangraph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {

    public static int cont;

    public static double RetornaPesoAresta(Vertice inicio, Vertice fim){
        for (Aresta aresta:inicio.getAdjacencia()){
             if(aresta.getDestino().getNome().equals(fim.getNome())){
                return aresta.getPeso();
                 }
             }
         return -1;
         }

    public  void BuscaProfundidade(Vertice vertice){
        //Controla a Pre Visita
        vertice.previsita = cont++;//insere a visita
        if (vertice.getAdjacencia() != null){
            for (Aresta aresta : vertice.getAdjacencia()){
                if (aresta.getDestino().previsita==-1){
                    //aresta.getDestino().previous = t;    verificar
                    BuscaProfundidade(aresta.getDestino());
                    }
                }
            }
        //Controla a P´os Visita
         vertice.posvisita = cont++;//insere a finaliza¸c~ao do vertice
        cont=0;
    }

    static PriorityQueue<Vertice> aVisitar = new PriorityQueue<Vertice>();

      //Busca em Largura
        public static void BuscaLargura(Vertice vertice){
         if (vertice.previsita == -1){
             vertice.previsita = cont++;
             }
         if (vertice.getAdjacencia() != null){
             for (Aresta aresta : vertice.getAdjacencia()){
                 if (aresta.getDestino().previsita==-1){
                     if (!aVisitar.contains(vertice)){
                         aresta.getDestino().previsita = vertice.previsita+1;
                         aVisitar.add(aresta.getDestino());
                         }
                    }
                 }
            }
         while(!aVisitar.isEmpty()){
             BuscaLargura(aVisitar.poll());
             }
        }

    public static void Dijkstra(Vertice origem) {
         origem.setDistanciaMinima(0.0);
         PriorityQueue<Vertice> filaDeVertices = new PriorityQueue<Vertice>();
        filaDeVertices.add(origem);

         while (!filaDeVertices.isEmpty()) {
             Vertice u = filaDeVertices.poll();

             if (u.getAdjacencia() != null){
                // Visita cada aresta de u
                 for (Aresta aresta : u.getAdjacencia())
                     {
                     Vertice v = aresta.getDestino();
                     double peso = aresta.getPeso();
                     double distanceThroughU = u.getDistanciaMinima() + peso;
                     if (distanceThroughU < v.getDistanciaMinima()) {
                         filaDeVertices.remove(v);
                         v.setDistanciaMinima(distanceThroughU);
                         v.setAnterior(u);
                         filaDeVertices.add(v);
                         }
                     }
                 }
             }
         }

         //imprimir o menor caminho do Dijkstra
         public static List<Vertice> menorCaminho(Vertice destino) {
         List<Vertice> path = new ArrayList<Vertice>();
        for (Vertice vertice = destino; vertice != null; vertice = vertice.getAnterior())
             path.add(vertice);
         Collections.reverse(path);
         return path;
         }

}
