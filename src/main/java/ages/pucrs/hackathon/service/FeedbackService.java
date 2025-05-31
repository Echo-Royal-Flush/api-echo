package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.CardEntity;
import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.repository.FeedbackRepository;
import ages.pucrs.hackathon.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final CardService cardService;

    public FeedbackService(FeedbackRepository feedbackRepository, UserService userService, CardService cardService) {
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
        this.cardService = cardService;
    }

    public List<FeedbackEntity> listAll() {
        return feedbackRepository.findAll();
    }

    public Optional<FeedbackEntity> findById(UUID id) {
        return feedbackRepository.findById(id);
    }

    public FeedbackEntity create(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    public FeedbackEntity update(UUID id, FeedbackEntity feedbackData) {
        return feedbackRepository.findById(id)
                .map(feedback -> {
                    feedback.setCard(feedbackData.getCard());
                    feedback.setEvaluator(feedbackData.getEvaluator());
                    feedback.setIdEvaluated(feedbackData.getIdEvaluated());
                    feedback.setDescription(feedbackData.getDescription());
                    feedback.setDate(feedbackData.getDate());
                    feedback.setIsAnon(feedbackData.getIsAnon());
                    feedback.setType(feedbackData.getType());
                    return feedbackRepository.save(feedback);
                }).orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    public void delete(UUID id) {
        feedbackRepository.deleteById(id);
    }

    public void registerFeedbackToUser(UUID userId, String card_type, String description){
        Optional<CardEntity> card = cardService.findByType(card_type);
        Date today = new Date();
        
        FeedbackEntity feedback = FeedbackEntity.builder()
                                    .idEvaluated(userId)
                                    .card(card.get())
                                    .description(description)
                                    .date(today)
                                    .build();

        create(feedback);
    }
}