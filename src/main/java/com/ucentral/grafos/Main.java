package com.ucentral.grafos;

public class Main {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        System.out.println("Grafo creado. Vértices: " + grafo.numVertices());
        grafo.mostrarLista();
    }
}
