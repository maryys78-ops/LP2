package com.turnero.consultorio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email; // Representa el correo exigido por la consigna

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    // Atributos de la actividad
    private String nombre;
    private Integer edad;

    public enum Rol {
        SUPER_ADMIN,
        ADMIN,
        MEDICO,
        PACIENTE
    }

    // 1. Constructor vacío (requerido por JPA)
    public Usuario() {}

    // 2. Constructor de 4 parámetros (requerido por SuperAdminController)
    public Usuario(String username, String email, String password, Rol rol) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // 3. Constructor completo (6 parámetros)
    public Usuario(String username, String email, String password, Rol rol, String nombre, Integer edad) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.nombre = nombre;
        this.edad = edad;
    }
}
