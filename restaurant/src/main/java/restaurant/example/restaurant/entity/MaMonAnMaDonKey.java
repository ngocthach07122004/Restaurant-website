package restaurant.example.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MaMonAnMaDonKey  implements Serializable {
    @Column(name = "maMonAn")
    String maMonAnId;

    @Column(name = "maDon")
    String maDonId;

}
