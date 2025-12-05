package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Cards, Long> {
    @Query("SELECT c FROM Cards c WHERE c.user.idUser=:idUser")
    List<Cards> findAllByUser(@Param("idUser")Long idUser);
}
