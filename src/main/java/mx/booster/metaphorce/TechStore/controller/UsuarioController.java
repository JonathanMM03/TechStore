package mx.booster.metaphorce.TechStore.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.booster.metaphorce.TechStore.entity.Producto;
import mx.booster.metaphorce.TechStore.entity.Usuario;
import mx.booster.metaphorce.TechStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario")
@CrossOrigin(origins = "*") // Consider restricting origins for better security
@Validated
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    @Operation(summary = "Registra un nuevo usuario")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getEmail().isEmpty() || usuario.getPassword().isEmpty()) {
            // Return bad request with JSON message
            String mensaje = String.format("{\"error\": \"%s\"}", "Error al registrar nuevo usuario");
            return ResponseEntity.badRequest().body(mensaje);
        }

        usuario.setFechaRegistro(Instant.now().toString());
        Usuario savedUser = usuarioRepository.save(usuario);
        return ResponseEntity.ok(savedUser);
    }
}
