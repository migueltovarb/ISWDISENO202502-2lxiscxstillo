package co.edu.editorial.service;

import co.edu.editorial.modelo.Disco;
import org.bson.types.ObjectId;
import java.util.List;

public interface IDiscoService {
    Disco crearDisco(Disco disco);
    Disco actualizarDisco(Disco disco);
    List<Disco> getTodosLosDiscos();
    Disco getDiscoPorId(ObjectId discoId);
    void eliminarDisco(ObjectId discoId);
}