package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchases, Long> {

    @Query("SELECT p FROM Purchases p WHERE p.user.idUser=:idUser")
    List<Purchases> getAllPurchasesByUser(Long idUser);
}
