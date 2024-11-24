package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.NhanVienTongDai;

@Mapper(componentModel = "spring")
public interface NhanVienTongDaiMapper {
//           @Mapping(target = "cccd", ignore = true)
          public NhanVienTongDai toNhanVienTongDai (NhanVienTongDai nhanVienTongDai);
          @Mapping(target = "cccd", ignore = true)
          public void updateNhanVienTongDai (@MappingTarget NhanVienTongDai nhanVienTongDai, NhanVienTongDai nhanVienTongDaiUpdate);
}
