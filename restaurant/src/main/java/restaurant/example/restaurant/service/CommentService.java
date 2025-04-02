package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import restaurant.example.restaurant.dto.request.CommentNumberLike;
import restaurant.example.restaurant.entity.Comment;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.CommentMapper;
import restaurant.example.restaurant.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;
    public Comment createComment(Comment comment){
        Comment newComment = commentMapper.toComment(comment);
        return commentRepository.save(newComment);

    }
   public  String deleteComment(String idComment ){
         commentRepository.deleteById(idComment);
         return "delete success";
   }
    public List<Comment> getAllCommentByIdMonAn( String idComment){
          List<Comment> listComment = commentRepository.findAll();
          return listComment.stream().filter(comment -> comment.getIdComment().equals(idComment)).collect(Collectors.toList());
   }
   public Comment updateNumberLike (String idComment, CommentNumberLike commentNumberLike){
         Comment comment = commentRepository.findById(idComment).orElseThrow( ()->new AppException(ErrorCode.COMMENT_NOT_EXIST));
         comment.setNumberLike(commentNumberLike.getNumberLike());
         return commentRepository.save(comment);
   }
}
