package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.dto.FeedbackCreateDTO;
import ages.pucrs.hackathon.dto.PageDTO;
import ages.pucrs.hackathon.entity.CardEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.projection.FeedbackCountByType;
import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CardService cardService;
    private final UserService userService;
    private final TeamService teamService;


    public FeedbackService(FeedbackRepository feedbackRepository, CardService cardService, UserService userService, TeamService teamService) {
        this.feedbackRepository = feedbackRepository;
        this.cardService = cardService;
        this.userService = userService;
        this.teamService = teamService;
    }

    public List<FeedbackEntity> listAll() {
        return feedbackRepository.findAll();
    }

    public Optional<FeedbackEntity> findById(UUID id) {
        return feedbackRepository.findById(id);
    }

    public FeedbackEntity create(FeedbackCreateDTO dto) {
        sendMessageToUser(dto.idEvaluated());

        CardEntity card = cardService.findById(dto.idCard())
                .orElseThrow(() -> new RuntimeException("Card não encontrado"));

        UserEntity evaluator = userService.findById(dto.idEvaluator())
                .orElseThrow(() -> new RuntimeException("Avaliador não encontrado"));

        FeedbackEntity feedback = FeedbackEntity.builder()
                .id(UUID.randomUUID())
                .card(card)
                .evaluator(evaluator)
                .idEvaluated(dto.idEvaluated())
                .description(dto.description())
                .date(new Date())
                .isAnon(dto.isAnon())
                .type(dto.type())
                .build();

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

    public PageDTO<FeedbackEntity> pageReturn(UUID userId, Integer pagina, Integer tamanho) {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);

        Page<FeedbackEntity> feedbackEntityPage = feedbackRepository.findAllByIdEvaluated(userId, pageable);

        return new PageDTO<>(
                feedbackEntityPage.getTotalElements(),
                feedbackEntityPage.getTotalPages(),
                feedbackEntityPage.getNumber(),
                feedbackEntityPage.getSize(),
                feedbackEntityPage.getContent()
        );
    }

    @Async
    protected void sendMessageToUser(UUID userID){
        ZoneId zoneBrazil = ZoneId.of("America/Sao_Paulo");
        LocalDate today = LocalDate.now(zoneBrazil);

        Date startOfDay = Date.from(today.atStartOfDay(zoneBrazil).toInstant());
        Date endOfDay = Date.from(today.plusDays(1).atStartOfDay(zoneBrazil).toInstant());

        long total = feedbackRepository.countByDateBetweenAndEvaluatorId(startOfDay, endOfDay, userID);

        if(total >= 1){
            teamService.triggerUser(userID);
        }
    }
}