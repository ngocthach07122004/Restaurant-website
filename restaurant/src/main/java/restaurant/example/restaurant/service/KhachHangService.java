package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.KhachHang;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.KhachHangMapper;
import restaurant.example.restaurant.repository.KhachHangRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangService {
             KhachHangRepository khachHangRepository;
             KhachHangMapper khachHangMapper;
             public KhachHang createKhachHang (KhachHang khachhang) {
                   KhachHang newKhachHang = khachHangMapper.toKhachHang(khachhang);
                   newKhachHang.setCccd(khachhang.getCccd());
                    return khachHangRepository.save(newKhachHang);
             }
             public KhachHang getSpecificKhachHang (String maKhachHang) {

                 return khachHangRepository.findById(maKhachHang)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<KhachHang> getAllKhachHang (){
                  return khachHangRepository.findAll();
             }

             public String updateKhachHang (String maKhachHang, KhachHang khachHang) {
                  KhachHang khachHangUpdate =getSpecificKhachHang(maKhachHang);
                  khachHangMapper.updateKhachHang(khachHangUpdate, khachHang);
                  khachHangRepository.save(khachHangUpdate);
                  return "update success";

             }
             public String deleteKhachHang (String maKhachHang) {
                    khachHangRepository.deleteById(maKhachHang);
                    return "delete success";
             }


}
