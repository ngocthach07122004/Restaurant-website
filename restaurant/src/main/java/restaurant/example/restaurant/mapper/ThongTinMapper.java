package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.ThongTin;

@Mapper(componentModel = "spring")
public interface ThongTinMapper {
        //    @Mapping(target = "cccd", ignore = true)
           @Mapping(target = "matKhau" , ignore = true)
          public ThongTin toThongTin (ThongTin thongTin);
        //   @Mapping(target = "cccd", ignore = true)

          public void updateThongTin (@MappingTarget ThongTin thongTin, ThongTin thongTinUpdate);
}
