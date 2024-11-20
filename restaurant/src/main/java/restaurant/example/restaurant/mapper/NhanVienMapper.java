package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.NhanVien;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {
//           @Mapping(target = "cccd", ignore = true)
          public NhanVien toNhanVien (NhanVien nhanVien);
          @Mapping(target = "cccd", ignore = true)
          public void updateNhanVien (@MappingTarget NhanVien nhanVien, NhanVien nhanVienUpdate);
}
