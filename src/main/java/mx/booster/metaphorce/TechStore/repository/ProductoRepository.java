package mx.booster.metaphorce.TechStore.repository;

import mx.booster.metaphorce.TechStore.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByCategoria(String categoria, Pageable pageable);
    Page<Producto> findByCategoriaContaining(String categoria, Pageable pageable);
}
