package uz.muzaffar.hotel_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.hotel_10.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findByHotelId(Integer hotel_id);
    Boolean existsByFloorAndNumberAndSize(Integer floor, String number, Integer size);

}
