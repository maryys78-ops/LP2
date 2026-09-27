package com.turnero.consultorio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;

    // Getters y Setters
}