package com.shtven.cinema.Controller;

import com.shtven.cinema.DTO.Request.ShowtimeRequest;
import com.shtven.cinema.DTO.Responsive.ShowtimeDetails;
import com.shtven.cinema.DTO.Responsive.ShowtimesResponsive;
import com.shtven.cinema.services.ShowtimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimesController {

    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping
    public ResponseEntity<Void> createShowtime (@Valid @RequestBody ShowtimeRequest request){
        showtimeService.createShowtime(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Void> updateShowtime (@PathVariable Long id, @Valid @RequestBody ShowtimeRequest request){
        showtimeService.updateShowtime(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteShowtime (@PathVariable Long id){
        showtimeService.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<List<ShowtimesResponsive>> getShowtimesFromMovie(@PathVariable Long id) {
        List<ShowtimesResponsive> showtimes = showtimeService.getShowtimesFromMovie(id);
        return ResponseEntity.ok(showtimes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowtimeDetails> getShowtimeDetails(@PathVariable Long id ) {
        ShowtimeDetails details = showtimeService.getShowtimeDetails(id);
        return ResponseEntity.ok(details);
    }
}
