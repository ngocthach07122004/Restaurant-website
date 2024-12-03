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
import restaurant.example.restaurant.mapper.MaKhuyenMaiMapper;
import restaurant.example.restaurant.repository.MaKhuyenMaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaKhuyenMaiService {
             MaKhuyenMaiRepository maKhuyenMaiRepository;
             MaKhuyenMaiMapper maKhuyenMaiMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public MaKhuyenMai createMaKhuyenMai (MaKhuyenMai maKhuyenMai) {
                   MaKhuyenMai newMaKhuyenMai = maKhuyenMaiMapper.toMaKhuyenMai(maKhuyenMai);
                   newMaKhuyenMai.setIdKhuyenMai(maKhuyenMai.getIdKhuyenMai());
        if (maKhuyenMai.getMaDon() != null) {
            DonMonAn donMonAn = entityHelper.findOrMerge_MTO(
                    entityManager,
                    maKhuyenMai.getMaDon(),
                    DonMonAn.class,
                    maKhuyenMai.getMaDon().getMaDon(),
                    "DonMonAn with ID "
            );
            newMaKhuyenMai.setMaDon(donMonAn);
        }
        if (maKhuyenMai.getMaChiNhanh() != null) {
            ChiNhanh chiNhanh = entityHelper.findOrMerge_MTO(
                    entityManager,
                    maKhuyenMai.getMaChiNhanh(),
                    ChiNhanh.class,
                    maKhuyenMai.getMaChiNhanh().getMaChiNhanh(),
                    "ChiNhanh with ID "
            );
            newMaKhuyenMai.setMaChiNhanh(chiNhanh);
        }
//        if (maKhuyenMai.getListMaKhuyenMaiKhachHang() != null) {
//            List<MaKhuyenMaiKhachHang> updatedMaKhuyenMaiKhachHangList = entityHelper.updateOrCreateRelatedEntities_OTM(
//                    newMaKhuyenMai.getListMaKhuyenMaiKhachHang(),
//                    MaKhuyenMaiKhachHang::getMaKhuyenMai, // Hàm lấy ID
//                    id -> entityManager.find(MaKhuyenMaiKhachHang.class, id), // Hàm tìm kiếm trong cơ sở dữ liệu
//                    maKhuyenMaiKhachHang -> maKhuyenMaiKhachHang.setMaKhuyenMai(newMaKhuyenMai), // Thiết lập quan hệ ngược
//                    maKhuyenMaiKhachHang -> entityManager.merge(maKhuyenMaiKhachHang) // Lưu entity mới hoặc cập nhật
//            );
//
//            // Gán lại danh sách đã xử lý
//            newMaKhuyenMai.setListMaKhuyenMaiKhachHang(updatedMaKhuyenMaiKhachHangList);
//        }
        if (maKhuyenMai.getListMaKhuyenMaiKhachHang() != null) {
            List<MaKhuyenMaiKhachHang> newListMaKhuyenMaiKhachHang = entityHelper.processNestedEntityList_MTMP(
                    entityManager,
                    maKhuyenMai.getListMaKhuyenMaiKhachHang(),
                    MaKhuyenMaiKhachHang.class,
                    MaKhuyenMaiKhachHang::getMaMaKhuyenMaiKhachHang,
                    MaKhuyenMaiKhachHang::getMaKhuyenMai,
                    MaKhuyenMaiKhachHang::setMaKhuyenMai,
                    MaKhuyenMai.class,
                    MaKhuyenMai::getIdKhuyenMai,
                    "MaKhuyenMaiKhachHang with ID ",
                    "MaKhuyenMai with ID "
            );
            newMaKhuyenMai.setListMaKhuyenMaiKhachHang(newListMaKhuyenMaiKhachHang);
        }

                    return maKhuyenMaiRepository.save(newMaKhuyenMai);
             }
             public MaKhuyenMai getSpecificMaKhuyenMai (String maMaKhuyenMai) {

                 return maKhuyenMaiRepository.findById(maMaKhuyenMai)
                         .orElseThrow(()-> new AppException(ErrorCode.MAKHUYENMAI_NOT_EXIST));

             }
             public List<MaKhuyenMai> getAllMaKhuyenMai (){
                  return maKhuyenMaiRepository.findAll();
             }

             public String updateMaKhuyenMai (String maMaKhuyenMai, MaKhuyenMai maKhuyenMai) {
                  MaKhuyenMai maKhuyenMaiUpdate =getSpecificMaKhuyenMai(maMaKhuyenMai);
                  maKhuyenMaiMapper.updateMaKhuyenMai(maKhuyenMaiUpdate, maKhuyenMai);
                  maKhuyenMaiRepository.save(maKhuyenMaiUpdate);
                  return "update success";

             }
             public String deleteMaKhuyenMai (String maMaKhuyenMai) {
                    maKhuyenMaiRepository.deleteById(maMaKhuyenMai);
                    return "delete success";
             }


}
