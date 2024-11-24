package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.NhanVienGiaoHang;

@Mapper(componentModel = "spring")
public interface NhanVienGiaoHangMapper {
//           @Mapping(target = "cccd", ignore = true)
          public NhanVienGiaoHang toNhanVienGiaoHang (NhanVienGiaoHang nhanVienGiaoHang);
          @Mapping(target = "cccd", ignore = true)
          public void updateNhanVienGiaoHang (@MappingTarget NhanVienGiaoHang nhanVienGiaoHang, NhanVienGiaoHang nhanVienGiaoHangUpdate);
}
