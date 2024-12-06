//package restaurant.example.restaurant.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.time.LocalDateTime;
//
//
//@Data
//@Builder
//@FieldDefaults(level =  AccessLevel.PRIVATE)
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//
//public class BaoGom {
//    @EmbeddedId
//    MaMonAnMaDonKey maMonAnMaDonKey;
//
////    @JoinColumn(name = "maMonAn", nullable = false)
////    @JoinColumn(name = "maMonAn")
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JsonBackReference("monAn-reference")
//    @MapsId("maMonAnId")
//    @JoinColumn(name = "maMonAn", referencedColumnName = "maMonAn")
//    MonAn monAn;
//
//
////    @JoinColumn(name = "maDon", nullable = false)
//@ManyToOne(cascade = CascadeType.PERSIST)
//    @JsonBackReference("donMonAn-reference")
//    @MapsId("maDonId")
//    @JoinColumn(name = "maDon", referencedColumnName = "maDon")
//     DonMonAn donMonAn ;
//    @Column(name = "soLuongMonAn")
//     int soLuongMonAn ;
//
//
//
//}
