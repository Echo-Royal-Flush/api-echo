package ages.pucrs.hackathon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserTeamRequest {
    @NotBlank(message = "userId should be informed")
    private String userId;

    @NotBlank(message = "teamId should be informed")
    private String teamId;
}