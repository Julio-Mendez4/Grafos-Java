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
    //  AÑADIR (Create)
    // =========================================================

    /**
     * Agrega un vértice nuevo sin aristas.
     * @return true si se agregó, false si ya existía o el nombre es vacío.
     */
    public boolean agregarVertice(String vertice) {
        if (vertice == null || vertice.isBlank() || existeVertice(vertice)) {
            return false;
        }
        adyacencia.put(vertice, new LinkedHashMap<>());
        return true;
    }

    /**
     * Agrega la arista dirigida origen -> destino con su peso.
     * Si alguno de los vértices no existe, se crea automáticamente.
     * @return true si se agregó, false si la arista ya existía o el peso es negativo.
     */
    public boolean agregarArista(String origen, String destino, int peso) {
        if (peso < 0 || origen.equals(destino)) {
            return false;               // no se permiten pesos negativos ni bucles
        }
        agregarVertice(origen);
        agregarVertice(destino);
        if (existeArista(origen, destino)) {
            return false;               // para cambiar el peso se usa actualizarPeso
        }
        adyacencia.get(origen).put(destino, peso);
        return true;
    }

    // =========================================================
    //  ACTUALIZAR (Update)
    // =========================================================

    /**
     * Cambia el peso de una arista que ya existe.
     * @return true si se actualizó, false si la arista no existe o el peso es negativo.
     */
    public boolean actualizarPeso(String origen, String destino, int nuevoPeso) {
        if (nuevoPeso < 0 || !existeArista(origen, destino)) {
            return false;
        }
        adyacencia.get(origen).put(destino, nuevoPeso);
        return true;
    }

    /**
     * Cambia el nombre de un vértice conservando todas sus aristas
     * (las que salen de él y las que llegan a él).
     * @return true si se renombró, false si no existe o el nuevo nombre ya está en uso.
     */
    public boolean renombrarVertice(String actual, String nuevo) {
        if (!existeVertice(actual) || nuevo == null || nuevo.isBlank() || existeVertice(nuevo)) {
            return false;
        }
        // Se reconstruye el mapa para conservar el orden original de los vértices
        Map<String, Map<String, Integer>> copia = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String, Integer>> fila : adyacencia.entrySet()) {
            String clave = fila.getKey().equals(actual) ? nuevo : fila.getKey();
            Map<String, Integer> vecinos = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> v : fila.getValue().entrySet()) {
                String destino = v.getKey().equals(actual) ? nuevo : v.getKey();
                vecinos.put(destino, v.getValue());
            }
            copia.put(clave, vecinos);
        }
        adyacencia.clear();
        adyacencia.putAll(copia);
        return true;
    }

    // =========================================================
    //  BORRAR (Delete)
    // =========================================================

    /**
     * Elimina la arista dirigida origen -> destino.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminarArista(String origen, String destino) {
        if (!existeArista(origen, destino)) {
            return false;
        }
        adyacencia.get(origen).remove(destino);
        return true;
    }

    /**
     * Elimina un vértice y TODAS sus aristas:
     *  1) las que salen de él (se borran junto con su lista), y
     *  2) las que llegan a él desde otros vértices (hay que buscarlas).
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminarVertice(String vertice) {
        if (!existeVertice(vertice)) {
            return false;
        }
        adyacencia.remove(vertice);                      // 1) aristas de salida
        for (Map<String, Integer> vecinos : adyacencia.values()) {
            vecinos.remove(vertice);                     // 2) aristas de entrada
        }
        return true;
    }

    // =========================================================
    //  CONSULTAS BÁSICAS
    // =========================================================

    /** Indica si existe la arista dirigida origen -> destino. */
    public boolean existeArista(String origen, String destino) {
        return existeVertice(origen) && adyacencia.get(origen).containsKey(destino);
    }

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
