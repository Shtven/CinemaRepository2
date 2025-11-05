package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT e FROM User e WHERE e.email=:email")
    Optional<User> findByEmail(String email);
}
