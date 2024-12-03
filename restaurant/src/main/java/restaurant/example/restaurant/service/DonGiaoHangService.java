package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonGiaoHang;
import restaurant.example.restaurant.entity.DonMonAn;
import restaurant.example.restaurant.entity.ThongTin;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.DonGiaoHangMapper;
import restaurant.example.restaurant.repository.DonGiaoHangRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonGiaoHangService {
             DonGiaoHangRepository donGiaoHangRepository;
             DonGiaoHangMapper donGiaoHangMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public DonGiaoHang createDonGiaoHang (DonGiaoHang dongiaohang) {
                   DonGiaoHang newDonGiaoHang = donGiaoHangMapper.toDonGiaoHang(dongiaohang);
                   newDonGiaoHang.setMaDonGiaoHang(dongiaohang.getMaDonGiaoHang());
                 if (dongiaohang.getMaDon() != null) {
                     DonMonAn maDon = entityHelper.findOrMerge_OTO(
                             entityManager,
                             dongiaohang.getMaDon(),
                             DonMonAn.class,
                             dongiaohang.getMaDon().getMaDon(),
                             "DonMonAn with ID "
                     );
                     newDonGiaoHang.setMaDon(maDon); // Thiết lập mối quan hệ One-to-One
                 }
                    return donGiaoHangRepository.save(newDonGiaoHang);
             }
             public DonGiaoHang getSpecificDonGiaoHang (String maDonGiaoHang) {

                 return donGiaoHangRepository.findById(maDonGiaoHang)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<DonGiaoHang> getAllDonGiaoHang (){
                  return donGiaoHangRepository.findAll();
             }

             public String updateDonGiaoHang (String maDonGiaoHang, DonGiaoHang donGiaoHang) {
                  DonGiaoHang donGiaoHangUpdate =getSpecificDonGiaoHang(maDonGiaoHang);
                  donGiaoHangMapper.updateDonGiaoHang(donGiaoHangUpdate, donGiaoHang);
                  donGiaoHangRepository.save(donGiaoHangUpdate);
                  return "update success";

             }
             public String deleteDonGiaoHang (String maDonGiaoHang) {
                    donGiaoHangRepository.deleteById(maDonGiaoHang);
                    return "delete success";
             }


}
