package restaurant.example.restaurant.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
//import restaurant.example.restaurant.entity.BaoGom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonAnThuocDonMonAnResponse {
    String maMonAn 			 ;
    BigDecimal gia 				;
    String tenMonAn 			;
    String moTa 			;
    String khauPhan;
    String loaiMonAn;
    String thoiGianHoanTat ;
    String anhMonAn;

    int soLuongMonAn;

}
