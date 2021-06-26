package uz.muzaffar.hotel_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.hotel_10.entity.Hotel;
import uz.muzaffar.hotel_10.entity.Room;
import uz.muzaffar.hotel_10.payload.RoomDto;
import uz.muzaffar.hotel_10.repository.HotelRepository;
import uz.muzaffar.hotel_10.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Integer id) {
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()) return null;
        Optional<Room> byId1 = roomRepository.findById(id);
        return byId1.get();
    }

    @GetMapping("/{byHotelId}")
    public List<Room> getHotelBy(@PathVariable Integer byHotelId) {
        Optional<Hotel> byId2 = hotelRepository.findById(byHotelId);
        if (byId2.isPresent()) {
            return roomRepository.findByHotelId(byHotelId);
        }
        return null;
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Optional<Hotel> byId = hotelRepository.findById(roomDto.getHotel().getId());
        if (!byId.isPresent()) return "Hotel not found";
        Boolean aBoolean = roomRepository.existsByFloorAndNumberAndSize(roomDto.getFloor(), roomDto.getNumber(), roomDto.getSize());
        if (aBoolean) return "Already exist !!";
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setHotel(roomDto.getHotel());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "New Room is added !";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()) {
            roomRepository.deleteById(id);
            return "Deleted !@";
        }
        return "Wrong Room info !";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()) {
            Optional<Hotel> byId2 = hotelRepository.findById(roomDto.getHotel().getId());
            if (!byId2.isPresent()) return "Hotel info wrong !";
            Optional<Room> byId1 = roomRepository.findById(id);
            Room room = byId1.get();
            room.setSize(roomDto.getSize());
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setHotel(roomDto.getHotel());
            Boolean aBoolean = roomRepository.existsByFloorAndNumberAndSize(roomDto.getFloor(), roomDto.getNumber(), roomDto.getSize());
            if (!aBoolean) return "This room is already existed !";
            roomRepository.save(room);
            return "Successfully edited !";
        }
        return "Room not found";
    }
}

