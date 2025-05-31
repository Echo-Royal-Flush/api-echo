package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "id_evaluator")
    private User evaluator;

    @Column(name = "id_evaluated")
    private UUID idEvaluated;

    private String description;

    private Date date;

    @Column(name = "is_anon")
    private Boolean isAnon;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        USER, SERVICE
    }
}