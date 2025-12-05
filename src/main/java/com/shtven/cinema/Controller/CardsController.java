package com.shtven.cinema.Controller;

import com.shtven.cinema.Model.Cards;
import com.shtven.cinema.services.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    CardService cardService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> saveCard(
            @PathVariable Long userId,
            @Valid @RequestBody Cards request
    ) {
        cardService.saveCard(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 sin body
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) {
        cardService.DeleteCard(cardId);
        return ResponseEntity.noContent().build(); // 204 sin body
    }

    // Obtener todas las tarjetas de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cards>> getCardsByUser(@PathVariable Long userId) {
        List<Cards> cards = cardService.findAllByUser(userId);
        return ResponseEntity.ok(cards); // 200 con JSON (lista de tarjetas)
    }
}
