package com.shtven.cinema.Controller;

import com.shtven.cinema.Model.Rooms;
import com.shtven.cinema.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping()
    public List<Rooms> getAllRooms() {
        return roomRepository.findAll();
    }
}
