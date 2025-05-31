package ages.pucrs.hackathon.projection;

import ages.pucrs.hackathon.entity.CardEntity;

public interface FeedbackCountByType{
    CardEntity.Type getType();
    Long getCount();
}
