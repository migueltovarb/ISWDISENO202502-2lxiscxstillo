package paqueteeditorial;

public class Libro extends Publicacion {
    private int numPaginas;
    private int anioPublicacion;

    public Libro(String titulo, float precio, int numPaginas, int anioPublicacion) {
        super(titulo, precio);
        this.numPaginas = numPaginas;
        this.anioPublicacion = anioPublicacion;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public String toString() {
        return "\n--- LIBRO ---\n" +
               super.toString() +
               "\nNúmero de páginas: " + numPaginas +
               "\nAño de publicación: " + anioPublicacion;
    }
}


