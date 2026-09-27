package com.turnero.consultorio.dto;

import com.turnero.consultorio.model.Usuario.Rol;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistroUsuarioDTO {
    private String username;
    private String email;
    private String password;
    private Rol rol;

}