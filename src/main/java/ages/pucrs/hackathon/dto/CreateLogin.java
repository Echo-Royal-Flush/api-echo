package ages.pucrs.hackathon.dto;

import lombok.Data;

@Data
public class CreateLogin {
    private String email;
    private String password;
    private String role;
    private String position;
}
