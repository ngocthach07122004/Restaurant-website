package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ChiNhanh {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maChiNhanh;
    String diaChi; 
    String moTa; 
    String trangThaiHoatDong; 
    String tenChiNhanh; 
    Date hoiGianMoCua;
    Date hoiGianDongCua; 
    String cccdQuanTriVien; 

    
}
