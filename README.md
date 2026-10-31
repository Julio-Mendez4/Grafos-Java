# Grafos-Java

Implementación de un **grafo dirigido y ponderado** en Java usando **lista de adyacencia**,
con sus operaciones básicas (añadir, actualizar, borrar y consultar).

Proyecto del curso **Ingeniería de Software 2 – Universidad Central** (Semana 9: Grafos).

## Ejemplo usado

Red de vuelos de la Sesión 2 (los vértices son ciudades y el peso es el costo del vuelo):

| Origen   | Destino  | Costo |
|----------|----------|-------|
| Bogotá   | Medellín | 250   |
| Bogotá   | Cali     | 400   |
| Medellín | Cali     | 180   |
| Cali     | Bogotá   | 300   |

## Operaciones

| Tipo        | Métodos |
|-------------|---------|
| Añadir      | `agregarVertice`, `agregarArista` |
| Actualizar  | `actualizarPeso`, `renombrarVertice` |
| Borrar      | `eliminarArista`, `eliminarVertice` |
| Consultar   | `existeVertice`, `existeArista`, `obtenerPeso`, `vecinos`, `gradoEntrada`, `gradoSalida`, `gradoTotal`, `recorridoAnchura` |
| Mostrar     | `mostrarLista`, `mostrarMatriz` |

## Cómo ejecutarlo en NetBeans

1. `File > Open Project` y seleccionar la carpeta `Grafos-Java` (NetBeans la reconoce como proyecto Maven).
2. Clic derecho sobre el proyecto `> Run` (o `F6`).
3. Usar el menú de la consola. La opción **1** carga la red de vuelos de ejemplo.

## Estructura

```
Grafos-Java/
├── pom.xml
└── src/main/java/com/ucentral/grafos/
    ├── Grafo.java   // estructura de datos y operaciones
    └── Main.java    // menú interactivo por consola
```
