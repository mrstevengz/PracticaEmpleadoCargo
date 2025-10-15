package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleados")
@Getter @Setter
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(name = "nombre_empleado", length = 100, unique = true, nullable = false)
    private String nombre;

    @Column(name = "apellido_empleado", length = 100, nullable = false)
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "nombre_cargo", nullable = false)
    private Cargo cargo;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Empleado{");
        sb.append("id=").append(id_empleado);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", cargo=").append(cargo);
        sb.append('}');
        return sb.toString();
    }
}
