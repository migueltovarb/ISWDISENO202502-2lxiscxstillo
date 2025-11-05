package co.edu.editorial.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.editorial.modelo.Libro;
import org.bson.types.ObjectId;



public interface LibroRepository extends MongoRepository <Libro, ObjectId> {

}