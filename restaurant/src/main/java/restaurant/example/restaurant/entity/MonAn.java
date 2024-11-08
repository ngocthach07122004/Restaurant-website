package restaurant.example.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class MonAn {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maMonAn 			 ;
    Double gia 				;
    String tenMonAn 			;
    String moTa 			;

}
