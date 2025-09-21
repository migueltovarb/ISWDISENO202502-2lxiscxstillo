package com.fabrica.carros;

import java.util.List;

public class Carro {
    private String color;
    private List<Llanta> llantas;
    private Chasis chasis;

    public Carro(String color, List<Llanta> llantas, Chasis chasis) {
        this.color = color;
        this.llantas = llantas;
        this.chasis = chasis;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Llanta> getLlantas() {
        return llantas;
    }

    public void setLlantas(List<Llanta> llantas) {
        this.llantas = llantas;
    }

    public Chasis getChasis() {
        return chasis;
    }

    public void setChasis(Chasis chasis) {
        this.chasis = chasis;
    }

    @Override
    public String toString() {
        return "Carro{color='" + color + "', llantas=" + llantas + ", chasis=" + chasis + "}";
    }
}

