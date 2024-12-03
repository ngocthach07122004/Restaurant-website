package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.MonAn;
import restaurant.example.restaurant.entity.SuKienUuDai;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.SuKienUuDaiMapper;
import restaurant.example.restaurant.repository.SuKienUuDaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SuKienUuDaiService {
             SuKienUuDaiRepository suKienUuDaiRepository;
             SuKienUuDaiMapper suKienUuDaiMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public SuKienUuDai createSuKienUuDai (SuKienUuDai suKienUuDai) {
                   SuKienUuDai newSuKienUuDai = suKienUuDaiMapper.toSuKienUuDai(suKienUuDai);
                   newSuKienUuDai.setMaUuDai(suKienUuDai.getMaUuDai());

        if (suKienUuDai.getMaChiNhanh() != null) {
            ChiNhanh chiNhanh = entityHelper.findOrMerge_MTO(
                    entityManager,
                    suKienUuDai.getMaChiNhanh(),
                    ChiNhanh.class,
                    suKienUuDai.getMaChiNhanh().getMaChiNhanh(),
                    "ChiNhanh with ID "
            );
            newSuKienUuDai.setMaChiNhanh(chiNhanh);
        }


        if (suKienUuDai.getListMonAn() != null) {
            List<MonAn> updateMonAnList = entityHelper.processEntityList_MTM(
                    entityManager,
                    suKienUuDai.getListMonAn(),
                    MonAn.class,
                    MonAn::getMaMonAn,
                    "MonAn with ID"

            );
            suKienUuDai.setListMonAn(updateMonAnList);
        }


                    return suKienUuDaiRepository.save(newSuKienUuDai);
             }
             public SuKienUuDai getSpecificSuKienUuDai (String maSuKienUuDai) {

                 return suKienUuDaiRepository.findById(maSuKienUuDai)
                         .orElseThrow(()-> new AppException(ErrorCode.SUKIENUUDAI_NOT_EXIST));

             }
             public List<SuKienUuDai> getAllSuKienUuDai (){
                  return suKienUuDaiRepository.findAll();
             }

             public String updateSuKienUuDai (String maSuKienUuDai, SuKienUuDai suKienUuDai) {
                  SuKienUuDai suKienUuDaiUpdate =getSpecificSuKienUuDai(maSuKienUuDai);
                  suKienUuDaiMapper.updateSuKienUuDai(suKienUuDaiUpdate, suKienUuDai);
                  suKienUuDaiRepository.save(suKienUuDaiUpdate);
                  return "update success";

             }
             public String deleteSuKienUuDai (String maSuKienUuDai) {
                    suKienUuDaiRepository.deleteById(maSuKienUuDai);
                    return "delete success";
             }


}
