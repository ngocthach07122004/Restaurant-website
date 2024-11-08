package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.MaKhuyenMai;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.MaKhuyenMaiMapper;
import restaurant.example.restaurant.repository.MaKhuyenMaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaKhuyenMaiService {
             MaKhuyenMaiRepository maKhuyenMaiRepository;
             MaKhuyenMaiMapper maKhuyenMaiMapper;
             public MaKhuyenMai createMaKhuyenMai (MaKhuyenMai maKhuyenMai) {
                   MaKhuyenMai newMaKhuyenMai = maKhuyenMaiMapper.toMaKhuyenMai(maKhuyenMai);
                   newMaKhuyenMai.setIdKhuyenMai(maKhuyenMai.getIdKhuyenMai());
                    return maKhuyenMaiRepository.save(newMaKhuyenMai);
             }
             public MaKhuyenMai getSpecificMaKhuyenMai (String maMaKhuyenMai) {

                 return maKhuyenMaiRepository.findById(maMaKhuyenMai)
                         .orElseThrow(()-> new AppException(ErrorCode.MAKHUYENMAI_NOT_EXIST));

             }
             public List<MaKhuyenMai> getAllMaKhuyenMai (){
                  return maKhuyenMaiRepository.findAll();
             }

             public String updateMaKhuyenMai (String maMaKhuyenMai, MaKhuyenMai maKhuyenMai) {
                  MaKhuyenMai maKhuyenMaiUpdate =getSpecificMaKhuyenMai(maMaKhuyenMai);
                  maKhuyenMaiMapper.updateMaKhuyenMai(maKhuyenMaiUpdate, maKhuyenMai);
                  return "update success";

             }
             public String deleteMaKhuyenMai (String maMaKhuyenMai) {
                    maKhuyenMaiRepository.deleteById(maMaKhuyenMai);
                    return "delete success";
             }


}
