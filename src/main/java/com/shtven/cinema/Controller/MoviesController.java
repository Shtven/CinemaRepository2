package com.shtven.cinema.Controller;

import com.shtven.cinema.Model.Movies;
import com.shtven.cinema.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Void> saveMovie(@Valid @RequestBody Movies request){
        movieService.saveMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteMOvie (@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Void> updateMovie (@PathVariable Long id, @Valid @RequestBody Movies request){
        movieService.updateMovie(request, id);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies (){
        List<Movies> movies = movieService.getAllActiveMovies();
        return ResponseEntity.ok(movies);
    }

}
