package com.ucentral.grafos;

import java.util.Scanner;

/**
 * Menú por consola para probar las operaciones del grafo.
 * En NetBeans: clic derecho sobre el proyecto > Run (F6).
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Grafo grafo = new Grafo();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            System.out.println();
            switch (opcion) {
                case 1 -> cargarEjemplo();
                case 2 -> agregarVertice();
                case 3 -> agregarArista();
                case 4 -> actualizarPeso();
                case 5 -> renombrarVertice();
                case 6 -> eliminarArista();
                case 7 -> eliminarVertice();
                case 8 -> consultarArista();
                case 9 -> {
                    System.out.println("LISTA DE ADYACENCIA");
                    grafo.mostrarLista();
                }
                case 10 -> {
                    System.out.println("MATRIZ DE COSTOS (∞ = sin vuelo directo)");
                    grafo.mostrarMatriz();
                }
                case 11 -> {
                    System.out.println("GRADOS DE LOS VÉRTICES");
                    grafo.mostrarGrados();
                }
                case 12 -> recorrido();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========= GRAFO DE VUELOS =========");
        System.out.println(" 1. Cargar red de vuelos de ejemplo");
        System.out.println(" --- Añadir ---");
        System.out.println(" 2. Agregar ciudad (vértice)");
        System.out.println(" 3. Agregar vuelo (arista)");
        System.out.println(" --- Actualizar ---");
        System.out.println(" 4. Actualizar costo de un vuelo");
        System.out.println(" 5. Renombrar ciudad");
        System.out.println(" --- Borrar ---");
        System.out.println(" 6. Eliminar vuelo");
        System.out.println(" 7. Eliminar ciudad");
        System.out.println(" --- Consultar ---");
        System.out.println(" 8. Consultar si existe un vuelo");
        System.out.println(" 9. Mostrar lista de adyacencia");
        System.out.println("10. Mostrar matriz de costos");
        System.out.println("11. Mostrar grados");
        System.out.println("12. Recorrido en anchura (BFS)");
        System.out.println(" 0. Salir");
        System.out.println("===================================");
    }

    /** Red de vuelos de la Sesión 2 (diapositiva 48). */
    private static void cargarEjemplo() {
        grafo.agregarArista("Bogotá", "Medellín", 250);
        grafo.agregarArista("Bogotá", "Cali", 400);
        grafo.agregarArista("Medellín", "Cali", 180);
        grafo.agregarArista("Cali", "Bogotá", 300);
        System.out.println("Red de vuelos cargada:");
        grafo.mostrarLista();
    }

    private static void agregarVertice() {
        String c = leerTexto("Nombre de la ciudad: ");
        mensaje(grafo.agregarVertice(c),
                "Ciudad " + c + " agregada.",
                "No se pudo agregar: la ciudad ya existe o el nombre está vacío.");
    }

    private static void agregarArista() {
        String o = leerTexto("Ciudad de origen: ");
        String d = leerTexto("Ciudad de destino: ");
        int p = leerEntero("Costo del vuelo: ");
        mensaje(grafo.agregarArista(o, d, p),
                "Vuelo " + o + " -> " + d + " (" + p + ") agregado.",
                "No se pudo agregar: el vuelo ya existe, es a la misma ciudad o el costo es negativo.");
    }

    private static void actualizarPeso() {
        String o = leerTexto("Ciudad de origen: ");
        String d = leerTexto("Ciudad de destino: ");
        int p = leerEntero("Nuevo costo: ");
        mensaje(grafo.actualizarPeso(o, d, p),
                "Costo de " + o + " -> " + d + " actualizado a " + p + ".",
                "No se pudo actualizar: el vuelo no existe o el costo es negativo.");
    }

    private static void renombrarVertice() {
        String a = leerTexto("Nombre actual: ");
        String n = leerTexto("Nombre nuevo: ");
        mensaje(grafo.renombrarVertice(a, n),
                "Ciudad " + a + " renombrada a " + n + ".",
                "No se pudo renombrar: la ciudad no existe o el nombre nuevo ya está en uso.");
    }

    private static void eliminarArista() {
        String o = leerTexto("Ciudad de origen: ");
        String d = leerTexto("Ciudad de destino: ");
        mensaje(grafo.eliminarArista(o, d),
                "Vuelo " + o + " -> " + d + " eliminado.",
                "No se pudo eliminar: el vuelo no existe.");
    }

    private static void eliminarVertice() {
        String c = leerTexto("Ciudad a eliminar: ");
        mensaje(grafo.eliminarVertice(c),
                "Ciudad " + c + " y todos sus vuelos eliminados.",
                "No se pudo eliminar: la ciudad no existe.");
    }

    private static void consultarArista() {
        String o = leerTexto("Ciudad de origen: ");
        String d = leerTexto("Ciudad de destino: ");
        Integer p = grafo.obtenerPeso(o, d);
        System.out.println(p != null
                ? "Sí existe el vuelo " + o + " -> " + d + " con costo " + p + "."
                : "No existe vuelo directo " + o + " -> " + d + ".");
    }

    private static void recorrido() {
        String c = leerTexto("Ciudad de inicio: ");
        if (!grafo.existeVertice(c)) {
            System.out.println("La ciudad no existe.");
            return;
        }
        System.out.println("BFS desde " + c + ": " + String.join(" -> ", grafo.recorridoAnchura(c)));
    }

    // ---------- utilidades de lectura ----------

    private static void mensaje(boolean ok, String exito, String error) {
        System.out.println(ok ? "✔ " + exito : "✘ " + error);
    }

    private static String leerTexto(String texto) {
        System.out.print(texto);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String texto) {
        while (true) {
            System.out.print(texto);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número entero.");
            }
        }
    }
}
