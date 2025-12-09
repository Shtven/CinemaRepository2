package com.shtven.cinema.services;

import com.google.zxing.WriterException;
import com.shtven.cinema.DTO.Mapping.SeatMapping;
import com.shtven.cinema.DTO.Request.PurchaseRequest;
import com.shtven.cinema.DTO.Response.SeatsResponse;
import com.shtven.cinema.Model.Movies;
import com.shtven.cinema.Model.Purchases;
import com.shtven.cinema.Model.Showtimes;
import com.shtven.cinema.Model.Users;
import com.shtven.cinema.Repository.MovieRepository;
import com.shtven.cinema.Repository.PurchaseRepository;
import com.shtven.cinema.Repository.ShowtimeRepository;
import com.shtven.cinema.Repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SeatMapping seatMapping;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private EmailService emailService;

    public void savePurchase(PurchaseRequest purchaseRequest, Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Showtimes> showtime = showtimeRepository.findById(purchaseRequest.getIdShowtime());
            if (showtime.isPresent()) {
                Purchases purchases = new Purchases();
                purchases.setDate(Timestamp.from(java.time.Instant.now()));
                purchases.setUser(user.get());
                purchases.setShowtime(showtime.get());
                Optional<Movies> movie = movieRepository.findById(showtime.get().getMovie().getIdMovie());
                if (movie.isPresent()) {
                    double totalAmount = movie.get().getPrice() * purchaseRequest.getSeats().size();
                    purchases.setTotalAmount(totalAmount);
                    seatMapping.saveSeats(purchaseRequest.getSeats(), showtime.get());

                    purchases = purchaseRepository.save(purchases);

                    String movies = movie.get().getTitle();
                    String rooms = showtime.get().getRoom().getName();
                    String seats = seatMapping.buildSeatsResponse(purchaseRequest.getSeats())
                            .stream()
                            .map(SeatsResponse::getSeatNumber)
                            .collect(Collectors.joining(", "));
                    String folio = "CP-" + purchases.getIdPurchase();
                    String total = String.format("$%.2f", purchases.getTotalAmount());

                    try{
                        emailService.loadHtmlTemplatePurchaseAndSend(movies, rooms, seats, folio, total, user.get().getEmail());
                    }catch(MessagingException | IOException | WriterException ex){
                        throw new RuntimeException("Failed to send confirmation email: " + ex.getMessage());
                    }
                }

            }
        }
    }

    public Purchases getPurchaseById(Long idPurchase) {
        Optional<Purchases> purchase = purchaseRepository.findById(idPurchase);
        return purchase.orElse(null);
    }

    public List<Purchases> getAllPurchasesByUser(Long userId) {
        return purchaseRepository.getAllPurchasesByUser(userId);
    }

}
