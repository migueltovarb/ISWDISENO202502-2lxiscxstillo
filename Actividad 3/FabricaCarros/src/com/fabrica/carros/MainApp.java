package com.fabrica.carros;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        // Crear dos plantas
        Planta planta1 = new Planta(
                16, "Deportiva", 1200.5, "Acero",
                Arrays.asList("Rojo", "Negro", "Blanco")
        );

        Planta planta2 = new Planta(
                18, "Todo Terreno", 1400.0, "Aluminio",
                Arrays.asList("Azul", "Gris", "Verde")
        );

        // Cada planta fabrica 100 carros
        List<Carro> carrosPlanta1 = planta1.fabricarVariosCarros(100);
        List<Carro> carrosPlanta2 = planta2.fabricarVariosCarros(100);

        // Mostrar en pantalla
        System.out.println("=== Carros fabricados en Planta 1 ===");
        carrosPlanta1.forEach(System.out::println);

        System.out.println("\n=== Carros fabricados en Planta 2 ===");
        carrosPlanta2.forEach(System.out::println);
    }
}

