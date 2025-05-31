package ages.pucrs.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String adNumber;

    @Column(name = "ad_cep")
    private String adCep;
}
