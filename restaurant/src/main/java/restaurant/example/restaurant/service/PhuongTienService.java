package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.PhuongTien;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.PhuongTienMapper;
import restaurant.example.restaurant.repository.PhuongTienRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhuongTienService {
             PhuongTienRepository phuongTienRepository;
             PhuongTienMapper phuongTienMapper;
             public PhuongTien createPhuongTien (PhuongTien phuongTien) {
                   PhuongTien newPhuongTien = phuongTienMapper.toPhuongTien(phuongTien);
                   newPhuongTien.setBienSoXe(phuongTien.getBienSoXe());
                    return phuongTienRepository.save(newPhuongTien);
             }
             public PhuongTien getSpecificPhuongTien (String maPhuongTien) {

                 return phuongTienRepository.findById(maPhuongTien)
                         .orElseThrow(()-> new AppException(ErrorCode.PHUONGTIEN_NOT_EXIST));

             }
             public List<PhuongTien> getAllPhuongTien (){
                  return phuongTienRepository.findAll();
             }

             public String updatePhuongTien (String maPhuongTien, PhuongTien phuongTien) {
                  PhuongTien phuongTienUpdate =getSpecificPhuongTien(maPhuongTien);
                  phuongTienMapper.updatePhuongTien(phuongTienUpdate, phuongTien);
                  phuongTienRepository.save(phuongTienUpdate);
                  return "update success";

             }
             public String deletePhuongTien (String maPhuongTien) {
                    phuongTienRepository.deleteById(maPhuongTien);
                    return "delete success";
             }


}
