package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.NhanVienPhucVu;

@Mapper(componentModel = "spring")
public interface NhanVienPhucVuMapper {
           @Mapping(target = "maNhanVienPhucVu", ignore = true)
          public NhanVienPhucVu toNhanVienPhucVu (NhanVienPhucVu nhanVienPhucVu);
          @Mapping(target = "maNhanVienPhucVu", ignore = true)
          public void updateNhanVienPhucVu (@MappingTarget NhanVienPhucVu nhanVienPhucVu, NhanVienPhucVu nhanVienPhucVuUpdate);
}
