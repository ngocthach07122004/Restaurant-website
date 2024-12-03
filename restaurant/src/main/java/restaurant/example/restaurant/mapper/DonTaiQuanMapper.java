package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.entity.DonTaiQuan;

@Mapper(componentModel = "spring")
public interface DonTaiQuanMapper {
       @Mapping(target = "maDonTaiQuan", ignore = true)
          public DonTaiQuan toDonTaiQuan (DonTaiQuan donTaiQuan);
          @Mapping(target = "maDonTaiQuan", ignore = true)
          public void updateDonTaiQuan (@MappingTarget DonTaiQuan donTaiQuan, DonTaiQuan donTaiQuanUpdate);
}
