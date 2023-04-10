package pe.mmaw.projectventafactura.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ADMIN','USER','CUSTOMER')")
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference(value = "usuarioRole")
    private Usuario usuario;

    public enum Rol {
        ADMIN,
        USER,
        CUSTOMER
    }

    public Role(String username, String password, Rol rol, Usuario usuario) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.usuario = usuario;
    }
}
