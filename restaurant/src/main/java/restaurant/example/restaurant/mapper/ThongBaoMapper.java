package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.ThongBao;

@Mapper(componentModel = "spring")
public interface ThongBaoMapper {
           @Mapping(target = "maThongBao", ignore = true)
          public ThongBao toThongBao (ThongBao chiNhanh);
          public void updateThongBao (@MappingTarget ThongBao chiNhanh, ThongBao chiNhanhUpdate);
}
