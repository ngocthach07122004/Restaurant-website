package restaurant.example.restaurant.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongTinRequest {
    String tenDangNhap  ;
    String matKhau 	 ;
}
