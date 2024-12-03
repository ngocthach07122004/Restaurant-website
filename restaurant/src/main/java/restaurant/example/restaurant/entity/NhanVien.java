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

public class NhanVien {
    // @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String cccd 			 ;
	LocalDate ngayVaoLam 		;
	BigDecimal luong 			;

	@ManyToOne
	@JoinColumn(name="cccdNhanVienQuanLy")
	NhanVienQuanLy cccdNhanVienQuanLy	;

	String maChiNhanh 	;

    
}
