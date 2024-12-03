package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maChiNhanh")
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

    @OneToMany(mappedBy = "chiNhanh")
    List<NhanVienQuanLy> listNhanVienQuanLy;

    @ManyToOne
    @JoinColumn(name="cccdQuanTriVien")
    QuanTriVien cccdQuanTriVien   ;

    @OneToMany(mappedBy = "maChiNhanh")
            @JsonIgnore
    List<DonMonAn> listDonMonAn;

    @OneToMany(mappedBy = "maChiNhanh")
    List<MaKhuyenMai> listMaKhuyenMai;

    @OneToMany(mappedBy = "maChiNhanh")
    List<SuKienUuDai> listSuKienUuDai;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="MonAnThuocVe",
            joinColumns = @JoinColumn(name = "maChiNhanh"),
            inverseJoinColumns = @JoinColumn(name = "maMonAnChiNhanh")

    )
    List<MonAnChiNhanh> listMonAnChiNhanh;


}

