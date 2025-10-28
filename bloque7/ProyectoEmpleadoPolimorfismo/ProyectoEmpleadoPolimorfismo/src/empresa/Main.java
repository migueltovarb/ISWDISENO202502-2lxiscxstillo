package empresa;

public class Main {
    public static void main(String[] args) {
        Coche coche1 = new Coche("ABC123", "Toyota", "Corolla");
        Coche coche2 = new Coche("XYZ999", "Ford", "Focus");
        Secretario sec = new Secretario("Ana", "López", "12345678A", "C/ Mayor 1", "600111222", 2000.0, "D1", "911000111");
        Vendedor vend = new Vendedor("Carlos", "Pérez", "87654321B", "C/ Luna 2", "600222333", 1800.0, coche1, "600333444", "Zona Norte", 0.10);
        JefeZona jefe = new JefeZona("Laura", "Gómez", "11223344C", "C/ Sol 3", "600444555", 3000.0, "D2", coche2, sec);
        Cliente cli1 = new Cliente("Cliente 1", "699111222");

        jefe.altaVendedor(vend);
        vend.altaCliente(cli1);
        vend.cambiarSupervisor(jefe); 

        jefe.imprimir();
        sec.imprimir();
        vend.imprimir();
        
        System.out.println("Incrementando salarios específicos...");
        jefe.incrementarSalario();   
        sec.incrementarSalario();    
        vend.incrementarSalario();   

        jefe.imprimir();
        sec.imprimir();
        vend.imprimir();
    }
}

