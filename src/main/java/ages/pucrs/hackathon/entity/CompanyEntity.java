package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(name = "ad_country")
    private String adCountry;

    @Column(name = "ad_state")
    private String adState;

    @Column(name = "ad_city")
    private String adCity;

    @Column(name = "ad_street")
    private String adStreet;

    @Column(name = "ad_number")
    private Integer adNumber;

    @Column(name = "ad_cep")
    private Integer adCep;
}
