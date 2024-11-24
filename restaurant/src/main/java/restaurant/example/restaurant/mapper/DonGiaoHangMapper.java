package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.DonGiaoHang;

@Mapper(componentModel = "spring")
public interface DonGiaoHangMapper {
//           @Mapping(target = "maDon", ignore = true)
          public DonGiaoHang toDonGiaoHang (DonGiaoHang donGiaoHang);
    @Mapping(target = "maDon", ignore = true)
          public void updateDonGiaoHang (@MappingTarget DonGiaoHang donGiaoHang, DonGiaoHang donGiaoHangUpdate);
}
