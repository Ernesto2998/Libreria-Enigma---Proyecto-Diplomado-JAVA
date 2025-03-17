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
@Entity(name = "Autor")
public class Autor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_autor")
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    @Column(name = "apellido_1")
    private String apellidoUno;
    @NotBlank
    @Column(name = "apellido_2")
    private String apellidoDos;
    @NotBlank
    private String nacionalidad;

//    @ManyToMany(mappedBy = "autores")
//    private List<Libro> libros;

    public Autor(String nombre, String apellidoUno, String apellidoDos, String nacionalidad) {
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidoUno='" + apellidoUno + '\'' +
                ", apellidoDos='" + apellidoDos + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
