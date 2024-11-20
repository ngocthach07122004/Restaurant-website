package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.NhanVienThuNgan;

@Mapper(componentModel = "spring")
public interface NhanVienThuNganMapper {
//           @Mapping(target = "cccd", ignore = true)
          public NhanVienThuNgan toNhanVienThuNgan (NhanVienThuNgan nhanVienThuNgan);
          @Mapping(target = "cccd", ignore = true)
          public void updateNhanVienThuNgan (@MappingTarget NhanVienThuNgan nhanVienThuNgan, NhanVienThuNgan nhanVienThuNganUpdate);
}
