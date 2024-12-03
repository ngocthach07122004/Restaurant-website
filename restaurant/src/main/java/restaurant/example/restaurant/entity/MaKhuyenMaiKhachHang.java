package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maMaKhuyenMaiKhachHang")
public class MaKhuyenMaiKhachHang {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maMaKhuyenMaiKhachHang  ;
    @ManyToOne
    @JoinColumn(name= "maKhuyenMai")
    MaKhuyenMai maKhuyenMai;
    int soLuong;

    @ManyToMany(mappedBy = "listMaKhuyenMaiKhachHang")
    List<KhachHang> listKhachHang;

}
