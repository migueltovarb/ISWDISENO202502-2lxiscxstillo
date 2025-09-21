package persona;

import java.util.Random;

public class Persona {
    // Constantes
    public static final char SEXO_HOMBRE = 'H';
    public static final char SEXO_MUJER = 'M';

    public static final int PESO_BAJO = -1;
    public static final int PESO_IDEAL = 0;
    public static final int SOBREPESO = 1;

    // Atributos privados
    private String nombre;
    private int edad;
    private String dni;
    private char sexo;
    private double peso;
    private double altura;

    // Constructor por defecto
    public Persona() {
        this.nombre = "";
        this.edad = 0;
        this.sexo = SEXO_HOMBRE;
        this.peso = 0.0;
        this.altura = 0.0;
        this.dni = generaDNI();
    }

    // Constructor con nombre, edad y sexo
    public Persona(String nombre, int edad, char sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = comprobarSexo(sexo);
        this.peso = 0.0;
        this.altura = 0.0;
        this.dni = generaDNI();
    }

    // Constructor con todos los atributos
    public Persona(String nombre, int edad, char sexo, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = comprobarSexo(sexo);
        this.peso = peso;
        this.altura = altura;
        this.dni = generaDNI();
    }

    // Métodos
    public int calcularIMC() {
        if (altura <= 0) {
            return PESO_BAJO; // Por seguridad si no hay altura válida
        }
        double imc = peso / (altura * altura);
        if (imc < 20) {
            return PESO_BAJO;
        } else if (imc <= 25) {
            return PESO_IDEAL;
        } else {
            return SOBREPESO;
        }
    }

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }

    // Privado: comprueba que el sexo sea válido
    private char comprobarSexo(char sexo) {
        if (sexo == SEXO_HOMBRE || sexo == SEXO_MUJER) {
            return sexo;
        }
        return SEXO_HOMBRE;
    }

    // Privado: genera un DNI válido
    private String generaDNI() {
        Random rand = new Random();
        int numero = 10000000 + rand.nextInt(90000000); // 8 dígitos
        char letra = calcularLetraDNI(numero);
        return numero + String.valueOf(letra);
    }

    private char calcularLetraDNI(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letras.charAt(numero % 23);
    }

    // Getters y Setters (excepto DNI)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = comprobarSexo(sexo);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Persona [Nombre=" + nombre +
                ", Edad=" + edad +
                ", DNI=" + dni +
                ", Sexo=" + sexo +
                ", Peso=" + peso +
                ", Altura=" + altura + "]";
    }
}

