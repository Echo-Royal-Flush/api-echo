package ages.pucrs.hackathon.dto;

import ages.pucrs.hackathon.entity.FeedbackEntity;

import java.util.UUID;

public record FeedbackCreateDTO(
        UUID idCard,
        UUID idEvaluator,
        UUID idEvaluated,
        String description,
        Boolean isAnon,
        FeedbackEntity.Type type // ou você pode usar um `Enum` diretamente
){}