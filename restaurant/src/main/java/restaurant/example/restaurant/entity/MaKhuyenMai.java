package restaurant.example.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Double giaTriGiamGia 	;
    String dieuKienDung ;
    String maDon ;
    String maChiNhanh ;
}
