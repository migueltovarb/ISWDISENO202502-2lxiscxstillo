package paquetefiguras;
import java.util.Scanner;

public class Circulo {
    protected double radio;

    public Circulo() {
        this.radio = 0.0;
    }

    public Circulo(double radio) {
        this.radio = radio;
    }

    public void leerRadio() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el radio del círculo: ");
        this.radio = sc.nextDouble();
    }

    public double area() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double circunferencia() {
        return 2 * Math.PI * radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
}

