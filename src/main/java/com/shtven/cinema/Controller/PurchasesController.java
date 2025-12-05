package com.shtven.cinema.Controller;

import com.shtven.cinema.DTO.Request.PurchaseRequest;
import com.shtven.cinema.services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/user/{id}")
    public ResponseEntity<Void> createPurchase(@PathVariable Long id, @Valid @RequestBody PurchaseRequest request) {
        purchaseService.savePurchase(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
