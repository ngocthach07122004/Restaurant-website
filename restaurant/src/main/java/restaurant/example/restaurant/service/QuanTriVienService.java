package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.QuanTriVien;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.QuanTriVienMapper;
import restaurant.example.restaurant.repository.QuanTriVienRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuanTriVienService {
             QuanTriVienRepository quanTriVienRepository;
             QuanTriVienMapper quanTriVienMapper;
             public QuanTriVien createQuanTriVien (QuanTriVien quantrivien) {
                   QuanTriVien newQuanTriVien = quanTriVienMapper.toQuanTriVien(quantrivien);
                   newQuanTriVien.setCccd(quantrivien.getCccd());
                    return quanTriVienRepository.save(newQuanTriVien);
             }
             public QuanTriVien getSpecificQuanTriVien (String maQuanTriVien) {

                 return quanTriVienRepository.findById(maQuanTriVien)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<QuanTriVien> getAllQuanTriVien (){
                  return quanTriVienRepository.findAll();
             }

             public String updateQuanTriVien (String maQuanTriVien, QuanTriVien quanTriVien) {
                  QuanTriVien quanTriVienUpdate =getSpecificQuanTriVien(maQuanTriVien);
                  quanTriVienMapper.updateQuanTriVien(quanTriVienUpdate, quanTriVien);
                  quanTriVienRepository.save(quanTriVienUpdate);
                  return "update success";

             }
             public String deleteQuanTriVien (String maQuanTriVien) {
                    quanTriVienRepository.deleteById(maQuanTriVien);
                    return "delete success";
             }


}
