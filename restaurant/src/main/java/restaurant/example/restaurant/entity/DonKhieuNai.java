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

public class DonKhieuNai {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String  maDonKhieuNai 		;
    String  noiDung 			;
    LocalDate thoiGian 			;
    String  maDon 			;
    String  cccdQuanLy 		;
    String  cccdKhachHang 		;
    String cccdTongDaiVien 		;
}
