package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seats, Long> {
    @Query("SELECT s FROM Seats s WHERE s.showtime.idShowtime = :showtimeId")
    List<Seats> findByShowtime(Long showtimeId);
}
