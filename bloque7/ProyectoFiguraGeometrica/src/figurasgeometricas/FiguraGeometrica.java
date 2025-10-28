package figurasgeometricas;

public class FiguraGeometrica {
    protected int valor1;

    public FiguraGeometrica() {
        this.valor1 = 0;
    }

    public FiguraGeometrica(int valor1) {
        this.valor1 = valor1;
    }

    public double getArea() {
        return 0.0;
    }

    public double getPerimetro() {
        return 0.0;
    }

    public int getValor1() {
        return valor1;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }
}

