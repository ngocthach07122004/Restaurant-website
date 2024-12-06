package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maUuDai")
public class SuKienUuDai {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maUuDai 			;

    @ManyToOne
    @JoinColumn(name= "maChiNhanh")
    ChiNhanh maChiNhanh ;

    String  loaiMa 			;
    BigDecimal giaTriGiamGia 		;

    @ElementCollection
    @CollectionTable(name = "ThoiGianSuKien", joinColumns = @JoinColumn(name = "maUuDai"))
    @Column(name = "thoiGianSuKien")
    List<String> thoiGianSuKien;


    @ManyToMany(mappedBy = "listSuKienUuDai")

    List<MonAn> listMonAn;




}
