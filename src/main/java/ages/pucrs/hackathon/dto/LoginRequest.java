package ages.pucrs.hackathon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email should be informed")
    private String email;

    @NotBlank(message = "Password should be informed")
    private String password;
}

