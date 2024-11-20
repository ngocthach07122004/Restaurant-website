package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.SuKienUuDai;

@Mapper(componentModel = "spring")
public interface SuKienUuDaiMapper {
           @Mapping(target = "maUuDai", ignore = true)
          public SuKienUuDai toSuKienUuDai (SuKienUuDai chiNhanh);
          @Mapping(target = "maUuDai", ignore = true)
          public void updateSuKienUuDai (@MappingTarget SuKienUuDai chiNhanh, SuKienUuDai chiNhanhUpdate);
}
