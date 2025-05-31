package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.projection.FeedbackCountByType;
import ages.pucrs.hackathon.dto.SendFeedbackUserDTO;
import ages.pucrs.hackathon.entity.CardEntity;
import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.entity.ServiceEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.repository.FeedbackRepository;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CardService cardService;
    private final UserService userService;
    private final ServiceService serviceService;

    public FeedbackService(FeedbackRepository feedbackRepository, ServiceService serviceService, UserService userService, CardService cardService) {
        this.feedbackRepository = feedbackRepository;
        this.cardService = cardService;
        this.serviceService = serviceService;
        this.userService = userService;
        
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

    public List<FeedbackCountByType> getRetroByUser(UUID userId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date startDate = cal.getTime();
        return feedbackRepository.countFeedbacksByTypeForUserInLastMonth(userId, startDate);
    }

    public void registerFeedbackToUser(SendFeedbackUserDTO userFeedback){
        Optional<UserEntity> user = userService.findById(userFeedback.evaluator);
        Optional<CardEntity> card = cardService.findById(userFeedback.cardId);
        Date today = new Date();
        
        FeedbackEntity feedback = FeedbackEntity.builder()
                                    .evaluator(user.get())
                                    .idEvaluated(userFeedback.evaluator)
                                    .card(card.get())
                                    .description(userFeedback.description)
                                    .date(today)
                                    .isAnon(userFeedback.isAnon)
                                    .type(FeedbackEntity.Type.USER)
                                    .build();
        create(feedback);
    }
        
        public void registerFeedbackToService(SendFeedbackUserDTO userFeedback){
        Optional<UserEntity> user = userService.findById(userFeedback.evaluator);
        Optional<CardEntity> card = cardService.findById(userFeedback.cardId);
        Date today = new Date();
        
        FeedbackEntity feedback = FeedbackEntity.builder()
                                    .evaluator(user.get())
                                    .idEvaluated(userFeedback.idEvaluated)
                                    .card(card.get())
                                    .description(userFeedback.description)
                                    .date(today)
                                    .isAnon(userFeedback.isAnon)
                                    .type(FeedbackEntity.Type.SERVICE)
                                    .build();

        create(feedback);
    }
}