package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor



@Entity

public class  DonGiaoHang {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maDonGiaoHang;
    @OneToOne
    @JoinColumn(name = "maDon" , referencedColumnName = "maDon")
    DonMonAn maDon ;
    String tenNguoiNhan ;
    LocalDateTime thoiGianGiaoDuKien ;
    LocalDateTime  thoiGianNhanThucTe 	;
    String ghiChu 		;
    String tinhTrangDonHang ;
    String diaChiNhan 		;
    BigDecimal phiVanChuyen 	;
//    String cccdThuNgan 	;
    BigDecimal khoangCach ;
//    @OneToOne
//    @JoinColumn(name = "maNhanVienGiaoHang" , referencedColumnName = "maNhanVienGiaoHang")
//    NhanVienGiaoHang maNhanVienGiaoHang;
//
//    @OneToOne
//    @JoinColumn(name = "maKhachHang" , referencedColumnName = "maKhachHang")
//    KhachHang maKhachHang ;
 }
