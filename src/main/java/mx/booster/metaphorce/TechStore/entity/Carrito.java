package mx.booster.metaphorce.TechStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Table(name = "carrito")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<Map<Producto, Long>> productos;

    @OneToOne(mappedBy = "carrito")
    private Usuario usuario;
}
