package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVienGiaoHang;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.NhanVienGiaoHangMapper;
import restaurant.example.restaurant.repository.NhanVienGiaoHangRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienGiaoHangService {
             NhanVienGiaoHangRepository nhanVienGiaoHangRepository;
             NhanVienGiaoHangMapper nhanVienGiaoHangMapper;
             public NhanVienGiaoHang createNhanVienGiaoHang (NhanVienGiaoHang nhanviengiaohang) {
                   NhanVienGiaoHang newNhanVienGiaoHang = nhanVienGiaoHangMapper.toNhanVienGiaoHang(nhanviengiaohang);
                   newNhanVienGiaoHang.setCccd(nhanviengiaohang.getCccd());
                    return nhanVienGiaoHangRepository.save(newNhanVienGiaoHang);
             }
             public NhanVienGiaoHang getSpecificNhanVienGiaoHang (String maNhanVienGiaoHang) {

                 return nhanVienGiaoHangRepository.findById(maNhanVienGiaoHang)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVienGiaoHang> getAllNhanVienGiaoHang (){
                  return nhanVienGiaoHangRepository.findAll();
             }

             public String updateNhanVienGiaoHang (String maNhanVienGiaoHang, NhanVienGiaoHang nhanVienGiaoHang) {
                  NhanVienGiaoHang nhanVienGiaoHangUpdate =getSpecificNhanVienGiaoHang(maNhanVienGiaoHang);
                  nhanVienGiaoHangMapper.updateNhanVienGiaoHang(nhanVienGiaoHangUpdate, nhanVienGiaoHang);
                  nhanVienGiaoHangRepository.save(nhanVienGiaoHangUpdate);
                  return "update success";

             }
             public String deleteNhanVienGiaoHang (String maNhanVienGiaoHang) {
                    nhanVienGiaoHangRepository.deleteById(maNhanVienGiaoHang);
                    return "delete success";
             }


}
