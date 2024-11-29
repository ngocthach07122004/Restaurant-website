package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.DanhGia;

@Mapper(componentModel = "spring")
public interface DanhGiaMapper {
           @Mapping(target = "maDanhGia", ignore = true)
          public DanhGia toDanhGia (DanhGia danhGia);
          @Mapping(target = "maDanhGia", ignore = true)
          public void updateDanhGia (@MappingTarget DanhGia danhGia, DanhGia danhGiaUpdate);
}
