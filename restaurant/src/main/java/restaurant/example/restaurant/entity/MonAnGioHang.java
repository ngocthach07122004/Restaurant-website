package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maMonAnGioHang")
public class MonAnGioHang {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maMonAnGioHang  ;
    @ManyToOne
    @JoinColumn(name= "monAn")
    MonAn monAn;
    int soLuong;

    @ManyToMany(mappedBy = "listMonAnGioHang")
            @JsonIgnore
    List<DonMonAn> listDonMonAn;

}
