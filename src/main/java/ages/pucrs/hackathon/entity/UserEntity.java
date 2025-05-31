package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    private UUID id;

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String position;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    public enum Role {
        ADMIN, EMPLOYEE
    }
}
