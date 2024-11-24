package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.MonAn;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.MonAnMapper;
import restaurant.example.restaurant.repository.MonAnRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MonAnService {
             MonAnRepository monAnRepository;
             MonAnMapper monAnMapper;
             public MonAn createMonAn (MonAn monAn) {
                   MonAn newMonAn = monAnMapper.toMonAn(monAn);
                   newMonAn.setMaMonAn(monAn.getMaMonAn());
                    return monAnRepository.save(newMonAn);
             }
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
