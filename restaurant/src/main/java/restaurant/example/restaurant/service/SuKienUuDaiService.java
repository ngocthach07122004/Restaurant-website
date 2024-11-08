package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.SuKienUuDai;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.SuKienUuDaiMapper;
import restaurant.example.restaurant.repository.SuKienUuDaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SuKienUuDaiService {
             SuKienUuDaiRepository suKienUuDaiRepository;
             SuKienUuDaiMapper suKienUuDaiMapper;
             public SuKienUuDai createSuKienUuDai (SuKienUuDai suKienUuDai) {
                   SuKienUuDai newSuKienUuDai = suKienUuDaiMapper.toSuKienUuDai(suKienUuDai);
                   newSuKienUuDai.setMaUuDai(suKienUuDai.getMaUuDai());
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
                  return "update success";

             }
             public String deleteSuKienUuDai (String maSuKienUuDai) {
                    suKienUuDaiRepository.deleteById(maSuKienUuDai);
                    return "delete success";
             }


}
