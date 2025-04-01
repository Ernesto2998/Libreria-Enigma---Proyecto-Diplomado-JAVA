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
@Entity(name = "nacionalidad")
public class Nacionalidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_nacionalidad")
    private Integer id;

    @NotBlank(message = "El campo nacionalidad no puede estar vacío")
    @Column(name = "nacionalidad")
    private String nacionalidadName;

    @OneToMany(mappedBy = "nacionalidad")
    private List<Autor> autores;

    public Nacionalidad(String nacionalidadName) {
        this.nacionalidadName = nacionalidadName;
    }

    @Override
    public String toString() {
        return "Nacionalidad{" +
                "id=" + id +
                ", nacionalidad='" + nacionalidadName + '\'' +
                '}';
    }
}
