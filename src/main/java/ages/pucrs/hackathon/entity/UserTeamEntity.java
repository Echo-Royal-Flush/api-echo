package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "user_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTeamEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity team;
}