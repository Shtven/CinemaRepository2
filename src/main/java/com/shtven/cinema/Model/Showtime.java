package com.shtven.cinema.Model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "horario")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long id;

    @Column(name = "id_sala")
    private Long roomId;

    @Column(name = "id_pelicula")
    private Long movieId;

    @Column(name = "fecha_hora")
    private Timestamp showtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Timestamp getShowtime() {
        return showtime;
    }

    public void setShowtime(Timestamp showtime) {
        this.showtime = showtime;
    }
}

