package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.CardEntity;
import ages.pucrs.hackathon.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardEntity> getAll() {
        return cardService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardEntity> getById(@PathVariable UUID id) {
        return cardService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CardEntity> create(@RequestBody CardEntity card) {
        return ResponseEntity.ok(cardService.create(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardEntity> update(@PathVariable UUID id, @RequestBody CardEntity card) {
        return ResponseEntity.ok(cardService.update(id, card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}