package restaurant.example.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import restaurant.example.restaurant.dto.response.MonAnThuocDonMonAnResponse;
import restaurant.example.restaurant.entity.DonMonAn;

@Mapper(componentModel = "spring")
public interface DonMonAnMapper {
           @Mapping(target = "maDon", ignore = true)
           @Mapping(target = "listBaoGom", ignore = true)
          public DonMonAn toDonMonAn (DonMonAn donMonAn);
          @Mapping(target = "maDon", ignore = true)
          public void updateDonMonAn (@MappingTarget DonMonAn donMonAn, DonMonAn donMonAnUpdate);
         @Mapping(target = "soLuongMonAn", ignore = true)
          public MonAnThuocDonMonAnResponse toMonAnThuocDonMonAnResponse (DonMonAn donMonAn );
}
