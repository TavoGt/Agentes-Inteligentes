package algoritmo;


import java.util.List;

public class Graph {
    private final List<Nodo> nodos;
    private final List<Arista> aristas;
    
    public Graph(List<Nodo> nodos, List<Arista> aristas) {
        this.nodos = nodos;
        this.aristas = aristas;
    }
    
    public List<Nodo> getNodos() {
        return nodos;
    }
    
    public List<Arista> getAristas() {
        return aristas;
    }
}
