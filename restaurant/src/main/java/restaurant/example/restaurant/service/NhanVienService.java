package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.NhanVienMapper;
import restaurant.example.restaurant.repository.NhanVienRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienService {
             NhanVienRepository nhanVienRepository;
             NhanVienMapper nhanVienMapper;
             public NhanVien createNhanVien (NhanVien nhanvien) {
                   NhanVien newNhanVien = nhanVienMapper.toNhanVien(nhanvien);
                   newNhanVien.setCccd(nhanvien.getCccd());
                    return nhanVienRepository.save(newNhanVien);
             }
             public NhanVien getSpecificNhanVien (String maNhanVien) {

                 return nhanVienRepository.findById(maNhanVien)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVien> getAllNhanVien (){
                  return nhanVienRepository.findAll();
             }

             public String updateNhanVien (String maNhanVien, NhanVien nhanVien) {
                  NhanVien nhanVienUpdate =getSpecificNhanVien(maNhanVien);
                  nhanVienMapper.updateNhanVien(nhanVienUpdate, nhanVien);
                  nhanVienRepository.save(nhanVienUpdate);
                  return "update success";

             }
             public String deleteNhanVien (String maNhanVien) {
                    nhanVienRepository.deleteById(maNhanVien);
                    return "delete success";
             }


}
