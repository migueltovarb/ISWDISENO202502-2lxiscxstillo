package figurasgeometricas;

public class Triangulo extends FiguraGeometrica {
    protected int valor2;

    public Triangulo(int base, int altura) {
        super(base);
        this.valor2 = altura;
    }

    @Override
    public double getArea() {
        return (valor1 * valor2) / 2.0;
    }

    @Override
    public double getPerimetro() {
        // Triángulo equilátero como ejemplo (puedes ajustar según necesidad)
        return 3 * valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }
}

