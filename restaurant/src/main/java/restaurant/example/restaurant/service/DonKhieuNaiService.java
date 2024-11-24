package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonKhieuNai;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DonKhieuNaiMapper;
import restaurant.example.restaurant.repository.DonKhieuNaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonKhieuNaiService {
             DonKhieuNaiRepository donKhieuNaiRepository;
             DonKhieuNaiMapper donKhieuNaiMapper;
             public DonKhieuNai createDonKhieuNai (DonKhieuNai donKhieuNai) {
                   DonKhieuNai newDonKhieuNai = donKhieuNaiMapper.toDonKhieuNai(donKhieuNai);
                   newDonKhieuNai.setMaDonKhieuNai(donKhieuNai.getMaDonKhieuNai());
                    return donKhieuNaiRepository.save(newDonKhieuNai);
             }
             public DonKhieuNai getSpecificDonKhieuNai (String maDonKhieuNai) {

                 return donKhieuNaiRepository.findById(maDonKhieuNai)
                         .orElseThrow(()-> new AppException(ErrorCode.DONKHIEUNAI_NOT_EXIST));

             }
             public List<DonKhieuNai> getAllDonKhieuNai (){
                  return donKhieuNaiRepository.findAll();
             }

             public String updateDonKhieuNai (String maDonKhieuNai, DonKhieuNai donKhieuNai) {
                  DonKhieuNai donKhieuNaiUpdate =getSpecificDonKhieuNai(maDonKhieuNai);
                  donKhieuNaiMapper.updateDonKhieuNai(donKhieuNaiUpdate, donKhieuNai);
                  donKhieuNaiRepository.save(donKhieuNaiUpdate);
                  return "update success";

             }
             public String deleteDonKhieuNai (String maDonKhieuNai) {
                    donKhieuNaiRepository.deleteById(maDonKhieuNai);
                    return "delete success";
             }


}
