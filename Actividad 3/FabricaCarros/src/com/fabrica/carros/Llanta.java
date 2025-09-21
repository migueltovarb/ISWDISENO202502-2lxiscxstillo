package com.fabrica.carros;

public class Llanta {
    private int tamaño;
    private String tipo;

    public Llanta(int tamaño, String tipo) {
        this.tamaño = tamaño;
        this.tipo = tipo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Llanta{tamaño=" + tamaño + ", tipo='" + tipo + "'}";
    }
}
