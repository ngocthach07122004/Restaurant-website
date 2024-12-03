package restaurant.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class SuKienUuDai {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maUuDai 			;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "maChiNhanh")
    ChiNhanh maChiNhanh ;

    String  loaiMa 			;
    BigDecimal giaTriGiamGia 		;

}
