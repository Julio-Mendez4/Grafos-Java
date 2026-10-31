package com.ucentral.grafos;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Grafo dirigido y ponderado implementado con LISTA DE ADYACENCIA.
 *
 * Cada vértice (por ejemplo, una ciudad) guarda un mapa con sus vecinos
 * de salida y el peso de cada arista (por ejemplo, el costo del vuelo).
 *
 *   Bogotá   -> {Medellín=250, Cali=400}
 *   Medellín -> {Cali=180}
 *   Cali     -> {Bogotá=300}
 *
 * Se usa LinkedHashMap para que los vértices se muestren en el mismo
 * orden en que se agregaron.
 */
public class Grafo {

    /** vértice -> (vecino -> peso) */
    private final Map<String, Map<String, Integer>> adyacencia;

    public Grafo() {
        this.adyacencia = new LinkedHashMap<>();
    }

    // =========================================================
    //  CONSULTAS BÁSICAS
    // =========================================================

    /** Indica si el vértice existe en el grafo. */
    public boolean existeVertice(String vertice) {
        return adyacencia.containsKey(vertice);
    }

    /** Número de vértices del grafo. */
    public int numVertices() {
        return adyacencia.size();
    }

    // =========================================================
    //  MOSTRAR
    // =========================================================

    /** Imprime la lista de adyacencia: Origen: Destino(peso), ... */
    public void mostrarLista() {
        if (adyacencia.isEmpty()) {
            System.out.println("  (el grafo está vacío)");
            return;
        }
        for (Map.Entry<String, Map<String, Integer>> fila : adyacencia.entrySet()) {
            StringBuilder linea = new StringBuilder("  " + fila.getKey() + ": ");
            if (fila.getValue().isEmpty()) {
                linea.append("(sin aristas de salida)");
            } else {
                int i = 0;
                for (Map.Entry<String, Integer> vecino : fila.getValue().entrySet()) {
                    if (i++ > 0) {
                        linea.append(", ");
                    }
                    linea.append(vecino.getKey()).append("(").append(vecino.getValue()).append(")");
                }
            }
            System.out.println(linea);
        }
    }
}
