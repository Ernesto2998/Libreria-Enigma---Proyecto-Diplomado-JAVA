package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Sucursal")
public class Sucursal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sucursal")
    private Integer id;

    @NotBlank(message = "La calle no puede estar vacía")
    private String calle;

    @NotNull(message = "El número exterior no puede estar vacío")
    @Column(name = "numero_exterior")
    private Integer numeroExterior;

    @Column(name = "numero_interior")
    private String numeroInterior;

    @NotBlank(message = "La colonia no puede estar vacía")
    private String colonia;

    @NotBlank(message = "El municipio no puede estar vacío")
    private String municipio;

    @NotNull(message = "El código postal no puede estar vacío")
    private Integer codigoPostal;

//    @NotBlank(message = "El país no puede estar vacío")
//    private String pais;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais")
    private Pais pais;


    @Override
    public String toString() {
        return "Sucursal{" +
                "calle='" + calle + '\'' +
                ", id=" + id +
                ", numeroExterior=" + numeroExterior +
                ", numeroInterior='" + numeroInterior + '\'' +
                ", colonia='" + colonia + '\'' +
                ", municipio='" + municipio + '\'' +
                ", codigoPostal=" + codigoPostal + '\'' +
                '}';
    }
}
