package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.example.restaurant.entity.Comment;

public interface CommentRepository  extends JpaRepository<Comment,String> {
}
