package figurasgeometricas;

public class Cuadrado extends FiguraGeometrica {

    public Cuadrado(int lado) {
        super(lado);
    }

    @Override
    public double getArea() {
        return Math.pow(valor1, 2);
    }

    @Override
    public double getPerimetro() {
        return 4 * valor1;
    }
}
