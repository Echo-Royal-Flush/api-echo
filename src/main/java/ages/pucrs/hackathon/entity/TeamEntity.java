package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(name = "length")
    private Integer length;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;
}