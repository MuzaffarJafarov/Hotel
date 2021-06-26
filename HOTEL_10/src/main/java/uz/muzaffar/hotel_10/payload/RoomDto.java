package uz.muzaffar.hotel_10.payload;

import lombok.Data;
import uz.muzaffar.hotel_10.entity.Hotel;

@Data
public class RoomDto {
    private String number;
    private Integer floor;
    private Integer size;
    private Hotel hotel;

}
