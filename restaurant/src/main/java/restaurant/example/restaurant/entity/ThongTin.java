package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cccd")
public class ThongTin {

    @Id
    String cccd 		;
    String tenDangNhap  ;
    String matKhau 	 ;
    String ho 				;
    String ten 				;
    Date ngaySinh 			;
    String email 		;
    String gioiTinh 	;
    @ElementCollection
    @CollectionTable(name = "SoDienThoai", joinColumns = @JoinColumn(name = "cccd"))
    @Column(name = "soDienThoai")
     List<String> soDienThoai;

//    String soDienThoai 			;

    String maTaiKhoan 			;

    @ManyToOne
    @JoinColumn(name = "cccdQuanTriVien")
    @JsonIgnore
    QuanTriVien cccdQuanTriVien 		;

    String anhThongTin;

        @ManyToMany(mappedBy = "listThongTin")
    List<ThongBao> listThongBao;


}


