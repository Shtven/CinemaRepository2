package com.shtven.cinema.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "showtimes")
public class Showtimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_showtime")
    private Long idShowtime;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    private Rooms room;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false)
    private Movies movie;

    @Column(name = "showtime", nullable = false)
    private Timestamp showtime;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public Long getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(Long idShowtime) {
        this.idShowtime = idShowtime;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public Timestamp getShowtime() {
        return showtime;
    }

    public void setShowtime(Timestamp showtime) {
        this.showtime = showtime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

