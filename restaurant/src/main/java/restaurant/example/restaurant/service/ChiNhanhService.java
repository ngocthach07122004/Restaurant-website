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
import restaurant.example.restaurant.mapper.ChiNhanhMapper;
import restaurant.example.restaurant.repository.ChiNhanhRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChiNhanhService {
             ChiNhanhRepository chiNhanhRepository;
             ChiNhanhMapper chiNhanhMapper;
             @PersistenceContext
             EntityManager entityManager;
         
             EntityHelper entityHelper;
         
             @Transactional
             public ChiNhanh createChiNhanh (ChiNhanh chiNhanh) {
                   ChiNhanh newChiNhanh = chiNhanhMapper.toChiNhanh(chiNhanh);
                   newChiNhanh.setMaChiNhanh(chiNhanh.getMaChiNhanh());

                 if (chiNhanh.getListNhanVienQuanLy() != null) {
                     List<NhanVienQuanLy> updatedNhanVienQuanLyList = entityHelper.updateOrCreateRelatedEntities_OTM(
                             chiNhanh.getListNhanVienQuanLy(),
                             NhanVienQuanLy::getCccd,
                             id -> entityManager.find(NhanVienQuanLy.class, id),
                             nhanVienQuanLy -> nhanVienQuanLy.setChiNhanh(newChiNhanh), // Thiết lập quan hệ ngược
                             nhanVienQuanLy -> entityManager.merge(nhanVienQuanLy) // Lưu entity mới hoặc cập nhật
                     );

                     // Gán lại danh sách đã xử lý
                     newChiNhanh.setListNhanVienQuanLy(updatedNhanVienQuanLyList);
                 }



                   if (chiNhanh.getMaChiNhanh() != null) {
                    QuanTriVien quanTriVien = entityHelper.findOrMerge_MTO(
                            entityManager,
                            chiNhanh.getCccdQuanTriVien(),
                            QuanTriVien.class,
                            chiNhanh.getCccdQuanTriVien().getCccd(),
                            "Cccd QuanTriVien with ID "
                    );
                       newChiNhanh.setCccdQuanTriVien(quanTriVien);
                }
                 if (chiNhanh.getListDonMonAn() != null) {
                     List<DonMonAn> updatedDonMonAnList = entityHelper.updateOrCreateRelatedEntities_OTM(
                             chiNhanh.getListDonMonAn(),
                             DonMonAn::getMaDon,
                             id -> entityManager.find(DonMonAn.class, id),
                             donMonAn -> donMonAn.setMaChiNhanh(newChiNhanh), // Thiết lập quan hệ ngược
                             donMonAn -> entityManager.merge(donMonAn) // Lưu entity mới hoặc cập nhật
                     );

                     
                     newChiNhanh.setListDonMonAn(updatedDonMonAnList);
                 }
                 if (chiNhanh.getListMaKhuyenMai() != null) {
                     List<MaKhuyenMai> updatedMaKhuyenMaiList = entityHelper.updateOrCreateRelatedEntities_OTM(
                             chiNhanh.getListMaKhuyenMai(),
                             MaKhuyenMai::getIdKhuyenMai,
                             id -> entityManager.find(MaKhuyenMai.class, id),
                             maKhuyenMai -> maKhuyenMai.setMaChiNhanh(newChiNhanh), // Thiết lập quan hệ ngược
                             maKhuyenMai -> entityManager.merge(maKhuyenMai) // Lưu entity mới hoặc cập nhật
                     );

                     
                     newChiNhanh.setListMaKhuyenMai(updatedMaKhuyenMaiList);
                 }
                 if (chiNhanh.getListSuKienUuDai() != null) {
                     List<SuKienUuDai> updatedSuKienUuDaiList = entityHelper.updateOrCreateRelatedEntities_OTM(
                             chiNhanh.getListSuKienUuDai(),
                             SuKienUuDai::getMaUuDai,
                             id -> entityManager.find(SuKienUuDai.class, id),
                             suKienUuDai -> suKienUuDai.setMaChiNhanh(newChiNhanh), // Thiết lập quan hệ ngược
                             suKienUuDai -> entityManager.merge(suKienUuDai) // Lưu entity mới hoặc cập nhật
                     );

                     
                     newChiNhanh.setListSuKienUuDai(updatedSuKienUuDaiList);
                 }
                 if (chiNhanh.getListMonAnChiNhanh() != null) {
                     List<MonAnChiNhanh> newListMonAnChiNhanh = entityHelper.processNestedEntityList_MTMP(
                             entityManager,
                             chiNhanh.getListMonAnChiNhanh(),
                             MonAnChiNhanh.class,
                             MonAnChiNhanh::getMaMonAnChiNhanh,
                             MonAnChiNhanh::getMonAn,
                             MonAnChiNhanh::setMonAn,
                             MonAn.class,
                             MonAn::getMaMonAn,
                             "MonAnChiNhanh with ID ",
                             "MonAn with ID "
                     );
                     newChiNhanh.setListMonAnChiNhanh(newListMonAnChiNhanh);
                 }


                    return chiNhanhRepository.save(newChiNhanh);
             }
             public ChiNhanh getSpecificChiNhanh (String maChiNhanh) {

                 return chiNhanhRepository.findById(maChiNhanh)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<ChiNhanh> getAllChiNhanh (){
                  return chiNhanhRepository.findAll();
             }

             public String updateChiNhanh (String maChiNhanh, ChiNhanh chiNhanh) {
                  ChiNhanh chiNhanhUpdate =getSpecificChiNhanh(maChiNhanh);
                  chiNhanhMapper.updateChiNhanh(chiNhanhUpdate, chiNhanh);
                   chiNhanhRepository.save(chiNhanhUpdate);
                  return "update success";

             }
             public String deleteChiNhanh (String maChiNhanh) {
                    chiNhanhRepository.deleteById(maChiNhanh);
                    return "delete success";
             }


}
