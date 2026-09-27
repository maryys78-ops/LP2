package com.turnero.consultorio.controller;

import com.turnero.consultorio.exception.ResourceNotFoundException;
import com.turnero.consultorio.model.Usuario;
import com.turnero.consultorio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Crear
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // 2. Listar todos
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // 3. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return ResponseEntity.ok(usuario);
    }

    // 4. Modificar
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario detalles) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (detalles.getUsername() != null) usuario.setUsername(detalles.getUsername());
        if (detalles.getEmail() != null) usuario.setEmail(detalles.getEmail());
        if (detalles.getPassword() != null) usuario.setPassword(detalles.getPassword());
        if (detalles.getRol() != null) usuario.setRol(detalles.getRol());
        if (detalles.getNombre() != null) usuario.setNombre(detalles.getNombre());
        if (detalles.getEdad() != null) usuario.setEdad(detalles.getEdad());

        Usuario actualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(actualizado);
    }

    // 5. Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        usuarioRepository.delete(usuario);
        return ResponseEntity.ok("Usuario eliminado correctamente con ID: " + id);
    }
}