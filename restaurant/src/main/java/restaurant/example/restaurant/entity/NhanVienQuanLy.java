package restaurant.example.restaurant.entity;

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

public class NhanVienQuanLy {
    // @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String cccd 			 ;

    @ManyToOne
    @JoinColumn (name = "chiNhanh")
    ChiNhanh chiNhanh;

    @OneToMany(mappedBy = "cccdNhanVienQuanLy")
    List<ThongBao> listThongBao;

    @OneToMany(mappedBy = "cccdNhanVienQuanLy")
    List<NhanVien> listNhanVien;

    @OneToMany(mappedBy = "cccdNhanVienQuanLy")
    List<DonKhieuNai> listDonKhieuNai;


}
