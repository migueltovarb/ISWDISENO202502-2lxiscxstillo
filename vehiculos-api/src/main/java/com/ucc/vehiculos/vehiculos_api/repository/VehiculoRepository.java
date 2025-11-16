package com.ucc.vehiculos.vehiculos_api.repository;

import com.ucc.vehiculos.vehiculos_api.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
}

