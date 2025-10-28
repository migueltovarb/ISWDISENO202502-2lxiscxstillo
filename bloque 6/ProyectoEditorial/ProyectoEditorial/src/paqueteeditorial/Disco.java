package paqueteeditorial;

public class Disco extends Publicacion {
    private float duracion;

    public Disco(String titulo, int precio, float duracion) {
        super(titulo, (float) precio);
        this.duracion = duracion;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "\n--- DISCO ---\n" +
               super.toString() +
               "\nDuración (min): " + duracion;
    }
}

