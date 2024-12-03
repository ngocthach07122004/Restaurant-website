package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
//import restaurant.example.restaurant.entity.BaoGom;
import restaurant.example.restaurant.entity.MaKhuyenMai;
import restaurant.example.restaurant.entity.MonAn;
import restaurant.example.restaurant.entity.MonAnGioHang;
import restaurant.example.restaurant.entity.SuKienUuDai;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.MonAnMapper;
import restaurant.example.restaurant.repository.MonAnRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MonAnService {
             MonAnRepository monAnRepository;
             MonAnMapper monAnMapper;
             @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
    public MonAn createMonAn (MonAn monAn) {
        MonAn newMonAn = monAnMapper.toMonAn(monAn);
                   newMonAn.setMaMonAn(monAn.getMaMonAn());
        if (monAn.getListMonAnGioHang() != null) {
            List<MonAnGioHang> updatedMonAnGioHangList = entityHelper.updateOrCreateRelatedEntities_OTM(
                    newMonAn.getListMonAnGioHang(),
                    MonAnGioHang::getMaMonAnGioHang, // Hàm lấy ID
                    id -> entityManager.find(MonAnGioHang.class, id), // Hàm tìm kiếm trong cơ sở dữ liệu
                    monAnGioHang -> monAnGioHang.setMonAn(newMonAn), // Thiết lập quan hệ ngược
                    monAnGioHang -> entityManager.merge(monAnGioHang) // Lưu entity mới hoặc cập nhật
            );
        }
            if (monAn.getListSuKienUuDai() != null) {
                List<SuKienUuDai> updateSuKienUuDaiList = entityHelper.processEntityList_MTM(
                        entityManager,
                        monAn.getListSuKienUuDai(),
                        SuKienUuDai.class,
                        SuKienUuDai::getMaUuDai,
                        "SuKienUuDai with ID"

                );
                newMonAn.setListSuKienUuDai(updateSuKienUuDaiList);
            }


//            List<Mission> listMission = newEmployee.getMissions();
//            List<Mission> newListMission = new ArrayList<>();
//            for( int i =0 ; i < listMission.size() ;i ++ ) {
//                  if(listMission.get(i)!=null){
//                       Mission mission = listMission.get(i);
//                   if(mission.getIdMission()!=null){
//                       Mission existingMission = entityManager.find(Mission.class,mission.getIdMission());
//                       if (existingMission == null) {
//                           throw new IllegalArgumentException("Address with ID " + mission.getIdMission()+ " does not exist.");
//                       }
//                       newListMission.add(existingMission);
//
//                   }
//                   else {
//                        Mission newMission = entityManager.merge(mission);
//                       newListMission.add(newMission);
//                   }
//                  }
//            }
//            newEmployee.setMissions(newListMission);
//
//            newMonAn.setListMonAnGioHang(updatedMonAnGioHangList);
//        }


            return monAnRepository.save(newMonAn);
        }
//    @Transactional
//             public MonAn createMonAn (MonAn monAn) {
//                   MonAn newMonAn = monAnMapper.toMonAn(monAn);
//                   newMonAn.setMaMonAn(monAn.getMaMonAn());
//        List<BaoGom> listBaoGom = monAn.getListBaoGom();
//        List<BaoGom> newListBaoGom = new ArrayList<>();
//        if(listBaoGom!=null) {
//            for (int i = 0; i < listBaoGom.size(); i++) {
//                if (listBaoGom.get(i) != null) {
//                    BaoGom baoGom = listBaoGom.get(i);
//                    if (baoGom.getMaMonAnMaDonKey() != null) {
//                        BaoGom existingBaoGom = entityManager.find(BaoGom.class, baoGom.getMaMonAnMaDonKey());
//                        if (existingBaoGom == null) {
//                            throw new IllegalArgumentException("Address with ID " + baoGom.getMaMonAnMaDonKey() + " does not exist.");
//                        }
//                        newListBaoGom.add(existingBaoGom);
//
//                    } else {
//                        BaoGom newBaoGom = entityManager.merge(baoGom);
//                        newListBaoGom.add(newBaoGom);
//                    }
//                }
//            }
//        }
//        newMonAn.setListBaoGom(newListBaoGom);
//                    return monAnRepository.save(newMonAn);
//             }
             public MonAn getSpecificMonAn (String maMonAn) {

                 return monAnRepository.findById(maMonAn)
                         .orElseThrow(()-> new AppException(ErrorCode.MONAN_NOT_EXIST));

             }
             public List<MonAn> getAllMonAn (){
                  return monAnRepository.findAll();
             }

             public String updateMonAn (String maMonAn, MonAn monAn) {
                  MonAn monAnUpdate =getSpecificMonAn(maMonAn);
                  monAnMapper.updateMonAn(monAnUpdate, monAn);
                  monAnRepository.save(monAnUpdate);
                  return "update success";

             }
             public String deleteMonAn (String maMonAn) {
                    monAnRepository.deleteById(maMonAn);

                    return "delete success";
             }


}
