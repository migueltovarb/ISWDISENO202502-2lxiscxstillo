package co.edu.editorial.modelo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document(collection="Libros")
public class Libro extends Publicacion {
	
	@Id
	@JsonSerialize(using = ObjectIdSerializer.class)
	private ObjectId id;
	
	


	private int numeroPaginas;
	private int anyoPublicacion;
	

	public Libro(String titulo, float precio,int numeroPaginas, int anyoPublicacion) {
		super(titulo, precio);
		this.numeroPaginas=numeroPaginas;
		this.anyoPublicacion=anyoPublicacion;
	}


	public int getNumeroPaginas() {
		return numeroPaginas;
	}


	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}


	public int getAnyoPublicacion() {
		return anyoPublicacion;
	}


	public void setAnyoPublicacion(int anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}
	
	


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Libro [numeroPaginas=" + numeroPaginas + ", anyoPublicacion=" + anyoPublicacion + ", getTitulo()="
				+ getTitulo() + ", getPrecio()=" + getPrecio() + "]";
	}
	
	

}


