package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "users_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTeamEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_users")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity team;
}