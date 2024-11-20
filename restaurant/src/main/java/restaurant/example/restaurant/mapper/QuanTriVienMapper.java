package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.QuanTriVien;

@Mapper(componentModel = "spring")
public interface QuanTriVienMapper {
//           @Mapping(target = "cccd", ignore = true)
          public QuanTriVien toQuanTriVien (QuanTriVien quanTriVien);
          @Mapping(target = "cccd", ignore = true)
          public void updateQuanTriVien (@MappingTarget QuanTriVien quanTriVien, QuanTriVien quanTriVienUpdate);
}
