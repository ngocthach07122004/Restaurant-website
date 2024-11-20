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

public class ChiNhanh {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maChiNhanh;
    String diaChi; 
    String moTa; 
    String trangThaiHoatDong; 
    String tenChiNhanh; 
    LocalDate thoiGianMoCua;
    LocalDate  thoiGianDongCua; 
    String cccdQuanTriVien; 

    
}
