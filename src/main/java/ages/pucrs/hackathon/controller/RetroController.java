package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.projection.FeedbackCountByType;
import ages.pucrs.hackathon.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/retro")
public class RetroController {
    private final FeedbackService feedbackService;

    public RetroController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{userId}")
    public List<FeedbackCountByType> getRetroByUser(@PathVariable UUID userId) {
        return feedbackService.getRetroByUser(userId);
    }
}
