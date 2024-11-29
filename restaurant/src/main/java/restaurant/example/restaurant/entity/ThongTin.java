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

public class ThongTin {

    @Id
    String cccd 		;
    String tenDangNhap  ;
    String matKhau 	 ;
    String ho 				;
    String ten 				;
    LocalDate ngaySinh 			;
    String email 				;
    String gioiTinh 			;
    @ElementCollection
    @CollectionTable(name = "SoDienThoai", joinColumns = @JoinColumn(name = "cccd"))
    @Column(name = "soDienThoai")
     List<String> soDienThoai;
//    String soDienThoai 			;
    String maTaiKhoan 			;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cccdQuanTriVien")
    NhanVienQuanLy cccdQuanTriVien 		;
    String anhThongTin;
//    List<KhachHang> listKhachHang;

    @OneToMany(mappedBy = "cccdThongTin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThongBao> listThongBao;


}


