package com.ucc.vehiculos.vehiculos_api.controller;

import com.ucc.vehiculos.vehiculos_api.model.Vehiculo;
import com.ucc.vehiculos.vehiculos_api.repository.VehiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoController(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable String id) {
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(id);
        return vehiculoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVehiculo(@RequestBody Vehiculo vehiculo) {
        if (isNullOrEmpty(vehiculo.getMarca())
                || isNullOrEmpty(vehiculo.getModelo())
                || isNullOrEmpty(vehiculo.getPlaca())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Los campos 'marca', 'modelo' y 'placa' son obligatorios.");
        }

        Vehiculo creado = vehiculoRepository.save(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable String id, @RequestBody Vehiculo vehiculoDetalles) {
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(id);
        if (vehiculoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Vehiculo vehiculoExistente = vehiculoOpt.get();
        vehiculoExistente.setMarca(vehiculoDetalles.getMarca());
        vehiculoExistente.setModelo(vehiculoDetalles.getModelo());
        vehiculoExistente.setAnio(vehiculoDetalles.getAnio());
        vehiculoExistente.setColor(vehiculoDetalles.getColor());
        vehiculoExistente.setPlaca(vehiculoDetalles.getPlaca());
        vehiculoExistente.setTipo(vehiculoDetalles.getTipo());

        Vehiculo actualizado = vehiculoRepository.save(vehiculoExistente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable String id) {
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(id);
        if (vehiculoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        vehiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}

