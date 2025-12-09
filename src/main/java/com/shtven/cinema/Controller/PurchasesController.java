package com.shtven.cinema.Controller;

import com.shtven.cinema.DTO.Request.PurchaseRequest;
import com.shtven.cinema.DTO.Response.PurchaseResponse;
import com.shtven.cinema.services.PurchaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping()
    public ResponseEntity<Void> createPurchase(@RequestAttribute("idUser") Long idUser, @Valid @RequestBody PurchaseRequest request) {
        purchaseService.savePurchase(request, idUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{idPurchase}")
    public ResponseEntity<PurchaseResponse> getPurchase(@RequestAttribute("idUser") Long idUser, @PathVariable("idPurchase") Long idPurchase) {
        return ResponseEntity.ok(purchaseService.getPurchaseByIdForUser(idPurchase, idUser));
    }

    @GetMapping()
    public ResponseEntity<List<PurchaseResponse>> getAllPurchasesByUser(@RequestAttribute("idUser") Long idUser){
        return ResponseEntity.ok(purchaseService.getAllPurchasesByUser(idUser));
    }
}
