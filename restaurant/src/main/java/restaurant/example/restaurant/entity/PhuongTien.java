package restaurant.example.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class PhuongTien {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String  bienSoXe 			 ;
    String  loaiPhuongTien 		 ;
    String  giayphepSoHuu 		 ;
    String thongTinDangKy 		 ;
    LocalDate thoiGianDangKy 		 ;
    LocalDate  thoiGianHetHan 		;
}
