package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.dto.FeedbackCreateDTO;
import ages.pucrs.hackathon.dto.PageDTO;
import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.service.FeedbackService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<FeedbackEntity> create(@RequestBody FeedbackCreateDTO feedback) {
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

    @GetMapping("/paged")
    public ResponseEntity<PageDTO<FeedbackEntity>> listarClientes(@RequestParam(value = "userId", required = true) UUID userId,
                                                                  @RequestParam(value = "pagina", required = false ,defaultValue = "0") Integer pagina,
                                                                  @RequestParam(value = "tamanho", required = false, defaultValue = "10") Integer tamanho
    ){
        return new ResponseEntity<>(feedbackService.pageReturn(userId, pagina, tamanho), HttpStatus.OK);
    }
}