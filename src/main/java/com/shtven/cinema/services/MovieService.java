package com.shtven.cinema.services;


import com.shtven.cinema.Model.Movies;
import com.shtven.cinema.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void saveMovie(Movies request) {
        request.setActive(true);
        movieRepository.save(request);
    }

    public void deleteMovie(Long idMovie) {
        Optional<Movies> movie = movieRepository.findById(idMovie);
        if(movie.isPresent()){
            Movies movies = movie.get();
            movies.setActive(false);
            movieRepository.save(movies);
        }else{
            throw new RuntimeException("Movie with id " + idMovie + " not found.");
        }
    }

    public void updateMovie(Movies request, Long idMovie) {
        Optional<Movies> movie = movieRepository.findById(idMovie);
        if(movie.isPresent()){
            Movies movies = movie.get();
            movies.setTitle(request.getTitle());
            movies.setDuration(request.getDuration());
            movies.setPrice(request.getPrice());
            movies.setGenre(request.getGenre());
            movies.setLanguage(request.getLanguage());
            movieRepository.save(movies);
        }else{
            throw new RuntimeException("Movie with id " + idMovie + " not found.");
        }
    }

    public List<Movies> getAllActiveMovies() {
        return movieRepository.findAllIfActivate();
    }
}
