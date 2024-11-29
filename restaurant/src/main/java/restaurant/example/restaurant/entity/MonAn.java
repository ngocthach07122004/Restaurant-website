package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @OneToMany(mappedBy = "maMonAnBaoGom", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<BaoGom> listBaoGom = new ArrayList<>();

}
