package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DanhGia;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DanhGiaMapper;
import restaurant.example.restaurant.repository.DanhGiaRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DanhGiaService {
             DanhGiaRepository danhGiaRepository;
             DanhGiaMapper danhGiaMapper;
             public DanhGia createDanhGia (DanhGia danhGia) {
                   DanhGia newDanhGia = danhGiaMapper.toDanhGia(danhGia);
                   newDanhGia.setMaDanhGia(danhGia.getMaDanhGia());
                    return danhGiaRepository.save(newDanhGia);
             }
             public DanhGia getSpecificDanhGia (String maDanhGia) {

                 return danhGiaRepository.findById(maDanhGia)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<DanhGia> getAllDanhGia (){
                  return danhGiaRepository.findAll();
             }

             public String updateDanhGia (String maDanhGia, DanhGia danhGia) {
                  DanhGia danhGiaUpdate =getSpecificDanhGia(maDanhGia);
                  danhGiaMapper.updateDanhGia(danhGiaUpdate, danhGia);
                   danhGiaRepository.save(danhGiaUpdate);
                  return "update success";

             }
             public String deleteDanhGia (String maDanhGia) {
                    danhGiaRepository.deleteById(maDanhGia);
                    return "delete success";
             }


}
