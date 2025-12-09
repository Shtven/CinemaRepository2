package com.shtven.cinema.DTO.Mapping;

import com.shtven.cinema.DTO.Response.MovieResponse;
import com.shtven.cinema.Model.Movies;
import org.springframework.stereotype.Component;

@Component
public class MovieMapping {

    public MovieResponse movieView(Movies movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getIdMovie());
        response.setTitle(movie.getTitle());
        response.setGenre(movie.getGenre());
        int totalMinutes =
                movie.getDuration().getHour() * 60
                        + movie.getDuration().getMinute();
        response.setDuration(totalMinutes);
        response.setPosterPath(movie.getPosterPath());

        return response;
    }
}
