package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.NhanVienQuanLy;

@Mapper(componentModel = "spring")
public interface NhanVienQuanLyMapper {
//           @Mapping(target = "cccd", ignore = true)
          public NhanVienQuanLy toNhanVienQuanLy (NhanVienQuanLy nhanVienQuanLy);
          @Mapping(target = "cccd", ignore = true)
          public void updateNhanVienQuanLy (@MappingTarget NhanVienQuanLy nhanVienQuanLy, NhanVienQuanLy nhanVienQuanLyUpdate);
}
