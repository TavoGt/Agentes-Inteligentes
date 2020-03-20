package algoritmo;

public class Arista {
    private final String id;
    private final Nodo origen;
    private final Nodo destino;
    private final int valor;
    
    public Arista(String id, Nodo origen, Nodo destino, int valor) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.valor = valor;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the origen
     */
    public Nodo getOrigen() {
        return origen;
    }

    /**
     * @return the destino
     */
    public Nodo getDestino() {
        return destino;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }
    
    @Override
    public String toString() {
        return origen + " " + destino;
    }
}
