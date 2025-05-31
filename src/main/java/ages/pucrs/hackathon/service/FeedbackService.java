package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
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
}