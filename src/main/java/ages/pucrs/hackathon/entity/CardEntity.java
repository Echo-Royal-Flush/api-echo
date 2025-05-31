package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String category;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        POSITIVE, NEGATIVE
    }
}
