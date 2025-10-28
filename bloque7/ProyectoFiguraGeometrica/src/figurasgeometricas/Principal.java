package figurasgeometricas;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== FIGURAS GEOMÉTRICAS ===");

        System.out.print("\nIngrese el radio del círculo: ");
        Circulo c = new Circulo(sc.nextInt());
        System.out.println("Área del círculo: " + c.getArea());
        System.out.println("Perímetro del círculo: " + c.getPerimetro());

        System.out.print("\nIngrese el lado del cuadrado: ");
        Cuadrado cu = new Cuadrado(sc.nextInt());
        System.out.println("Área del cuadrado: " + cu.getArea());
        System.out.println("Perímetro del cuadrado: " + cu.getPerimetro());

        System.out.print("\nIngrese el lado del cubo: ");
        Cubo cb = new Cubo(sc.nextInt());
        System.out.println("Área del cubo: " + cb.getArea());

        System.out.print("\nIngrese la base del rectángulo: ");
        int base = sc.nextInt();
        System.out.print("Ingrese la altura del rectángulo: ");
        int altura = sc.nextInt();
        Rectangulo r = new Rectangulo(base, altura);
        System.out.println("Área del rectángulo: " + r.getArea());
        System.out.println("Perímetro del rectángulo: " + r.getPerimetro());

        System.out.print("\nIngrese la base del triángulo: ");
        base = sc.nextInt();
        System.out.print("Ingrese la altura del triángulo: ");
        altura = sc.nextInt();
        Triangulo t = new Triangulo(base, altura);
        System.out.println("Área del triángulo: " + t.getArea());
        System.out.println("Perímetro del triángulo: " + t.getPerimetro());
    }
}
