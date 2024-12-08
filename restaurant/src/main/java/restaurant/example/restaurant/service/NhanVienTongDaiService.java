package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.entity.NhanVienTongDai;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.NhanVienTongDaiMapper;
import restaurant.example.restaurant.repository.NhanVienTongDaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienTongDaiService {
             NhanVienTongDaiRepository nhanVienTongDaiRepository;
             NhanVienTongDaiMapper nhanVienTongDaiMapper;
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public NhanVienTongDai createNhanVienTongDai (NhanVienTongDai nhanvientongdai) {
                   NhanVienTongDai newNhanVienTongDai = nhanVienTongDaiMapper.toNhanVienTongDai(nhanvientongdai);
                   newNhanVienTongDai.setCccd(nhanvientongdai.getCccd());
        if (nhanvientongdai.getCccd() != null) {
            NhanVien cccd = entityHelper.findOrMerge_OTO(
                    entityManager,
                    nhanvientongdai.getCccd(),
                    NhanVien.class,
                    nhanvientongdai.getCccd().getMaNhanVien(),
                    "NhanVien with ID "
            );
            newNhanVienTongDai.setCccd(cccd);
        }
        entityManager.persist(newNhanVienTongDai);
                    return nhanVienTongDaiRepository.save(newNhanVienTongDai);
             }
             public NhanVienTongDai getSpecificNhanVienTongDai (String maNhanVienTongDai) {

                 return nhanVienTongDaiRepository.findById(maNhanVienTongDai)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVienTongDai> getAllNhanVienTongDai (){
                  return nhanVienTongDaiRepository.findAll();
             }

             public String updateNhanVienTongDai (String maNhanVienTongDai, NhanVienTongDai nhanVienTongDai) {
                  NhanVienTongDai nhanVienTongDaiUpdate =getSpecificNhanVienTongDai(maNhanVienTongDai);
                  nhanVienTongDaiMapper.updateNhanVienTongDai(nhanVienTongDaiUpdate, nhanVienTongDai);
                  nhanVienTongDaiRepository.save(nhanVienTongDaiUpdate );
                  return "update success";

             }
             public String deleteNhanVienTongDai (String maNhanVienTongDai) {
                    nhanVienTongDaiRepository.deleteById(maNhanVienTongDai);
                    return "delete success";
             }


}
