package restaurant.example.restaurant.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonAnThuocDonMonAnResponse {
    String maDon 		 ;
    LocalDate thoiGianDat 		 ;
    BigDecimal tongGiaTien 	;

    String maChiNhanh 		 ;
    String cccdKhachHang 		 ;
    String cccdNhanVienThuNgan 	 ;
    String tinhTrangThanhToan  ;
    int soLuongMonAn;

}
