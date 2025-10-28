package paquetevehiculos;

public class Camion extends Vehiculo {
    private Remolque remolque;

    public Camion(String matricula) {
        super(matricula);
        this.remolque = null;
    }

    public void ponRemolque(Remolque r) {
        this.remolque = r;
    }

    public void quitaRemolque() {
        this.remolque = null;
    }

    @Override
    public void acelerar(double cantidad) {
        double nuevaVelocidad = this.velocidad + cantidad;
        if (remolque != null && nuevaVelocidad > 100) {
            System.out.println("¡Demasiado rápido! El camión con remolque no puede superar 100 km/h.");
        } else {
            this.velocidad = nuevaVelocidad;
        }
    }

    @Override
    public String toString() {
        String info = super.toString();
        if (remolque != null) {
            info += " | " + remolque.toString();
        }
        return info;
    }
}
