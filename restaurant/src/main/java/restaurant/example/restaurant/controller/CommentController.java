package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.dto.request.CommentNumberLike;
import restaurant.example.restaurant.entity.Comment;
import restaurant.example.restaurant.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
      CommentService commentService;
      @GetMapping("/all/{idMonAn}")
       public List<Comment> getAllCommentById(@PathVariable String idMonAn){
            return commentService.getAllCommentByIdMonAn(idMonAn);

      }
      @PostMapping("/create")
      public  Comment createComment (@RequestBody Comment comment){
            return commentService.createComment(comment);
      }
      @DeleteMapping("/delete/{idComment}")
      public String deleteComment(@PathVariable String idComment){
             return commentService.deleteComment(idComment);
      }
      @PutMapping("/update/{idComment}")
     public Comment updateNumberLike (@PathVariable String idComment , @RequestBody CommentNumberLike commentNumberLike){
          return commentService.updateNumberLike(idComment,commentNumberLike);
      }
}
