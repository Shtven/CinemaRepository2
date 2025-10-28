package com.shtven.cinema.DTO.Request;

import com.shtven.cinema.Model.Seat;

import java.util.List;

public class SeatRequest {

    private List<List<Integer>> seats;

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }
}
