package ages.pucrs.hackathon.dto;

import ages.pucrs.hackathon.entity.ServiceEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TeamRequest {
    @NotBlank(message = "Name should be informed")
    private String name;

    @NotBlank(message = "Length should be informed")
    private Integer length;

    @NotBlank(message = "Service should be informed")
    private ServiceEntity service;

    @NotBlank(message = "Members should be informed")
    private List<UUID> members;

    @NotBlank(message = "Company id should be informed")
    private String companyId;
}
