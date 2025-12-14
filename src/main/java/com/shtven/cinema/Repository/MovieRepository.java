package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movies, Long> {
    @Query("SELECT m FROM Movies m WHERE m.active=true")
    List<Movies> findAllIfActivate();

    @Query("SELECT m FROM Movies m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%')) AND m.active = true")
    List<Movies> findByTitle(@Param("title") String title);
}