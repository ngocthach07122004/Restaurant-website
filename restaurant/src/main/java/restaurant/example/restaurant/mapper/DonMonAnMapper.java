package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.DonMonAn;

@Mapper(componentModel = "spring")
public interface DonMonAnMapper {
           @Mapping(target = "maDon", ignore = true)
          public DonMonAn toDonMonAn (DonMonAn chiNhanh);
          @Mapping(target = "maDon", ignore = true)
          public void updateDonMonAn (@MappingTarget DonMonAn chiNhanh, DonMonAn chiNhanhUpdate);
}
