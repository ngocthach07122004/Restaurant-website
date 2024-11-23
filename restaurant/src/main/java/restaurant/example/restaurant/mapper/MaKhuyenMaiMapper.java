package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.MaKhuyenMai;

@Mapper(componentModel = "spring")
public interface MaKhuyenMaiMapper {
           @Mapping(target = "idKhuyenMai", ignore = true)
          public MaKhuyenMai toMaKhuyenMai (MaKhuyenMai maKhuyenMai);
          @Mapping(target = "idKhuyenMai", ignore = true)
          public void updateMaKhuyenMai (@MappingTarget MaKhuyenMai maKhuyenMai, MaKhuyenMai maKhuyenMaiUpdate);
}
