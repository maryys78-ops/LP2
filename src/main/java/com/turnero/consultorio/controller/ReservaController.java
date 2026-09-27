package com.turnero.consultorio.controller;

import com.turnero.consultorio.dto.ReservaDTO;
import com.turnero.consultorio.exception.ResourceNotFoundException;
import com.turnero.consultorio.model.Reserva;
import com.turnero.consultorio.model.Usuario;
import com.turnero.consultorio.repository.ReservaRepository;
import com.turnero.consultorio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Crear Reserva
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        Reserva reserva = new Reserva(
                dto.getFechaHora(),
                dto.getCantidadPersonas(),
                dto.getObservaciones(),
                usuario
        );

        Reserva nuevaReserva = reservaRepository.save(reserva);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    // 2. Listar todas las reservas
    @GetMapping
    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    // 3. Buscar Reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
        return ResponseEntity.ok(reserva);
    }

    // 4. Modificar Reserva
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO dto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        reserva.setFechaHora(dto.getFechaHora());
        reserva.setCantidadPersonas(dto.getCantidadPersonas());
        reserva.setObservaciones(dto.getObservaciones());
        reserva.setUsuario(usuario);

        Reserva actualizada = reservaRepository.save(reserva);
        return ResponseEntity.ok(actualizada);
    }

    // 5. Eliminar Reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        reservaRepository.delete(reserva);
        return ResponseEntity.ok("Reserva eliminada correctamente con ID: " + id);
    }
}