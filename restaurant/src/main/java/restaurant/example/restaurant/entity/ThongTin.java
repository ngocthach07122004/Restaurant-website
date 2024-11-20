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

public class ThongTin {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String cccd 		;
    String tenDangNhap  ;
    String matKhau 	 ;
    String ho 				;
    String ten 				;
    LocalDate ngaySinh 			;
    String email 				;
    String gioiTinh 			;
    String soDienThoai 			;
    String maTaiKhoan 			;
    String cccdQuanTriVien 		;

}
