package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maMonAnChiNhanh")
public class MonAnChiNhanh {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maMonAnChiNhanh  ;
    @ManyToOne
    @JoinColumn(name= "monAn")
    MonAn monAn;
    int soLuongMonAn;

    @ManyToMany(mappedBy = "listMonAnChiNhanh")
    List<ChiNhanh> listChiNhanh;

}
