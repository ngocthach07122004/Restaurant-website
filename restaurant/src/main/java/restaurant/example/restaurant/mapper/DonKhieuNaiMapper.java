package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.DonKhieuNai;

@Mapper(componentModel = "spring")
public interface DonKhieuNaiMapper {
           @Mapping(target = "maDonKhieuNai", ignore = true)
          public DonKhieuNai toDonKhieuNai (DonKhieuNai chiNhanh);
          public void updateDonKhieuNai (@MappingTarget DonKhieuNai chiNhanh, DonKhieuNai chiNhanhUpdate);
}
