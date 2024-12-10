package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.entity.NhanVienQuanLy;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.NhanVienQuanLyMapper;
import restaurant.example.restaurant.repository.NhanVienQuanLyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienQuanLyService {
             NhanVienQuanLyRepository nhanVienQuanLyRepository;
             NhanVienQuanLyMapper nhanVienQuanLyMapper;
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public NhanVienQuanLy createNhanVienQuanLy (NhanVienQuanLy nhanvienquanly) {
                   NhanVienQuanLy newNhanVienQuanLy = nhanVienQuanLyMapper.toNhanVienQuanLy(nhanvienquanly);
                   newNhanVienQuanLy.setMaNhanVienQuanLy(nhanvienquanly.getMaNhanVienQuanLy());
                   if (nhanvienquanly.getCccd() != null) {
                    NhanVien cccd = entityHelper.findOrMerge_OTO(
                            entityManager,
                            nhanvienquanly.getCccd(),
                            NhanVien.class,
                            nhanvienquanly.getCccd().getMaNhanVien(),
                            "NhanVien with ID "
                    );
                       newNhanVienQuanLy.setCccd(cccd);
                    }
                    entityManager.persist(newNhanVienQuanLy);


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
