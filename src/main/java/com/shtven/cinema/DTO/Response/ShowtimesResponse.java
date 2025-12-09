package com.shtven.cinema.DTO.Response;

import java.sql.Timestamp;

public class ShowtimesResponse {

    private Long id;
    private Timestamp showtime;
    private String roomName;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getShowtime() {
        return showtime;
    }

    public void setShowtime(Timestamp showtime) {
        this.showtime = showtime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
