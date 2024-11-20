package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonMonAn;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DonMonAnMapper;
import restaurant.example.restaurant.repository.DonMonAnRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonMonAnService {
             DonMonAnRepository donMonAnRepository;
             DonMonAnMapper donMonAnMapper;
             public DonMonAn createDonMonAn (DonMonAn donMonAn) {
                   DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
                   newDonMonAn.setMaDon(donMonAn.getMaDon());
                    return donMonAnRepository.save(newDonMonAn);
             }
             public DonMonAn getSpecificDonMonAn (String maDonMonAn) {

                 return donMonAnRepository.findById(maDonMonAn)
                         .orElseThrow(()-> new AppException(ErrorCode.DONMONAN_NOT_EXIST));

             }
             public List<DonMonAn> getAllDonMonAn (){
                  return donMonAnRepository.findAll();
             }

             public String updateDonMonAn (String maDonMonAn, DonMonAn donMonAn) {
                  DonMonAn donMonAnUpdate =getSpecificDonMonAn(maDonMonAn);
                  donMonAnMapper.updateDonMonAn(donMonAnUpdate, donMonAn);
                  donMonAnRepository.save(donMonAnUpdate);
                  return "update success";

             }
             public String deleteDonMonAn (String maDonMonAn) {
                    donMonAnRepository.deleteById(maDonMonAn);
                    return "delete success";
             }


}
