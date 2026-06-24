package com.userSecurityLearning.userSecurityLearning.jwtImp.jwtController;

import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    public  String addRoom(){
        return "room added successfully";
    }
    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id){
    return "room fetched by id "+id;
    }

    @GetMapping
    public String getAllRooms(){
        return " All rooms fetched";
    }
}
