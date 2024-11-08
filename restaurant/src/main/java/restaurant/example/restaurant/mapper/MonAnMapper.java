package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.MonAn;

@Mapper(componentModel = "spring")
public interface MonAnMapper {
           @Mapping(target = "maMonAn", ignore = true)
          public MonAn toMonAn (MonAn chiNhanh);
          public void updateMonAn (@MappingTarget MonAn chiNhanh, MonAn chiNhanhUpdate);
}
