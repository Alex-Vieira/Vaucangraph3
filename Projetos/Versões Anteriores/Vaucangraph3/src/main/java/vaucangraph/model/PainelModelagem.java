package vaucangraph.model;





public class PainelModelagem{

    final int PORT_DIAMETER = 30;
    final int PORT_RADIUS = PORT_DIAMETER/2 ;
    
    public void adicionaVertice(double x,double y ){
        
    }
    
    public void tamanhoPainel(double width, double height){
        
    }
/*
Vertice vertice, vertice2;
        Object obj1, obj2;
        Object parent = graph.getDefaultParent();
        Aresta aresta;

        vertice = new Vertice(null, "Hello", 40, 80, 100, 100);
        obj1=insereVertice(graph, parent, vertice);
       // graphComponent.setGraph(graph);

    }

    public Object insereVertice(mxGraph graph, Object parent, Vertice vertice) {
        graph.getModel().beginUpdate();

        mxCell vertex = (mxCell) graph.insertVertex(parent, vertice.getId(), vertice.getNome(), vertice.getPosX(), vertice.getPosY(), 100, 100, "shape=ellipse");
        //configuraVertice(graph, parent, vertice, vertex);
        graph.getModel().endUpdate();

        return vertex;
    }
    /*
    public void alteraPosicao(mxGraph graph, Object parent, Vertice vertice){
        graph.getModel().beginUpdate();

        mxCell vertex = (mxCell) graph.insertVertex(parent, vertice.getId(), vertice.getNome(), vertice.getPosX(), vertice.getPosY(), vertice.getWidth(), vertice.getHeight(), "shape=ellipse");
        //configuraVertice(graph, parent, vertice, vertex);
        graph.getModel().endUpdate();
    }
    */
/*
    public void configuraVertice(mxGraph graph, Object parent, Vertice vertice, mxCell vertex){
        vertex.setConnectable(false);
        graph.setCellsEditable(false);
        graph.setCellsResizable(false);

        mxGeometry geo1 = new mxGeometry(0, 0, vertice.getWidth(), vertice.getHeight());
        geo1.setRelative(true);
        mxCell port1 = new mxCell(null, geo1,"shape=ellipse;perimeter=ellipsePerimeter");

        port1.setVisible(false);
        graph.isPort(port1);
        graph.addCell(port1, vertex);
    }
*/
/*
    public void removeVertice(mxGraph graph, Vertice vertice, Object verticeObj) {
        graph.getModel().beginUpdate();
        graph.removeCells(new Object[]{verticeObj});
        removeAdjacencia(vertice);
        graph.getModel().endUpdate();
    }

    public void removeAdjacencia(Vertice vertice) {


    }

    public void insereAresta(mxGraph graph, Object parent,  Aresta aresta, Object verticeOrigem, Object verticeDestino) {
        aresta.getOrigem().getAdjacencia().add(aresta);
        aresta.getDestino().getAdjacencia().add(aresta);

        graph.getModel().beginUpdate();
        mxGeometry geo2 = new mxGeometry(0, 0, PORT_DIAMETER, PORT_DIAMETER);
        geo2.setRelative(true);

        mxCell port2 = new mxCell(null, geo2,
                "shape=ellipse;perimter=ellipsePerimeter");
        port2.setEdge(true);
        graph.isPort(port2);
        port2.setVisible(false);

        Object edge = graph.insertEdge(parent, aresta.getId(), aresta.getNome(), verticeOrigem, verticeDestino);
        graph.insertEdge(parent, null, " ", port2, edge);

        graph.getModel().endUpdate();
    }


    public void removeAresta (mxGraph graph, Aresta aresta, Object arestaObj) {
        graph.getModel().beginUpdate();
        aresta.getOrigem().getAdjacencia().remove(aresta);
        aresta.getDestino().getAdjacencia().remove(aresta);
        graph.removeCells(new Object[]{arestaObj});
        graph.getModel().endUpdate();
    }

*/
}