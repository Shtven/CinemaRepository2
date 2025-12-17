package com.shtven.cinema.Service;

import com.shtven.cinema.DTO.Response.StatsResponse;
import com.shtven.cinema.Repository.MovieRepository;
import com.shtven.cinema.Repository.RoomRepository;
import com.shtven.cinema.Repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieRepository movieRepository;

    public StatsResponse getStats(){
        StatsResponse response = new StatsResponse();
        response.setAllShowtimes(showtimeRepository.count());
        response.setAllActiveRooms(roomRepository.countByStatusTrue());
        response.setAllMovies(movieRepository.count());
        return response;
    }
}
