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

public class DonTaiQuan {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maDon 			;  
    String yeuCauCuaKhachHang 	; 
    String cccdPhucVu 			; 

    
}
