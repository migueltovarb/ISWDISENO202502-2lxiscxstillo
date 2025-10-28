package paquetefiguras;
import java.util.Scanner;

public class Cilindro extends Circulo {
    protected double altura;

    public Cilindro() {
        super();
        this.altura = 0.0;
    }

    public Cilindro(double radio, double altura) {
        super(radio);
        this.altura = altura;
    }

    public void leerDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el radio del cilindro: ");
        this.radio = sc.nextDouble();
        System.out.print("Ingrese la altura del cilindro: ");
        this.altura = sc.nextDouble();
    }

    @Override
    public double area() {
        // Área total del cilindro = 2πrh + 2πr²
        return 2 * Math.PI * radio * altura + 2 * Math.PI * Math.pow(radio, 2);
    }

    public double volumen() {
        // Volumen = πr²h
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
