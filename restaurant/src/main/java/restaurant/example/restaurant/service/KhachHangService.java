package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.*;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.KhachHangMapper;
import restaurant.example.restaurant.repository.KhachHangRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangService {
             KhachHangRepository khachHangRepository;
             KhachHangMapper khachHangMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public KhachHang createKhachHang (KhachHang khachhang) {
                   KhachHang newKhachHang = khachHangMapper.toKhachHang(khachhang);
                   newKhachHang.setMaKhachHang(khachhang.getMaKhachHang());

                    // ONE TO ONE
        if (khachhang.getCccd() != null) {
            ThongTin cccd = entityHelper.findOrMerge_OTO(
                    entityManager,
                    khachhang.getCccd(),
                    ThongTin.class,
                    khachhang.getCccd().getCccd(),
                    "ThongTin with ID "
            );
            newKhachHang.setCccd(cccd); // Thiết lập mối quan hệ One-to-One
        }
        entityManager.persist(newKhachHang);

        if (khachhang.getListDonMonAn() != null) {
                     List<DonMonAn> updatedDonMonAnList = entityHelper.updateOrCreateRelatedEntities_OTM(
                             newKhachHang.getListDonMonAn(),
                             DonMonAn::getMaDon, // Hàm lấy ID
                             id -> entityManager.find(DonMonAn.class, id), // Hàm tìm kiếm trong cơ sở dữ liệu
                             donMonAn -> donMonAn.setCccdKhachHang(newKhachHang), // Thiết lập quan hệ ngược
                             donMonAn -> entityManager.merge(donMonAn) // Lưu entity mới hoặc cập nhật
                     );

                     // Gán lại danh sách đã xử lý
                     newKhachHang.setListDonMonAn(updatedDonMonAnList);
                 }
                 if (khachhang.getListDonKhieuNai() != null) {
                    List<DonKhieuNai> updatedDonKhieuNaiList = entityHelper.updateOrCreateRelatedEntities_OTM(
                            newKhachHang.getListDonKhieuNai(),
                            DonKhieuNai::getMaDonKhieuNai, // Hàm lấy ID
                            id -> entityManager.find(DonKhieuNai.class, id), // Hàm tìm kiếm trong cơ sở dữ liệu
                            donKhieuNai -> donKhieuNai.setCccdKhachHang(newKhachHang), // Thiết lập quan hệ ngược
                            donKhieuNai -> entityManager.merge(donKhieuNai) // Lưu entity mới hoặc cập nhật
                    );

                    // Gán lại danh sách đã xử lý
                    newKhachHang.setListDonKhieuNai(updatedDonKhieuNaiList);
                }
        if (khachhang.getListMaKhuyenMaiKhachHang() != null) {
            List<MaKhuyenMaiKhachHang> newListMaKhuyenMaiKhachHang = entityHelper.processNestedEntityList_MTMP(
                    entityManager,
                    khachhang.getListMaKhuyenMaiKhachHang(),
                    MaKhuyenMaiKhachHang.class,
                    MaKhuyenMaiKhachHang::getMaMaKhuyenMaiKhachHang,
                    MaKhuyenMaiKhachHang::getMaKhuyenMai,
                    MaKhuyenMaiKhachHang::setMaKhuyenMai,
                    MaKhuyenMai.class,
                    MaKhuyenMai::getIdKhuyenMai,
                    "MaKhuyenMaiKhachHang with ID ",
                    "MaKhuyenMai with ID "
            );
            newKhachHang.setListMaKhuyenMaiKhachHang(newListMaKhuyenMaiKhachHang);
        }
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
