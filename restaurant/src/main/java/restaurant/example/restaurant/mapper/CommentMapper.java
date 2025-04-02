package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "idComment", ignore = true)
     Comment toComment ( Comment comment);
    @Mapping(target = "idComment", ignore = true)
     void updateComment ( @MappingTarget Comment commentTarget, Comment  comment);
}
