package algoritmo;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

    private List<Nodo> nodos;
    private List<Arista> aristas;
    private List<Integer> obstaculosNodos;
    private List<Coordenada> obstaculosCoord;
    private int filas, columnas;
    private int nodoMeta = 0;
    private int nodoOrigen = 0;

    public Test(){}
    
    public Test(int filas, int columnas, Coordenada origen, Coordenada meta, List<Coordenada> obstaculos) {
        nodos = new ArrayList<Nodo>();
        aristas = new ArrayList<Arista>();
        obstaculosNodos = new ArrayList<Integer>();
        this.filas = filas;
        this.columnas = columnas;
        asignarOrigen(origen.getX(), origen.getY());
        asignarMeta(meta.getX(), meta.getY());
        for (Coordenada obstaculo : obstaculos) {
            agregarObstaculo(obstaculo.getX(), obstaculo.getY());
        }
    }
    
    

    public List<Coordenada> ejecutarDijkstra() {
        
        int cont = 0, cont2 = 0;
        
        //Agregando nodos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Nodo ubicacion = new Nodo(i + "," + j, i + "," + j);
                nodos.add(ubicacion);
                cont++;
            }
        }
        
        /*
        agregarObstaculo(3, 4);
        agregarObstaculo(4, 4);
        agregarObstaculo(4, 5);
*/

        //Agregando rutas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                boolean norte = false, sur = false, este = false, oeste = false;
                boolean NO = false, NE = false, SO = false, SE = false;
                boolean obstaculoEncontrado = false;
                
                if(i == 0){
                    if(j == 0){
                        if(verificarObstaculo(cont2)){
                            if (verificarObstaculo(cont2 + filas)) {
                                agregarRuta("Ruta_Sur[" + i + "][" + j + "]", cont2, cont2 + filas, 10);
                            }
                            if (verificarObstaculo(cont2 + 1)) {
                                agregarRuta("Ruta_Este" + i + "][" + j + "]", cont2, cont2 + 1, 10);
                            }
                            if (verificarObstaculo(cont2 + filas + 1)) {
                                agregarRuta("Ruta_SE" + i + "][" + j + "]", cont2, cont2 + (filas) + 1, 14);
                            } 
                        }
                    }else if(j == columnas-1){
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 + filas)) {
                                agregarRuta("Ruta_Sur[" + i + "][" + j + "]", cont2, cont2 + filas, 10);
                            }
                            if (verificarObstaculo(cont2 - 1)) {
                                agregarRuta("Ruta_Oste" + i + "][" + j + "]", cont2, cont2 - 1, 10);
                            }
                            if (verificarObstaculo(cont2 + filas - 1)) {
                                agregarRuta("Ruta_SO" + i + "][" + j + "]", cont2, cont2 + (filas) - 1, 14);
                            }
                        }
                    }else{
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 + filas)) {
                                agregarRuta("Ruta_Sur[" + i + "][" + j + "]", cont2, cont2 + filas, 10);
                            }
                            if (verificarObstaculo(cont2 + 1)) {
                                agregarRuta("Ruta_Este" + i + "][" + j + "]", cont2, cont2 + 1, 10);
                            }
                            if (verificarObstaculo(cont2 - 1)) {
                                agregarRuta("Ruta_Oste" + i + "][" + j + "]", cont2, cont2 - 1, 10);
                            }
                            if (verificarObstaculo(cont2 + filas - 1)) {
                                agregarRuta("Ruta_SO" + i + "][" + j + "]", cont2, cont2 + (filas) - 1, 14);
                            }
                            if (verificarObstaculo(cont2 + filas + 1)) {
                                agregarRuta("Ruta_SE" + i + "][" + j + "]", cont2, cont2 + (filas) + 1, 14);
                            }
                        }
                    }
                }
                
                else if(i == filas-1){
                    if(j == 0){
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_Norte[" + i + "][" + j + "]", cont2, cont2 - filas, 10);
                            }
                            if (verificarObstaculo(cont2 + 1)) {
                                agregarRuta("Ruta_Este" + i + "][" + j + "]", cont2, cont2 + 1, 10);
                            }
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_NE" + i + "][" + j + "]", cont2, cont2 - (filas) + 1, 14);
                            }
                        }
                    }
                    else if(j == columnas-1){
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_Norte[" + i + "][" + j + "]", cont2, cont2 - filas, 10);
                            }
                            if (verificarObstaculo(cont2 - 1)) {
                                agregarRuta("Ruta_Oste" + i + "][" + j + "]", cont2, cont2 - 1, 10);
                            }
                            if (verificarObstaculo(cont2 - filas - 1)) {
                                agregarRuta("Ruta_NO" + i + "][" + j + "]", cont2, cont2 - (filas) - 1, 14);
                            }
                        }
                    } else {
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_Norte[" + i + "][" + j + "]", cont2, cont2 - filas, 10);
                            }
                            if (verificarObstaculo(cont2 + 1)) {
                                agregarRuta("Ruta_Este" + i + "][" + j + "]", cont2, cont2 + 1, 10);
                            }
                            if (verificarObstaculo(cont2 - 1)) {
                                agregarRuta("Ruta_Oste" + i + "][" + j + "]", cont2, cont2 - 1, 10);
                            }
                            if (verificarObstaculo(cont2 - filas - 1)) {
                                agregarRuta("Ruta_NO" + i + "][" + j + "]", cont2, cont2 - (filas) - 1, 14);
                            }
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_NE" + i + "][" + j + "]", cont2, cont2 - (filas) + 1, 14);
                            }
                        }
                    }
                }
                
                else if ((i > 0) && (i < filas - 1)) {
                    if(j == 0){
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_Norte[" + i + "][" + j + "]", cont2, cont2 - filas, 10);
                            }
                            if (verificarObstaculo(cont2 + filas)) {
                                agregarRuta("Ruta_Sur[" + i + "][" + j + "]", cont2, cont2 + filas, 10);
                            }
                            if (verificarObstaculo(cont2 + 1)) {
                                agregarRuta("Ruta_Este" + i + "][" + j + "]", cont2, cont2 + 1, 10);
                            }
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_NE" + i + "][" + j + "]", cont2, cont2 - (filas) + 1, 14);
                            }
                            if (verificarObstaculo(cont2 + filas + 1)) {
                                agregarRuta("Ruta_SE" + i + "][" + j + "]", cont2, cont2 + (filas) + 1, 14);
                            }
                        }
                    }
                    else if (j == columnas-1){
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_Norte[" + i + "][" + j + "]", cont2, cont2 - filas, 10);
                            }
                            if (verificarObstaculo(cont2 + filas)) {
                                agregarRuta("Ruta_Sur[" + i + "][" + j + "]", cont2, cont2 + filas, 10);
                            }
                            if (verificarObstaculo(cont2 - 1)) {
                                agregarRuta("Ruta_Oste" + i + "][" + j + "]", cont2, cont2 - 1, 10);
                            }
                            if (verificarObstaculo(cont2 - filas - 1)) {
                                agregarRuta("Ruta_NO" + i + "][" + j + "]", cont2, cont2 - (filas) - 1, 14);
                            }
                            if (verificarObstaculo(cont2 + filas - 1)) {
                                agregarRuta("Ruta_SO" + i + "][" + j + "]", cont2, cont2 + (filas) - 1, 14);
                            }
                        }
                    }
                    else if ((j > 0) && (j < columnas - 1)) {
                        if (verificarObstaculo(cont2)) {
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_Norte[" + i + "][" + j + "]", cont2, cont2 - filas, 10);
                            }
                            if (verificarObstaculo(cont2 + filas)) {
                                agregarRuta("Ruta_Sur[" + i + "][" + j + "]", cont2, cont2 + filas, 10);
                            }
                            if (verificarObstaculo(cont2 + 1)) {
                                agregarRuta("Ruta_Este" + i + "][" + j + "]", cont2, cont2 + 1, 10);
                            }
                            if (verificarObstaculo(cont2 - 1)) {
                                agregarRuta("Ruta_Oste" + i + "][" + j + "]", cont2, cont2 - 1, 10);
                            }
                            if (verificarObstaculo(cont2 - filas - 1)) {
                                agregarRuta("Ruta_NO" + i + "][" + j + "]", cont2, cont2 - (filas) - 1, 14);
                            }
                            if (verificarObstaculo(cont2 - filas)) {
                                agregarRuta("Ruta_NE" + i + "][" + j + "]", cont2, cont2 - (filas) + 1, 14);
                            }
                            if (verificarObstaculo(cont2 + filas - 1)) {
                                agregarRuta("Ruta_SO" + i + "][" + j + "]", cont2, cont2 + (filas) - 1, 14);
                            }
                            if (verificarObstaculo(cont2 + filas + 1)) {
                                agregarRuta("Ruta_SE" + i + "][" + j + "]", cont2, cont2 + (filas) + 1, 14);
                            }
                        }
                    }
                }
                cont2++;
            }
        }

        List<Coordenada> rutaOptima = new ArrayList<Coordenada>();
        
         //Agregando nodo origen y nodo meta
        Graph graph = new Graph(nodos, aristas);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.ejecutar(nodos.get(nodoOrigen));
        LinkedList<Nodo> ruta = dijkstra.getRuta(nodos.get(nodoMeta));

        for (Nodo nodo : ruta) {
            //System.out.println(nodo);
            String[] coord = nodo.getNombre().split(",");
            int x = Integer.parseInt(coord[0]);
            int y = Integer.parseInt(coord[1]);
            
            rutaOptima.add(new Coordenada(x, y));
        }
        
        return rutaOptima;
        
    }

    private void agregarRuta(String rutaId, int origenUbiNo, int destinoUbiNo, int duracion) {
        Arista ruta = new Arista(rutaId, nodos.get(origenUbiNo), nodos.get(destinoUbiNo), duracion);
        aristas.add(ruta);
    }

    private void agregarObstaculo(int corX, int corY) {
        int auxCont = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if ((i == corX) && (j == corY)) {
                    obstaculosNodos.add(auxCont);
                }
                auxCont++;
            }
        }
    }
    
    private void asignarOrigen(int corX, int corY) {
        int auxCont = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if ((i == corX) && (j == corY)) {
                    nodoOrigen = auxCont;
                    i = filas;
                    j = columnas;
                }
                auxCont++;
            }
        }
    }
    
    public void asignarMeta(int corX, int corY) {
        int auxCont = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if ((i == corX) && (j == corY)) {
                    nodoMeta = auxCont;
                    i = filas;
                    j = columnas;
                    System.out.println("NUEVA META: [" + corX +"]["+ corY+"");
                }
                auxCont++;
            }
        }
    }

    private boolean verificarObstaculo(int posicion) {
        boolean noEsObstaculo = true;
        for (Integer obstaculo : obstaculosNodos) {
            if (obstaculo == posicion) {
                noEsObstaculo = false;
            }
        }

        return noEsObstaculo;
    }
}
