package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cargo;

    @Column(name = "nombre_cargo", length = 100, nullable = false, unique = true)
    private String nombreCargo;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cargo{");
        sb.append("id=").append(id_cargo);
        sb.append(", nombreCargo='").append(nombreCargo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
