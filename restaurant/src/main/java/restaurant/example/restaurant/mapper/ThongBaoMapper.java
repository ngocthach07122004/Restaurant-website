package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.ThongBao;

@Mapper(componentModel = "spring")
public interface ThongBaoMapper {
           @Mapping(target = "maThongBao", ignore = true)
           @Mapping(target = "thoiGian", ignore =true)
          public ThongBao toThongBao (ThongBao thongBao);
          @Mapping(target = "maThongBao", ignore = true)
          public void updateThongBao (@MappingTarget ThongBao thongBao, ThongBao thongBaoUpdate);
}
