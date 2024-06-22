package mx.booster.metaphorce.TechStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;

@Table(name = "carrito")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carrito {
    @Id
    private long id;
    @OneToOne
    private long idUsuario;
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private LinkedList<Producto> productos;
}
