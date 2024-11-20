package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.ThongTin;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.ThongTinMapper;
import restaurant.example.restaurant.repository.ThongTinRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongTinService {
             ThongTinRepository thongTinRepository;
             ThongTinMapper thongTinMapper;
             public ThongTin createThongTin (ThongTin thongTin) {
                   ThongTin newThongTin = thongTinMapper.toThongTin(thongTin);
                   newThongTin.setCccd(thongTin.getCccd());
                    return thongTinRepository.save(newThongTin);
             }
             public ThongTin getSpecificThongTin (String maThongTin) {

                 return thongTinRepository.findById(maThongTin)
                         .orElseThrow(()-> new AppException(ErrorCode.THONGTIN_NOT_EXIST));

             }
             public List<ThongTin> getAllThongTin (){
                  return thongTinRepository.findAll();
             }

             public String updateThongTin (String maThongTin, ThongTin thongTin) {
                  ThongTin thongTinUpdate =getSpecificThongTin(maThongTin);
                  thongTinMapper.updateThongTin(thongTinUpdate, thongTin);
                  thongTinRepository.save(thongTinUpdate);
                  return "update success";

             }
             public String deleteThongTin (String maThongTin) {
                    thongTinRepository.deleteById(maThongTin);
                    return "delete success";
             }


}
