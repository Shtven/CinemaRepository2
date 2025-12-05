package com.shtven.cinema.services;

import com.shtven.cinema.DTO.Mapping.ShowtimeMapping;
import com.shtven.cinema.DTO.Request.ShowtimeRequest;
import com.shtven.cinema.DTO.Responsive.ShowtimeDetails;
import com.shtven.cinema.DTO.Responsive.ShowtimesResponsive;
import com.shtven.cinema.Model.Showtimes;
import com.shtven.cinema.Repository.MovieRepository;
import com.shtven.cinema.Repository.RoomRepository;
import com.shtven.cinema.Repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ShowtimeMapping showtimeMapping;


    public void createShowtime(ShowtimeRequest request) {
        Showtimes showtime = showtimeMapping.toEntity(request);
        showtimeRepository.save(showtime);
    }

    public void deleteShowtime(Long idShowtime) {
        showtimeRepository.deleteById(idShowtime);
    }

    public void updateShowtime(Long idShowtime, ShowtimeRequest request) {
        Showtimes showtime = showtimeMapping.toEntity(request);
        showtime.setIdShowtime(idShowtime);
        showtimeRepository.save(showtime);
    }

    public List<ShowtimesResponsive> getShowtimesFromMovie(Long idMovie) {
        return showtimeRepository.findByMovieIdMovie(idMovie).stream().map(showtimeMapping::toResponsive).toList();
    }

    public ShowtimeDetails getShowtimeDetails(Long idShowtime) {
        return showtimeMapping.viewShowtimeDetails(idShowtime);
    }
}
