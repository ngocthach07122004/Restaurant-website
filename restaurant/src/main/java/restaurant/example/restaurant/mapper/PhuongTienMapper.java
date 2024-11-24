package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.PhuongTien;

@Mapper(componentModel = "spring")
public interface PhuongTienMapper {
           @Mapping(target = "bienSoXe", ignore = true)
          public PhuongTien toPhuongTien (PhuongTien phuongTien);
          @Mapping(target = "bienSoXe", ignore = true)
          public void updatePhuongTien (@MappingTarget PhuongTien phuongTien, PhuongTien phuongTienUpdate);
}
