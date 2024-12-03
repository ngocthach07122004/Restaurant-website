package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class MonAn {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maMonAn 			 ;
    BigDecimal gia 				;
    String tenMonAn 			;
    String moTa 			;
    String khauPhan;
    String loaiMonAn;
    String thoiGianHoanTat ;
    String anhMonAn;

    @OneToMany(mappedBy = "monAn")
    @JsonIgnore
    List<MonAnGioHang>  listMonAnGioHang;
//    @OneToMany(mappedBy = "monAn", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    @JsonManagedReference("monAn-reference")
//     List<BaoGom> listBaoGom = new ArrayList<>();

}

