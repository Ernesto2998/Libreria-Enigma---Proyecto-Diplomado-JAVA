package dgtic.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta_libro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_libro")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "num_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @Column(name = "cantidad_libros", nullable = false)
    private Byte cantidadLibros;
}