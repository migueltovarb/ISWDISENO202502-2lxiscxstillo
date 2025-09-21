package persona;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir datos al usuario
        System.out.print("Introduce el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce la edad: ");
        int edad = sc.nextInt();

        System.out.print("Introduce el sexo (H/M): ");
        char sexo = sc.next().charAt(0);

        System.out.print("Introduce el peso (kg): ");
        double peso = sc.nextDouble();

        System.out.print("Introduce la altura (m): ");
        double altura = sc.nextDouble();

        // Crear objetos Persona
        Persona p1 = new Persona(nombre, edad, sexo, peso, altura);
        Persona p2 = new Persona(nombre, edad, sexo);
        Persona p3 = new Persona();
        p3.setNombre("Por Defecto");
        p3.setEdad(30);
        p3.setSexo('M');
        p3.setPeso(65);
        p3.setAltura(1.65);

        // Mostrar resultados
        mostrarInfo(p1);
        mostrarInfo(p2);
        mostrarInfo(p3);

        sc.close();
    }

    private static void mostrarInfo(Persona p) {
        // IMC
        int imc = p.calcularIMC();
        switch (imc) {
            case Persona.PESO_BAJO -> System.out.println(p.getNombre() + " está por debajo de su peso ideal.");
            case Persona.PESO_IDEAL -> System.out.println(p.getNombre() + " está en su peso ideal.");
            case Persona.SOBREPESO -> System.out.println(p.getNombre() + " tiene sobrepeso.");
        }

        // Mayoría de edad
        if (p.esMayorDeEdad()) {
            System.out.println(p.getNombre() + " es mayor de edad.");
        } else {
            System.out.println(p.getNombre() + " no es mayor de edad.");
        }

        // Mostrar objeto
        System.out.println(p.toString());
        System.out.println("------------------------------------------------");
    }
}

