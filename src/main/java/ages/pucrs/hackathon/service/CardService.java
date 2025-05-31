package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.Card;
import ages.pucrs.hackathon.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> listAll() {
        return cardRepository.findAll();
    }

    public Optional<Card> findById(UUID id) {
        return cardRepository.findById(id);
    }

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card update(UUID id, Card cardData) {
        return cardRepository.findById(id)
                .map(card -> {
                    card.setCategory(cardData.getCategory());
                    card.setType(cardData.getType());
                    return cardRepository.save(card);
                }).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public void delete(UUID id) {
        cardRepository.deleteById(id);
    }
}