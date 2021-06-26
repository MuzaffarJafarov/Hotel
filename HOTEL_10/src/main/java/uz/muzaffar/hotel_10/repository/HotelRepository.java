package uz.muzaffar.hotel_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.hotel_10.entity.Hotel;
import uz.muzaffar.hotel_10.entity.Room;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Boolean existsByName(String name);


}
