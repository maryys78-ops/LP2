package com.turnero.consultorio.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "reservas")
public class Reserva {

    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private Integer cantidadPersonas;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Reserva() {}

    public Reserva(LocalDateTime fechaHora, Integer cantidadPersonas, String observaciones, Usuario usuario) {
        this.fechaHora = fechaHora;
        this.cantidadPersonas = cantidadPersonas;
        this.observaciones = observaciones;
        this.usuario = usuario;
    }

    public void setId(Long id) { this.id = id; }

    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public void setCantidadPersonas(Integer cantidadPersonas) { this.cantidadPersonas = cantidadPersonas; }

    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
