package com.shtven.cinema.services;

import com.shtven.cinema.Model.Cards;
import com.shtven.cinema.Model.Users;
import com.shtven.cinema.Repository.CardRepository;
import com.shtven.cinema.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    public void saveCard(Cards card, Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent()) {
            card.setUsers(user.get());
            cardRepository.save(card);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }
    }

    public void DeleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    public List<Cards> findAllByUser(Long userId) {
        return cardRepository.findAllByUser(userId);
    }
}
