package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "clasificacion")
public class Clasificacion {
    @Id
    @Column(name = "id_clasificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotBlank(message = "El campo clasificacion no puede estar vacío")
    @Column(name = "tipo_clasificacion")
    private String tipoClasificacion;

//    @ManyToMany(mappedBy = "clasificaciones")
//    private List<Libro> libros;

    public Clasificacion(String tipoClasificacion) {
        this.tipoClasificacion = tipoClasificacion;
    }

    @Override
    public String toString() {
        return "Clasificacion{" +
                "idClasificacion=" + id +
                ", tipoClasificacion='" + tipoClasificacion + '\'' +
                '}';
    }
}
