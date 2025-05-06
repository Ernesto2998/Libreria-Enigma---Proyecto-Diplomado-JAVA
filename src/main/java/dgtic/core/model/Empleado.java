package dgtic.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_empleado")
    private Integer numEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido_1", nullable = false, length = 50)
    private String apellido1;

    @Column(name = "apellido_2", nullable = false, length = 50)
    private String apellido2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acceso", nullable = false)
    private NivelAcceso nivelAcceso;

    @Column(nullable = false, length = 255)
    private String contrasenia;


    public enum Genero {
        F, M
    }

    public enum NivelAcceso {
        Admin, User
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "numEmpleado=" + numEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", genero=" + genero +
                ", nivelAcceso=" + nivelAcceso +
                '}';
    }
}
