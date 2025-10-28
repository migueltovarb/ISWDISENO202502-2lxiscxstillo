package paquetefiguras;
import java.util.Scanner;

public class CilindroHueco extends Cilindro {
    private double radioInterno;

    public CilindroHueco() {
        super();
        this.radioInterno = 0.0;
    }

    public CilindroHueco(double radio, double radioInterno, double altura) {
        super(radio, altura);
        this.radioInterno = radioInterno;
    }

    public void leerDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el radio externo del cilindro hueco: ");
        this.radio = sc.nextDouble();
        System.out.print("Ingrese el radio interno del cilindro hueco: ");
        this.radioInterno = sc.nextDouble();
        System.out.print("Ingrese la altura del cilindro hueco: ");
        this.altura = sc.nextDouble();
    }

    @Override
    public double area() {
        // Área total del cilindro hueco
        return 2 * Math.PI * (Math.pow(radio, 2) - Math.pow(radioInterno, 2))
                + 2 * Math.PI * radio * altura
                + 2 * Math.PI * radioInterno * altura;
    }

    @Override
    public double volumen() {
        // Volumen = π(r² - rInterno²)h
        return Math.PI * (Math.pow(radio, 2) - Math.pow(radioInterno, 2)) * altura;
    }

    public double getRadioInterno() {
        return radioInterno;
    }

    public void setRadioInterno(double radioInterno) {
        this.radioInterno = radioInterno;
    }
}

