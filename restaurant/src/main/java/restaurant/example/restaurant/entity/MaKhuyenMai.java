package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idKhuyenMai")
public class MaKhuyenMai {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String idKhuyenMai ;
    String moTa ;
    BigDecimal giaTriGiamGia 	;
    String dieuKienDung ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "maDon")
    @JsonIgnore
    DonMonAn maDon ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "maChiNhanh")
    
    ChiNhanh maChiNhanh ;


    @OneToMany(mappedBy = "maKhuyenMai")
   
    List<MaKhuyenMaiKhachHang> listMaKhuyenMaiKhachHang;
}
