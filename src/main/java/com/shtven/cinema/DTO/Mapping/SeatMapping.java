package com.shtven.cinema.DTO.Mapping;

import com.shtven.cinema.DTO.Request.SeatRequest;
import com.shtven.cinema.Model.Seat;
import com.shtven.cinema.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatMapping {

    @Autowired
    private SeatRepository seatRepository;

    public void saveSeats(SeatRequest request, Long roomId) {
        for (List<Integer> entity : request.getSeats()) {
            Seat seat = new Seat();
            seat.setRoomId(roomId);
            seat.setRowNumber(entity.get(0));
            seat.setColumnNumber(entity.get(1));
            seat.setStatus(1);
            seatRepository.save(seat);
        }
    }

    public int[][] buildTicketMatrix(Long roomId) {
        List<Seat> seats = seatRepository.findByRoomId(roomId);
        int[][] matrix = new int[12][14];

        for (Seat seat : seats) {
            int row = seat.getRowNumber();
            int col = seat.getColumnNumber();
            matrix[row][col] = seat.getStatus();
        }

        return matrix;
    }
}
