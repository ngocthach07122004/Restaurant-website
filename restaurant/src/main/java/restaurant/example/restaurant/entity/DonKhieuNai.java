package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


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
    LocalDateTime thoiGian 			;
   

    @ManyToOne
    @JoinColumn(name = "cccdNhanVienQuanLy")
    NhanVienQuanLy  cccdNhanVienQuanLy 		;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cccdKhachHang" )
    KhachHang cccdKhachHang;
    // @ManyToOne
    // @JoinColumn(name="cccdTongDaiVien" )
    // NhanVienTongDai cccdTongDaiVien ;
    @ManyToOne
            @JoinColumn(name = "donMonAn")
    DonMonAn donMonAn;

}
 

