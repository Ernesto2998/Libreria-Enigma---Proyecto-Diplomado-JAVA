package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "editorial")
public class Editorial {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_editorial")
    private Integer id;
    @NotBlank(message = "El campo editorial no puede estar vacío")
    @Column(name = "editorial")
    private String editorialName;

//    @OneToMany(mappedBy = "editorial")
//    private List<Libro> libros;

    public Editorial(String editorialName) {
        this.editorialName = editorialName;
    }

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", editorial='" + editorialName + '\'' +
                '}';
    }
}
