package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.entity.NhanVienPhucVu;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.NhanVienPhucVuMapper;
import restaurant.example.restaurant.repository.NhanVienPhucVuRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienPhucVuService {
             NhanVienPhucVuRepository nhanVienPhucVuRepository;
             NhanVienPhucVuMapper nhanVienPhucVuMapper;
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public NhanVienPhucVu createNhanVienPhucVu (NhanVienPhucVu nhanvienphucvu) {
                   NhanVienPhucVu newNhanVienPhucVu = nhanVienPhucVuMapper.toNhanVienPhucVu(nhanvienphucvu);
                   newNhanVienPhucVu.setMaNhanVienPhucVu(nhanvienphucvu.getMaNhanVienPhucVu());
        if (nhanvienphucvu.getCccd() != null) {
            NhanVien cccd = entityHelper.findOrMerge_OTO(
                    entityManager,
                    nhanvienphucvu.getCccd(),
                    NhanVien.class,
                    nhanvienphucvu.getCccd().getMaNhanVien(),
                    "NhanVien with ID "
            );
            newNhanVienPhucVu.setCccd(cccd);
        }
        entityManager.persist(newNhanVienPhucVu);
                    return nhanVienPhucVuRepository.save(newNhanVienPhucVu);
             }
             public NhanVienPhucVu getSpecificNhanVienPhucVu (String maNhanVienPhucVu) {

                 return nhanVienPhucVuRepository.findById(maNhanVienPhucVu)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVienPhucVu> getAllNhanVienPhucVu (){
                  return nhanVienPhucVuRepository.findAll();
             }

             public String updateNhanVienPhucVu (String maNhanVienPhucVu, NhanVienPhucVu nhanVienPhucVu) {
                  NhanVienPhucVu nhanVienPhucVuUpdate =getSpecificNhanVienPhucVu(maNhanVienPhucVu);
                  nhanVienPhucVuMapper.updateNhanVienPhucVu(nhanVienPhucVuUpdate, nhanVienPhucVu);
                  nhanVienPhucVuRepository.save(nhanVienPhucVuUpdate);
                  return "update success";

             }
             public String deleteNhanVienPhucVu (String maNhanVienPhucVu) {
                    nhanVienPhucVuRepository.deleteById(maNhanVienPhucVu);
                    return "delete success";
             }


}
