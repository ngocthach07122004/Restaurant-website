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

public class NhanVienPhucVu {
     @GeneratedValue(strategy = GenerationType.UUID)
    @Id
     String maNhanVienPhucVu;
     @OneToOne
             @JoinColumn(name ="maNhanVien",referencedColumnName = "maNhanVien")
    NhanVien maNhanVien  ;

    
}
