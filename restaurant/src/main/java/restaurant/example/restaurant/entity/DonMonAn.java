package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maDon")
public class DonMonAn {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maDon 		 ;
    LocalDate thoiGianDat 		 ;
    BigDecimal tongGiaTien 	;

    @OneToMany(mappedBy = "maDon")
//    @OneToMany(mappedBy = "maDon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<MaKhuyenMai> listMaKhuyenMai;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "maChiNhanh")
    ChiNhanh maChiNhanh;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cccdKhachHang" )
            @JsonIgnore
    KhachHang cccdKhachHang;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cccdNhanVienThuNgan" )
            @JsonIgnore
    NhanVienThuNgan cccdNhanVienThuNgan 	 ;

    String tinhTrangThanhToan  ;
//    @ElementCollection
//    @CollectionTable(name = "PhuongThucThanhToan", joinColumns = @JoinColumn(name = "maDon"))
//    @Column(name = "phuongThucThanhToan")
//    List<String> phuongThucThanhToan;
    String phuongThucThanhToan;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="BaoGom",
            joinColumns = @JoinColumn(name = "maDon"),
            inverseJoinColumns = @JoinColumn(name = "maMonAnGioHang")

    )
            @JsonIgnore
    List<MonAnGioHang> listMonAnGioHang;




//    @OneToMany(mappedBy = "donMonAn", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JsonManagedReference("donMonAn-reference")
//     List<BaoGom> listBaoGom = new ArrayList<>();


    
}
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name ="BaoGom",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "mission_id")
//
//    )
////    @Column(name = "cacMoTaKhac")
////    @JsonManagedReference
//    List<Mission> missions;


// "maDon":"", 		  
// "thoiGianDat":"", 		  
// "tongGiaTien":"", 	 
// "listMaKhuyenMai":[], 
// "maChiNhanh":{}, 
// "cccdKhachHang":{}, 
// "cccdNhanVienThuNgan":{}, 	  
// "tinhTrangThanhToan":"",   
// "phuongThucThanhToan":[], 
// "listMonAnGioHang":[], 
