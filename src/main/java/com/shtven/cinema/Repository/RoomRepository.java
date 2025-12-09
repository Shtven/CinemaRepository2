package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Rooms, Long> {

    @Query("SELECT COUNT(r) FROM Rooms r WHERE r.status=true")
    Long countByStatusTrue();
}
