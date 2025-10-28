package paquetefiguras;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CÁLCULOS DE FIGURAS GEOMÉTRICAS ===");

        // ----- Círculo -----
        System.out.println("\n--- CÍRCULO ---");
        Circulo c = new Circulo();
        c.leerRadio();
        System.out.println("Circunferencia: " + c.circunferencia());
        System.out.println("Área del círculo: " + c.area());

        // ----- Cilindro -----
        System.out.println("\n--- CILINDRO ---");
        Cilindro cil = new Cilindro();
        cil.leerDatos();
        System.out.println("Área del cilindro: " + cil.area());
        System.out.println("Volumen del cilindro: " + cil.volumen());

        // ----- Cilindro Hueco -----
        System.out.println("\n--- CILINDRO HUECO ---");
        CilindroHueco ch = new CilindroHueco();
        ch.leerDatos();
        System.out.println("Área del cilindro hueco: " + ch.area());
        System.out.println("Volumen del cilindro hueco: " + ch.volumen());
    }
}
