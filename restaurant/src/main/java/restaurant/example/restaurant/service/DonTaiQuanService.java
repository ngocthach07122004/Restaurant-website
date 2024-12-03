package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonMonAn;
import restaurant.example.restaurant.entity.DonTaiQuan;
import restaurant.example.restaurant.entity.NhanVienPhucVu;
import restaurant.example.restaurant.entity.NhanVienThuNgan;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.DonTaiQuanMapper;
import restaurant.example.restaurant.repository.DonTaiQuanRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonTaiQuanService {
             DonTaiQuanRepository donTaiQuanRepository;
             DonTaiQuanMapper donTaiQuanMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public DonTaiQuan createDonTaiQuan (DonTaiQuan dontaiquan) {
                   DonTaiQuan newDonTaiQuan = donTaiQuanMapper.toDonTaiQuan(dontaiquan);
                   newDonTaiQuan.setMaDonTaiQuan(dontaiquan.getMaDonTaiQuan());
                   if (dontaiquan.getMaDon() != null) {
                    DonMonAn maDon = entityHelper.findOrMerge_OTO(
                            entityManager,
                            dontaiquan.getMaDon(),
                            DonMonAn.class,
                            dontaiquan.getMaDon().getMaDon(),
                            "DonMonAn with ID "
                    );
                       newDonTaiQuan.setMaDon(maDon); // Thiết lập mối quan hệ One-to-One
                }
        if (dontaiquan.getMaNhanVienPhucVu() != null) {
            NhanVienPhucVu maNhanVienPhucVu = entityHelper.findOrMerge_MTO(
                    entityManager,
                    dontaiquan.getMaNhanVienPhucVu(),
                    NhanVienPhucVu.class,
                    dontaiquan.getMaNhanVienPhucVu().getMaNhanVienPhucVu(),
                    "NhanVienPhucVu with ID "
            );
            dontaiquan.setMaNhanVienPhucVu(maNhanVienPhucVu);
        }


                    
                    return donTaiQuanRepository.save(newDonTaiQuan);
             }
             public DonTaiQuan getSpecificDonTaiQuan (String maDonTaiQuan) {

                 return donTaiQuanRepository.findById(maDonTaiQuan)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<DonTaiQuan> getAllDonTaiQuan (){
                  return donTaiQuanRepository.findAll();
             }

             public String updateDonTaiQuan (String maDonTaiQuan, DonTaiQuan donTaiQuan) {
                  DonTaiQuan donTaiQuanUpdate =getSpecificDonTaiQuan(maDonTaiQuan);
                  donTaiQuanMapper.updateDonTaiQuan(donTaiQuanUpdate, donTaiQuan);
                  donTaiQuanRepository.save(donTaiQuanUpdate);
                  return "update success";

             }
             public String deleteDonTaiQuan (String maDonTaiQuan) {
                    donTaiQuanRepository.deleteById(maDonTaiQuan);
                    return "delete success";
             }


}
