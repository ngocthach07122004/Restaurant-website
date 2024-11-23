package restaurant.example.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class NhanVien {
    // @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String cccd 			 ;
	LocalDate ngayVaoLam 		;
	BigDecimal luong 			;
	String cccd_quan_ly 		;
	String maChiNhanh 	;

    
}
