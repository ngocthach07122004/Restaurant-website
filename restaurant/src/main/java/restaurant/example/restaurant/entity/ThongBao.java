package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ThongBao {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maThongBao  ;
    String noiDungThongBao    ;
    LocalDate thoiGian  ;
//    String cccdThongTin  ;
    String cccdQuanLy  ;

    @ManyToOne
   @JoinColumn(name="cccdQuanTriVien")
    QuanTriVien cccdQuanTriVien   ;

    @ManyToOne
    @JoinColumn(name = "cccdThongTin")
    ThongTin cccdThongTin;


    @ManyToOne
    @JoinColumn(name = "cccdNhanVienQuanLy")
    NhanVienQuanLy cccdNhanVienQuanLy;



}
