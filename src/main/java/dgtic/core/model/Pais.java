package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pais")
public class Pais {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pais")
    private Integer id;

    @NotBlank(message = "El campo país no puede estar vacío")
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "pais")
    private List<Sucursal> sucursales;

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", pais='" + nombre + '\'' +
                '}';
    }
}
