package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.ChiNhanh;

@Mapper(componentModel = "spring")
public interface ChiNhanhMapper {
           @Mapping(target = "maChiNhanh", ignore = true)
          public ChiNhanh toChiNhanh (ChiNhanh chiNhanh);
          public void updateChiNhanh (@MappingTarget ChiNhanh chiNhanh, ChiNhanh chiNhanhUpdate);
}
