package br.ufms.vaucangraph.core;

public class EdgeProperty {

    private VertexProperty source;
    private VertexProperty target;
    private TransitionType transitionType;

    public EdgeProperty(VertexProperty source, VertexProperty target, TransitionType transitionType) {
        this.source = source;
        this.target = target;
        this.transitionType = transitionType;
    }

    public VertexProperty getSource() {
        return source;
    }

    public void setSource(VertexProperty source) {
        this.source = source;
    }

    public VertexProperty getTarget() {
        return target;
    }

    public void setTarget(VertexProperty target) {
        this.target = target;
    }

    public TransitionType getTransitionType() {
        return transitionType;
    }

    public void setTransitionType(TransitionType transitionType) {
        this.transitionType = transitionType;
    }
}
