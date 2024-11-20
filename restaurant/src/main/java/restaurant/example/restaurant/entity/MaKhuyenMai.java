package restaurant.example.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class MaKhuyenMai {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String idKhuyenMai ;
    String moTa ;
    BigDecimal giaTriGiamGia 	;
    String dieuKienDung ;
    String maDon ;
    String maChiNhanh ;
}
