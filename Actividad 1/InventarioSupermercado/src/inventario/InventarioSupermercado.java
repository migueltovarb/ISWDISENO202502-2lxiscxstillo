package inventario;

import java.util.Scanner;

public class InventarioSupermercado {
    final static int MAX_PRODUCTOS = 5;

    public static void main(String[] args) {
        Scanner miScanner = new Scanner(System.in);
        String[][] productosYCantidadesDisponibles = new String[MAX_PRODUCTOS][2];
        int totalProductos = 0;

        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            for (int i1 = 0; i1 < 2; i1++) {
                if (i1 == 0) {
                    System.out.println("Ingresa el nombre del producto " + i + ": ");
                    productosYCantidadesDisponibles[i][i1] = miScanner.nextLine();
                } else {
                    int cantidad = -1;
                    while (cantidad < 0) {
                        System.out.println("Ingrese la cantidad de existencias de " + i + ": ");
                        cantidad = miScanner.nextInt();
                        if (cantidad < 0) {
                            System.out.println("No se permiten cantidades negativas");
                        }
                    }
                    productosYCantidadesDisponibles[i][i1] = String.valueOf(cantidad);
                    totalProductos += cantidad;
                    miScanner.nextLine();
                }
            }
        }

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n1. Mostrar productos");
            System.out.println("2. Buscar producto");
            System.out.println("3. Actualizar inventario");
            System.out.println("4. Alertas de baja existencia");
            System.out.println("5. Salir");
            System.out.print("Elige: ");
            opcion = miScanner.nextInt();
            miScanner.nextLine();

            if (opcion == 1) {
                totalProductos = 0;
                for (int i = 0; i < MAX_PRODUCTOS; i++) {
                    for (int i1 = 0; i1 < 2; i1++) {
                        if (i1 == 0) {
                            System.out.print(productosYCantidadesDisponibles[i][i1] + " -> ");
                        } else {
                            System.out.println(productosYCantidadesDisponibles[i][i1] + " unidades");
                            totalProductos += Integer.parseInt(productosYCantidadesDisponibles[i][i1]);
                        }
                    }
                }
                System.out.println("Total productos: " + totalProductos);
            } else if (opcion == 2) {
                System.out.print("Nombre del producto: ");
                String buscado = miScanner.nextLine();
                boolean encontrado = false;
                for (int i = 0; i < MAX_PRODUCTOS; i++) {
                    if (productosYCantidadesDisponibles[i][0].equalsIgnoreCase(buscado)) {
                        System.out.println("Cantidad: " + productosYCantidadesDisponibles[i][1]);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("No existe ese producto");
                }
            } else if (opcion == 3) {
                System.out.print("Producto a actualizar: ");
                String nombre = miScanner.nextLine();
                for (int i = 0; i < MAX_PRODUCTOS; i++) {
                    if (productosYCantidadesDisponibles[i][0].equalsIgnoreCase(nombre)) {
                        System.out.print("Cantidad a sumar/restar: ");
                        int cambio = miScanner.nextInt();
                        int nueva = Integer.parseInt(productosYCantidadesDisponibles[i][1]) + cambio;
                        if (nueva < 0) {
                            System.out.println("No se puede dejar en negativo");
                        } else {
                            productosYCantidadesDisponibles[i][1] = String.valueOf(nueva);
                        }
                        break;
                    }
                }
                miScanner.nextLine();
            } else if (opcion == 4) {
                for (int i = 0; i < MAX_PRODUCTOS; i++) {
                    if (Integer.parseInt(productosYCantidadesDisponibles[i][1]) < 10) {
                        System.out.println(productosYCantidadesDisponibles[i][0] + " tiene baja existencia (" + productosYCantidadesDisponibles[i][1] + ")");
                    }
                }
            }
        }
    }
}

