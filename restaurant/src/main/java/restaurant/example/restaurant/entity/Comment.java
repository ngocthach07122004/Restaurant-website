package restaurant.example.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String idComment;
    LocalDateTime timeComment;
    String content;
    int numberLike =0 ;
    int numberDislike = 0 ;
    String maMonAn;
    String idUser;
}
