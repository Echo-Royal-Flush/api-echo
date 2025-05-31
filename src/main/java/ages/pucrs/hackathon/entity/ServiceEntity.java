package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}