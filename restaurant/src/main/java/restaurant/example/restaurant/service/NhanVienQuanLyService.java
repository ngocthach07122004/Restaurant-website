package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVienQuanLy;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.NhanVienQuanLyMapper;
import restaurant.example.restaurant.repository.NhanVienQuanLyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienQuanLyService {
             NhanVienQuanLyRepository nhanVienQuanLyRepository;
             NhanVienQuanLyMapper nhanVienQuanLyMapper;
             public NhanVienQuanLy createNhanVienQuanLy (NhanVienQuanLy nhanvienquanly) {
                   NhanVienQuanLy newNhanVienQuanLy = nhanVienQuanLyMapper.toNhanVienQuanLy(nhanvienquanly);
                   newNhanVienQuanLy.setMaNhanVienQuanLy(nhanvienquanly.getMaNhanVienQuanLy());
                    return nhanVienQuanLyRepository.save(newNhanVienQuanLy);
             }
             public NhanVienQuanLy getSpecificNhanVienQuanLy (String maNhanVienQuanLy) {

                 return nhanVienQuanLyRepository.findById(maNhanVienQuanLy)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVienQuanLy> getAllNhanVienQuanLy (){
                  return nhanVienQuanLyRepository.findAll();
             }

             public String updateNhanVienQuanLy (String maNhanVienQuanLy, NhanVienQuanLy nhanVienQuanLy) {
                  NhanVienQuanLy nhanVienQuanLyUpdate =getSpecificNhanVienQuanLy(maNhanVienQuanLy);
                  nhanVienQuanLyMapper.updateNhanVienQuanLy(nhanVienQuanLyUpdate, nhanVienQuanLy);
                  nhanVienQuanLyRepository.save(nhanVienQuanLyUpdate);
                  return "update success";

             }
             public String deleteNhanVienQuanLy (String maNhanVienQuanLy) {
                    nhanVienQuanLyRepository.deleteById(maNhanVienQuanLy);
                    return "delete success";
             }


}
