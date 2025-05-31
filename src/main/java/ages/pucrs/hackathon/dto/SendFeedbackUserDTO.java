package ages.pucrs.hackathon.dto;

import java.util.UUID;

import ages.pucrs.hackathon.entity.CardEntity;
import ages.pucrs.hackathon.entity.UserEntity;

public class SendFeedbackUserDTO {
    public UUID cardId;
    public UUID evaluator;
    public UUID idEvaluated;
    public String description;
    public Boolean isAnon;

    public SendFeedbackUserDTO(UUID cardId, UUID evaluator, UUID idEvaluated, String description,
            Boolean isAnon) {
        this.cardId = cardId;
        this.evaluator = evaluator;
        this.idEvaluated = idEvaluated;
        this.description = description;
        this.isAnon = isAnon;
    }

    
}
