package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchases, Long> {
}
