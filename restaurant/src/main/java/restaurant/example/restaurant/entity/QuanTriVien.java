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

public class QuanTriVien {
    // @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String cccd 			 ;
	String moTaVaiTro 		;

    @OneToMany(mappedBy = "cccdQuanTriVien")
    List<ThongTin> listThongTin;

    @OneToMany(mappedBy = "cccdQuanTriVien")
    List<ChiNhanh> listChiNhanh;

    @OneToMany(mappedBy = "cccdQuanTriVien")
    List<ThongBao> listThongBao;




}
