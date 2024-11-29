package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class DonMonAn {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maDon 		 ;
    LocalDate  thoiGianDat 		 ;
    BigDecimal tongGiaTien 	;

    String maChiNhanh 		 ;
    String cccdKhachHang 		 ;
    String cccdNhanVienThuNgan 	 ;
    String tinhTrangThanhToan  ;

    @CollectionTable(name = "PhuongThucThanhToan", joinColumns = @JoinColumn(name = "maDon"))
    @Column(name = "phuongThucThanhToan")

    List<String> phuongThucThanhToan;

    @OneToMany(mappedBy = "maDonBaoGom", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<BaoGom> listBaoGom = new ArrayList<>();







//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name ="BaoGom",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "mission_id")
//
//    )
////    @Column(name = "cacMoTaKhac")
////    @JsonManagedReference
//    List<Mission> missions;






    
}
