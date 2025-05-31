package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.Card;
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
    public List<Card> getAll() {
        return cardService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable UUID id) {
        return cardService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.create(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable UUID id, @RequestBody Card card) {
        return ResponseEntity.ok(cardService.update(id, card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}