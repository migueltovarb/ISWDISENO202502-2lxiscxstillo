package com.fabrica.carros;

import java.util.*;

public class Planta {
    private int tamLlantas;
    private String tipoLlantas;
    private double pesoChasis;
    private String materialChasis;
    private List<String> colores;

    public Planta(int tamLlantas, String tipoLlantas, double pesoChasis, String materialChasis, List<String> colores) {
        this.tamLlantas = tamLlantas;
        this.tipoLlantas = tipoLlantas;
        this.pesoChasis = pesoChasis;
        this.materialChasis = materialChasis;
        this.colores = colores;
    }

    public Carro fabricarCarro() {
        List<Llanta> llantas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            llantas.add(new Llanta(tamLlantas, tipoLlantas));
        }
        Chasis chasis = new Chasis(pesoChasis, materialChasis);
        String color = colores.get(new Random().nextInt(colores.size()));
        return new Carro(color, llantas, chasis);
    }

    public List<Carro> fabricarVariosCarros(int n) {
        List<Carro> carros = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            carros.add(fabricarCarro());
        }
        return carros;
    }
}

