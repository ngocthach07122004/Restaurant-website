package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVienThuNgan;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.NhanVienThuNganMapper;
import restaurant.example.restaurant.repository.NhanVienThuNganRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienThuNganService {
             NhanVienThuNganRepository nhanVienThuNganRepository;
             NhanVienThuNganMapper nhanVienThuNganMapper;
             public NhanVienThuNgan createNhanVienThuNgan (NhanVienThuNgan nhanvienthungan) {
                   NhanVienThuNgan newNhanVienThuNgan = nhanVienThuNganMapper.toNhanVienThuNgan(nhanvienthungan);
                   newNhanVienThuNgan.setCccd(nhanvienthungan.getCccd());
                    return nhanVienThuNganRepository.save(newNhanVienThuNgan);
             }
             public NhanVienThuNgan getSpecificNhanVienThuNgan (String maNhanVienThuNgan) {

                 return nhanVienThuNganRepository.findById(maNhanVienThuNgan)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVienThuNgan> getAllNhanVienThuNgan (){
                  return nhanVienThuNganRepository.findAll();
             }

             public String updateNhanVienThuNgan (String maNhanVienThuNgan, NhanVienThuNgan nhanVienThuNgan) {
                  NhanVienThuNgan nhanVienThuNganUpdate =getSpecificNhanVienThuNgan(maNhanVienThuNgan);
                  nhanVienThuNganMapper.updateNhanVienThuNgan(nhanVienThuNganUpdate, nhanVienThuNgan);
                  nhanVienThuNganRepository.save(nhanVienThuNganUpdate);
                  return "update success";

             }
             public String deleteNhanVienThuNgan (String maNhanVienThuNgan) {
                    nhanVienThuNganRepository.deleteById(maNhanVienThuNgan);
                    return "delete success";
             }


}
