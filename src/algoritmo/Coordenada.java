package algoritmo;

/***
 * Clase para establecer las coordenadas de un nodo
 */
public class Coordenada {

    private final int x;
    private final int y;
    
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return Coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * @return Coordenada y
     */
    public int getY() {
        return y;
    } 
}
