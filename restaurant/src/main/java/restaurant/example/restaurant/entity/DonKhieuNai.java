package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "cccdNhanVienQuanLy")
    NhanVienQuanLy  cccdNhanVienQuanLy 		;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cccdKhachHang" )
    KhachHang cccdKhachHang;

    String cccdTongDaiVien 		;
}
