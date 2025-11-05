package co.edu.editorial.modelo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Document(collection = "discos")
public class Disco extends Publicacion {
    
    @Id
    private ObjectId id;
    
    private float duracion;
    
    public Disco() {
        super("", 0);
    }
    
    public Disco(String titulo, float precio, float duracion) {
        super(titulo, precio);
        this.duracion = duracion;
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	

	

}
