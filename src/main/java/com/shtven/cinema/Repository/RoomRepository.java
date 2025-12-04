package com.shtven.cinema.Repository;

import com.shtven.cinema.Model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms, Long> {
}
