package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

public class NhanVienGiaoHang {
     @GeneratedValue(strategy = GenerationType.UUID)
    @Id
     String maNhanVienGiaoHang;
     @OneToOne
     @JoinColumn(name="cccd", referencedColumnName = "maNhanVien")
     NhanVien cccd 				 ;
	String tinhTrangHoatDong 		;
    @ElementCollection
    @CollectionTable(name = "BangLaiXe", joinColumns = @JoinColumn(name = "cccd"))
    @Column(name = "bangLaiXe")
    private List<String> bangLaiXe;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="SuDung",
            joinColumns = @JoinColumn(name = "cccd"),
            inverseJoinColumns = @JoinColumn(name = "bienSoXe")

    )
    List<PhuongTien> listPhuongTien;





}
