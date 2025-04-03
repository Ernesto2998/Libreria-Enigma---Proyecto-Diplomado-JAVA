package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Libro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_libro")
    private Integer id;

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @NotBlank(message = "El tipo de pasta no puede estar vacío")
    @Column(name = "tipo_pasta")
    private String tipoPasta;

    @NotBlank(message = "La sinopsis no puede estar vacía")
    private String sinopsis;

    @DecimalMin(value = "0.0",inclusive = false, message = "El precio no puede ser menor de $0.0")
    private Float precio;

    @Min(value = 0, message = "El decuento NO puede ser menor de 0%")
    @Max(value = 100, message = "El decuento NO puede superar el 100%")
    private Integer descuento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_clasificacion", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_libro"), // Clave foránea hacia `Libro`
            inverseJoinColumns = @JoinColumn(name = "id_clasificacion") // Clave foránea hacia `Clasificacion`
    )
    private List<Clasificacion> clasificaciones;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_libro"), // Clave foránea hacia `Libro`
            inverseJoinColumns = @JoinColumn(name = "id_autor") // Clave foránea hacia `Autor`
    )
    private List<Autor> autores;
}
