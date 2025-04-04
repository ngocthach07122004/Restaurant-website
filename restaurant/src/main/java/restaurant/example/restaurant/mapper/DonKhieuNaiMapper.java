package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.DonKhieuNai;

@Mapper(componentModel = "spring")
public interface DonKhieuNaiMapper {
           @Mapping(target = "maDonKhieuNai", ignore = true)
          public DonKhieuNai toDonKhieuNai (DonKhieuNai donKhieuNai);
          @Mapping(target = "maDonKhieuNai", ignore = true)
          public void updateDonKhieuNai (@MappingTarget DonKhieuNai donKhieuNai, DonKhieuNai donKhieuNaiUpdate);
}
