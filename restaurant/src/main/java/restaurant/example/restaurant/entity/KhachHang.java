package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class KhachHang {
     @GeneratedValue(strategy = GenerationType.UUID)
    @Id
     String maKhachHang;
     @OneToOne
    ThongTin cccd   ;
	LocalDate ngayThamGia ;
	String loaiKhachHang   ;

    @OneToMany(mappedBy = "cccdKhachHang")
    List<DonMonAn> listDonMonAn ;

    @OneToMany(mappedBy = "cccdKhachHang")
    List<DonKhieuNai> listDonKhieuNai ;

    int soDonDaDat =0;
    int soDonDaHuy =0;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="SoHuu",
            joinColumns = @JoinColumn(name = "cccd"),
            inverseJoinColumns = @JoinColumn(name = "maMaKhuyenMaiKhachHang")

    )
    List<MaKhuyenMaiKhachHang> listMaKhuyenMaiKhachHang;
    
}
