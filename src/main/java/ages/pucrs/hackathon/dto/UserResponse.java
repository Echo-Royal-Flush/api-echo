package ages.pucrs.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class UserResponse {
    private UUID id;
    private String name;
}
