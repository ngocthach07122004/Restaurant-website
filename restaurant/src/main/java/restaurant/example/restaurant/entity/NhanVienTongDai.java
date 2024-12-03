package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;



@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class NhanVienTongDai {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maNhanVienTongDai; 
    @OneToOne
    @JoinColumn(name = "cccd", referencedColumnName = "maNhanVien")
    NhanVien cccd 				 ;
     

    
}
