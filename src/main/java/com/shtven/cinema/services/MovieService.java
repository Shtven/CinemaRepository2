package com.shtven.cinema.services;


import com.shtven.cinema.DTO.Mapping.MovieMapping;
import com.shtven.cinema.DTO.Response.MovieResponse;
import com.shtven.cinema.Model.Movies;
import com.shtven.cinema.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Value("${app.upload.dir}")
    private String uploadDir;
    @Autowired
    private MovieMapping movieMapping;

    public void saveMovie(Movies request, MultipartFile file) throws IOException {

        request.setActive(true);
        Movies savedMovie = movieRepository.save(request);

        if (file != null && !file.isEmpty()) {
            movieRepository.save(savePoster(savedMovie, file));
        }
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

    public List<MovieResponse> getAllActiveMovies() {
        return movieRepository.findAllIfActivate().stream().map(movieMapping::movieView).toList();
    }

    private Movies savePoster(Movies movie, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty.");
        }

        try {
            Path postersDir = Paths.get(uploadDir, "posters");
            Files.createDirectories(postersDir);

            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            if (extension == null) {
                extension = ".png";
            }

            String filename = "movie_" + movie.getIdMovie() + "_" + System.currentTimeMillis() + extension;

            Path filePath = postersDir.resolve(filename);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String relativePath = "/uploads/posters/" + filename;
            movie.setPosterPath(relativePath);
            return movie;

        } catch (IOException e) {
            throw new RuntimeException("Error saving poster: " + e.getMessage(), e);
        }
    }


    public String getPosterPath(Long idMovie) {
        Optional<Movies> movie = movieRepository.findById(idMovie);
        return movie.map(Movies::getPosterPath).orElse(null);
    }

    private String getFileExtension(String filename) {
        if (filename == null) return null;
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return null;
        }
        return filename.substring(dotIndex);
    }
}
