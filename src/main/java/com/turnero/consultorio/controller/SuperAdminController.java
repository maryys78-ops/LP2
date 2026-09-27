package com.turnero.consultorio.controller;

import com.turnero.consultorio.dto.RegistroUsuarioDTO;
import com.turnero.consultorio.model.Usuario;
import com.turnero.consultorio.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superadmin/usuarios")
public class SuperAdminController {

    private final UsuarioService usuarioService;

    public SuperAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO dto) {
        try {
            Usuario nuevoUsuario = new Usuario(
                    dto.getUsername(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getRol()
            );

            Usuario guardado = usuarioService.registrarUsuario(nuevoUsuario);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }
}
