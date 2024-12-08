package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.entity.NhanVienGiaoHang;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.NhanVienGiaoHangMapper;
import restaurant.example.restaurant.repository.NhanVienGiaoHangRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienGiaoHangService {
             NhanVienGiaoHangRepository nhanVienGiaoHangRepository;
             NhanVienGiaoHangMapper nhanVienGiaoHangMapper;
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public NhanVienGiaoHang createNhanVienGiaoHang (NhanVienGiaoHang nhanviengiaohang) {
                   NhanVienGiaoHang newNhanVienGiaoHang = nhanVienGiaoHangMapper.toNhanVienGiaoHang(nhanviengiaohang);
                   newNhanVienGiaoHang.setMaNhanVienGiaoHang(nhanviengiaohang.getMaNhanVienGiaoHang());
                   if (nhanviengiaohang.getCccd() != null) {
                    NhanVien cccd = entityHelper.findOrMerge_OTO(
                            entityManager,
                            nhanviengiaohang.getCccd(),
                            NhanVien.class,
                            nhanviengiaohang.getCccd().getMaNhanVien(),
                            "NhanVien with ID "
                    );
                       newNhanVienGiaoHang.setCccd(cccd);
                    }
                    entityManager.persist(newNhanVienGiaoHang);
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
