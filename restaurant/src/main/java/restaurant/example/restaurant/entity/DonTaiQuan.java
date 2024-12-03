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

public class DonTaiQuan {
     @GeneratedValue(strategy = GenerationType.UUID)
    @Id
             String maDonTaiQuan;
     @OneToOne
             @JoinColumn(name = "maDon", referencedColumnName = "maDon")
    DonMonAn maDon 			;
    String yeuCauCuaKhachHang 	;
    @ManyToOne
    @JoinColumn(name="maNhanVienPhucVu" , referencedColumnName = "maNhanVienPhucVu")
    NhanVienPhucVu maNhanVienPhucVu 			;


    
}


