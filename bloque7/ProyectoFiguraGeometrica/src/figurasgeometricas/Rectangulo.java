package figurasgeometricas;

public class Rectangulo extends FiguraGeometrica {
    private int valor2;

    public Rectangulo(int base, int altura) {
        super(base);
        this.valor2 = altura;
    }

    @Override
    public double getArea() {
        return valor1 * valor2;
    }

    @Override
    public double getPerimetro() {
        return 2 * (valor1 + valor2);
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }
}

