package mx.booster.metaphorce.TechStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.booster.metaphorce.TechStore.entity.Producto;
import mx.booster.metaphorce.TechStore.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

@RestController
@RequestMapping("/producto")
@Tag(name = "Producto")
@CrossOrigin(origins = "*") // Consider restricting origins for better security
@Validated
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/add")
    @Operation(summary = "Agrega un nuevo producto")
    public Producto addProduct(@PathVariable Producto producto) {
        return productoRepository.save(producto);
    }

    @PostMapping("/addList")
    @Operation(summary = "Agrega un nuevo producto")
    public void addListProducts(@RequestBody List<Producto> productos) {
        productoRepository.saveAll(productos);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtiene todos los productos")
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @GetMapping("/allByPages")
    @Operation(summary = "Obtiene todos los productos con paginación")
    public Set<String> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam String categoria) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productosPage = productoRepository.findByCategoriaContaining(categoria ,pageable);
        List<Producto> productos = productosPage.getContent();

        return productos.stream()
                .map(producto -> producto.getNombre() + ": " + producto.getInventario())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @GetMapping("/allByCategory")
    @Operation(summary = "Obtiene todos los productos con paginación y categoría opcional")
    public Set<String> getAllProductsByPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String categoria) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productosPage;

        if (categoria != null && !categoria.isEmpty()) {
            productosPage = productoRepository.findByCategoria(categoria, pageable);
        } else {
            productosPage = productoRepository.findAll(pageable);
        }

        List<Producto> productos = productosPage.getContent();

        return productos.stream()
                .map(producto -> producto.getNombre() + ": " + producto.getInventario())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Obtiene un producto por su ID")
    public Optional<Producto> getProduct(@PathVariable long id) {
        return productoRepository.findById(id);
    }

    @PatchMapping("/addInventory")
    @Operation(summary = "Incrementa el inventario de un producto por su ID")
    public Producto updateInventory(@RequestParam long id, @RequestParam int amount){
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()){
            producto.get().setInventario(producto.get().getInventario()+amount);
        }
        Producto producto1 = productoRepository.save(producto.get());
        return producto1;
    }
}
