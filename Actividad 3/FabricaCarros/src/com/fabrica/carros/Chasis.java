package com.fabrica.carros;

public class Chasis {
    private double peso;
    private String material;

    public Chasis(double peso, String material) {
        this.peso = peso;
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Chasis{peso=" + peso + ", material='" + material + "'}";
    }
}

