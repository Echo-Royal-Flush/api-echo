package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.dto.PageDTO;
import ages.pucrs.hackathon.projection.FeedbackCountByType;
import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<FeedbackCountByType> getRetroByUser(UUID userId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date startDate = cal.getTime();
        return feedbackRepository.countFeedbacksByTypeForUserInLastMonth(userId, startDate);
    }

    public PageDTO<FeedbackEntity> pageReturn(Integer pagina, Integer tamanho) {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);
        Page<FeedbackEntity> feedbackEntityPage = feedbackRepository.findAll(pageable);

        return new PageDTO<>(
                feedbackEntityPage.getTotalElements(),
                feedbackEntityPage.getTotalPages(),
                feedbackEntityPage.getPageable().getPageNumber(),
                feedbackEntityPage.getSize(),
                feedbackEntityPage.getContent()
        );
    }
}