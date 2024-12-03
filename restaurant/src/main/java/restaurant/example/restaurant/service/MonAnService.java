package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
//import restaurant.example.restaurant.entity.BaoGom;
import restaurant.example.restaurant.entity.MonAn;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
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
