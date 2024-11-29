package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class BaoGom {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maBaoGom;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "maMonAn", nullable = false)
    @JoinColumn(name = "maMonAnBaoGom")
//    @JsonBackReference
     MonAn maMonAnBaoGom;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "maDon", nullable = false)
    @JoinColumn(name = "maDonBaoGom")
//    @JsonBackReference
     DonMonAn maDonBaoGom;
    @Column(name = "soLuongMonAn")
     int soLuongMonAn ;


    
}
