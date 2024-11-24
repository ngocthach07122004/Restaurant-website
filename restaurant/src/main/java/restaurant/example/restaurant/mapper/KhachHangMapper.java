package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.KhachHang;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {
//           @Mapping(target = "cccd", ignore = true)
          public KhachHang toKhachHang (KhachHang khachHang);
           @Mapping(target = "cccd", ignore = true)
          public void updateKhachHang (@MappingTarget KhachHang khachHang, KhachHang khachHangUpdate);
}
