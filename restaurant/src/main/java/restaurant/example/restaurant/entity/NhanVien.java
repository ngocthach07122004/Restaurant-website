package restaurant.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maNhanVien")
public class NhanVien {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
	String maNhanVien; 
	@OneToOne
     @JoinColumn(name = "cccd", referencedColumnName = "cccd")
    ThongTin cccd   ;
	LocalDateTime ngayVaoLam 		;
	BigDecimal luong 			;

	@ManyToOne
	@JoinColumn(name="cccdNhanVienQuanLy")
			@JsonIgnore
	NhanVienQuanLy cccdNhanVienQuanLy	;

	@ManyToOne
	@JoinColumn(name="maChiNhanh")
			@JsonIgnore
	ChiNhanh maChiNhanh 	;

    
}
