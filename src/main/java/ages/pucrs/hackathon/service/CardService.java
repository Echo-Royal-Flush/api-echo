package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.CardEntity;
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

    public List<CardEntity> listAll() {
        return cardRepository.findAll();
    }

    public Optional<CardEntity> findById(UUID id) {
        return cardRepository.findById(id);
    }

    public CardEntity create(CardEntity card) {
        return cardRepository.save(card);
    }

    public CardEntity update(UUID id, CardEntity cardData) {
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