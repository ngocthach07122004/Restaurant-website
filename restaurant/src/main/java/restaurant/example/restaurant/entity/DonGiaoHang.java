package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
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

public class  DonGiaoHang {
    // @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maDon ;
    String tenNguoiNhan ;
    LocalDate thoiGianGiaoDuKien ;
    LocalDate  thoiGianNhanThucTe 	;
    String ghiChu 		;
    String tinhTrangDonHang ;
    String diaChiNhan 		;
    BigDecimal phiVanChuyen 	;
    String cccdThuNgan 	;
    BigDecimal khoangCach ;
 }
