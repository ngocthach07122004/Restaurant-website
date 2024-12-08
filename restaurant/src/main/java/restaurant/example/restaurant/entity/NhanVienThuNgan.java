package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class NhanVienThuNgan {
     @GeneratedValue(strategy = GenerationType.UUID)
    @Id
      String maNhanVienThuNgan;
    @OneToOne
    @JoinColumn(name ="cccd", referencedColumnName = "maNhanVien")
    NhanVien cccd 				 ;
    @OneToMany(mappedBy ="cccdNhanVienThuNgan" )
            @JsonIgnore
    List<DonMonAn> listDonMonAn;

    
}
