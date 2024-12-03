package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class PhuongTien {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String  bienSoXe 			 ;
    String  loaiPhuongTien 		 ;
    String  giayphepSoHuu 		 ;
    String thongTinDangKy 		 ;
    LocalDate thoiGianDangKy 		 ;
    LocalDate  thoiGianHetHan 		;

    @ManyToMany(mappedBy = "listPhuongTien")
    List<NhanVienGiaoHang> listNhanVienGiaoHang;
}

