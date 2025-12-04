package com.shtven.cinema.DTO.Mapping;

import com.shtven.cinema.Model.Seats;
import com.shtven.cinema.Model.Showtimes;
import com.shtven.cinema.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatMapping {

    @Autowired
    private SeatRepository seatRepository;

    public void saveSeats(List<List<Integer>> seats, Showtimes showtimes) {
        for (List<Integer> seat : seats) {
            int row = seat.get(0);
            int col = seat.get(1);
            Seats seatsEntity = new Seats();
            seatsEntity.setRowNumber(row);
            seatsEntity.setColumnNumber(col);
            seatsEntity.setStatus(1);
            seatsEntity.setShowtime(showtimes);
            seatRepository.save(seatsEntity);
        }
    }

    public int[][] buildTicketMatrix(Long showtimeId) {
        List<Seats> seats = seatRepository.findByShowtime(showtimeId);
        int[][] matrix = new int[10][8];

        for (Seats seat : seats) {
            int row = seat.getRowNumber();
            int col = seat.getColumnNumber();
            matrix[row][col] = seat.getStatus();
        }

        return matrix;
    }
}
