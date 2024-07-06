package mx.booster.metaphorce.TechStore.repository;

import mx.booster.metaphorce.TechStore.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario existsByEmailAndPassword(String email, String password);
}
