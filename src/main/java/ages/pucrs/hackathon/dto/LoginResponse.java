package ages.pucrs.hackathon.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String name;
    private String role;
    private String id;
    private String companyId;
}
