package co.edu.editorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import co.edu.editorial.modelo.Disco;
import org.bson.types.ObjectId;

public interface DiscoRepository extends MongoRepository<Disco, ObjectId> {
}