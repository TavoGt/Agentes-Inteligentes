package algoritmo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    private final List<Nodo> nodos;
    private final List<Arista> aristas;
    private Set<Nodo> nodosColocados;
    private Set<Nodo> nodosQuitados;
    private Map<Nodo, Nodo> predecesores;
    private Map<Nodo, Integer> distancia;
    
    
    public Dijkstra(Graph graph) {
        this.nodos = new ArrayList<Nodo>(graph.getNodos());
        this.aristas = new ArrayList<Arista>(graph.getAristas());
    }
    
    public void ejecutar(Nodo origen) {
        nodosColocados = new HashSet<Nodo>();
        nodosQuitados = new HashSet<Nodo>();
        distancia = new HashMap<Nodo, Integer>();    
        predecesores = new HashMap<Nodo, Nodo>();
        distancia.put(origen, 0);
        nodosQuitados.add(origen);
        
        while(nodosQuitados.size() > 0) {
            Nodo nodo = getMinimo(nodosQuitados);
            nodosColocados.add(nodo);
            nodosQuitados.remove(nodo);
            buscarDistanciaMinima(nodo);
        }
    }
    
    private void buscarDistanciaMinima(Nodo nodo) {
        List<Nodo> nodosAdyacentes = getAdyacentes(nodo);
        for (Nodo target : nodosAdyacentes) {
            if(getDistanciaCorta(target) > getDistanciaCorta(nodo) + getDistancia(nodo, target)) {
                distancia.put(target, getDistanciaCorta(nodo) + getDistancia(nodo, target));
                predecesores.put(target, nodo);
                nodosQuitados.add(target);
            }
        }
    }
    
    private int getDistancia(Nodo nodo, Nodo target) {
        for (Arista arista : aristas) {
            if(arista.getOrigen().equals(nodo) && arista.getDestino().equals(target)) {
                return arista.getValor();
            }
        }
        throw new RuntimeException("No puede ser encontrado.");
    }
    
    private List<Nodo> getAdyacentes(Nodo nodo) {
        List<Nodo> adyacentes = new ArrayList<Nodo>();
        for (Arista arista : aristas) {
            if(arista.getOrigen().equals(nodo) && !esColocado(arista.getDestino())) {
                adyacentes.add(arista.getDestino());
            }
        }
        return adyacentes;
    }
    
    private Nodo getMinimo(Set<Nodo> nodos) {
        Nodo minimo = null;
        for (Nodo nodo : nodos) {
            if(minimo == null) {
                minimo = nodo;
            } else {
                if(getDistanciaCorta(nodo) < getDistanciaCorta(minimo)) {
                    minimo = nodo;
                }
            }
        }
        
        return minimo;
    }
    
    private boolean esColocado(Nodo nodo) {
        return nodosColocados.contains((nodo));
    }
    
    private int getDistanciaCorta(Nodo destino) {
        Integer d = distancia.get(destino);
        if(d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }
    
    /***
     * Método que devuelve la ruta más corta del origen al destino seleccionado
     * @param target objetivo
     * @return 
     */
    public LinkedList<Nodo> getRuta(Nodo target) {
        LinkedList<Nodo> ruta = new LinkedList<Nodo>();
        Nodo step = target;
        //Verificar si el path existe
        if(predecesores.get(step) == null) {
            return null;
        }
        ruta.add(step);
        while(predecesores.get(step) != null) {
            step = predecesores.get(step);
            ruta.add(step);
        }
        //Colocarlo en el orden correcto
        Collections.reverse(ruta);
        return ruta;
    }
}
