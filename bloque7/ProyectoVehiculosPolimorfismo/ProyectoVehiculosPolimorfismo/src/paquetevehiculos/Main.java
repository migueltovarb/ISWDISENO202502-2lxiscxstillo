package paquetevehiculos;

public class Main {
    public static void main(String[] args) {
        Coche coche = new Coche("ABC123", 4);
        coche.acelerar(60);
        System.out.println(coche);

        Camion camion = new Camion("XYZ789");
        camion.acelerar(80);
        System.out.println(camion);

        Remolque remolque = new Remolque(2000);
        camion.ponRemolque(remolque);
        camion.acelerar(30);
        System.out.println(camion);
    }
}

