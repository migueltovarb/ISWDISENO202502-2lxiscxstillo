package figurasgeometricas;

public class Cubo extends Cuadrado {

    public Cubo(int lado) {
        super(lado);
    }

    @Override
    public double getArea() {
        // Área total del cubo = 6 * lado^2
        return 6 * Math.pow(valor1, 2);
    }
}
