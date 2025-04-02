package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Autor")
public class Autor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_autor")
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido uno no puede estar vacío")
    @Column(name = "apellido_1")
    private String apellidoUno;

    @NotBlank(message = "El apellido dos no puede estar vacío")
    @Column(name = "apellido_2")
    private String apellidoDos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nacionalidad")
    private Nacionalidad nacionalidad;

//    @ManyToMany(mappedBy = "autores")
//    private List<Libro> libros;


    public Autor(String nombre, String apellidoUno, String apellidoDos, Nacionalidad nacionalidad) {
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.nacionalidad = nacionalidad;
    }

    public Autor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidoUno='" + apellidoUno + '\'' +
                ", apellidoDos='" + apellidoDos + '\'' +
                ", nacionalidad='" + nacionalidad.getNacionalidadName() + '\'' +
                '}';
    }
}
