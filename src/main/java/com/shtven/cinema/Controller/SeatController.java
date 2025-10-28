package com.shtven.cinema.Controller;

import com.shtven.cinema.DTO.Mapping.SeatMapping;
import com.shtven.cinema.DTO.Request.SeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatMapping seatMapping;

    @PostMapping("/{id}")
    public ResponseEntity<?> createSeats(@PathVariable Long id, @RequestBody SeatRequest seatRequest) {
        seatMapping.saveSeats(seatRequest, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<int [][]> getSeatMatrix(@PathVariable Long id) {
        return ResponseEntity.ok(seatMapping.buildTicketMatrix(id));
    }
}
