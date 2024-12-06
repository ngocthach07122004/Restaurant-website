package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maThongBao")
public class ThongBao {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String maThongBao  ;
    String noiDungThongBao    ;
    LocalDateTime thoiGian  ;
//    String cccdThongTin  ;
    // String cccdQuanLy  ;

    @ManyToOne
   @JoinColumn(name="cccdQuanTriVien")
    @JsonIgnore
    QuanTriVien cccdQuanTriVien   ;

//    @ManyToOne
//    @JoinColumn(name = "cccdThongTin")
//    ThongTin cccdThongTin;

//    @ManyToMany(mappedBy = "listThongBao")
//    List<ThongTin> listThongTin;
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name ="ChuaThongBao",
        joinColumns = @JoinColumn(name = "maThongBao"),
        inverseJoinColumns = @JoinColumn(name = "cccd")

)
        @JsonIgnore
List<ThongTin> listThongTin;


    @ManyToOne
    @JoinColumn(name = "cccdNhanVienQuanLy")
            @JsonIgnore
    NhanVienQuanLy cccdNhanVienQuanLy;



}


