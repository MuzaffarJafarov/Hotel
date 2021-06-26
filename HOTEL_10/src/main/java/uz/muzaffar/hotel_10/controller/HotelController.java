package uz.muzaffar.hotel_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.hotel_10.entity.Hotel;
import uz.muzaffar.hotel_10.payload.HotelDto;
import uz.muzaffar.hotel_10.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelBy(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (!byId.isPresent()) return null;
        Optional<Hotel> byId1 = hotelRepository.findById(id);
        return byId1.get();
    }

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {
        Boolean aBoolean = hotelRepository.existsByName(hotelDto.getName());
        if (aBoolean) return "This Hotel name is already exist !";
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotelRepository.save(hotel);
        return "New Hotel is created !";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            hotelRepository.deleteById(id);
            return "Deleted !@";
        }
        return "Wrong Hotel info !";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Optional<Hotel> byId1 = hotelRepository.findById(id);
            Hotel hotel = byId1.get();
            hotel.setName(hotelDto.getName());
            Boolean aBoolean = hotelRepository.existsByName(hotelDto.getName());
            if (!aBoolean) {
                hotelRepository.save(hotel);
                return "Successfully edited !";
            }
            return "This name is aready exist !";
        }
        return "Wrong hotel name !!";
    }
}
