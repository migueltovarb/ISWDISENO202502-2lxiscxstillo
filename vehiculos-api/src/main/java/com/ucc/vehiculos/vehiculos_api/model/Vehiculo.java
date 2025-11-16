package com.ucc.vehiculos.vehiculos_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehiculos")
public class Vehiculo {

    @Id
    private String id;

    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private String placa;
    private String tipo;
}

