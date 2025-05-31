package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "id_service")
    private Long idService;

    private Integer length;

    @Column(name = "id_company")
    private Long idCompany;
}
