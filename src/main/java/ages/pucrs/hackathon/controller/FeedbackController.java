package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.dto.SendFeedbackUserDTO;
import ages.pucrs.hackathon.entity.CardEntity;
import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackEntity> getAll() {
        return feedbackService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackEntity> getById(@PathVariable UUID id) {
        return feedbackService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackEntity> create(@RequestBody FeedbackEntity feedback) {
        return ResponseEntity.ok(feedbackService.create(feedback));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackEntity> update(@PathVariable UUID id, @RequestBody FeedbackEntity feedback) {
        return ResponseEntity.ok(feedbackService.update(id, feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sendUserFeedback")
    public ResponseEntity<Void> registerFeedbackToUser(@RequestBody SendFeedbackUserDTO userFeedback) {
        feedbackService.registerFeedbackToUser(userFeedback);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sendServiceFeedback")
    public ResponseEntity<Void> registerFeedbackToService(@RequestBody SendFeedbackUserDTO userFeedback){
        feedbackService.registerFeedbackToService(userFeedback);
        return ResponseEntity.noContent().build();
    }

}