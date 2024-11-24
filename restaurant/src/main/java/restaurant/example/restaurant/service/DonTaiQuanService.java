package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonTaiQuan;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DonTaiQuanMapper;
import restaurant.example.restaurant.repository.DonTaiQuanRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonTaiQuanService {
             DonTaiQuanRepository donTaiQuanRepository;
             DonTaiQuanMapper donTaiQuanMapper;
             public DonTaiQuan createDonTaiQuan (DonTaiQuan dontaiquan) {
                   DonTaiQuan newDonTaiQuan = donTaiQuanMapper.toDonTaiQuan(dontaiquan);
                   newDonTaiQuan.setMaDon(dontaiquan.getMaDon());
                    return donTaiQuanRepository.save(newDonTaiQuan);
             }
             public DonTaiQuan getSpecificDonTaiQuan (String maDonTaiQuan) {

                 return donTaiQuanRepository.findById(maDonTaiQuan)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<DonTaiQuan> getAllDonTaiQuan (){
                  return donTaiQuanRepository.findAll();
             }

             public String updateDonTaiQuan (String maDonTaiQuan, DonTaiQuan donTaiQuan) {
                  DonTaiQuan donTaiQuanUpdate =getSpecificDonTaiQuan(maDonTaiQuan);
                  donTaiQuanMapper.updateDonTaiQuan(donTaiQuanUpdate, donTaiQuan);
                  donTaiQuanRepository.save(donTaiQuanUpdate);
                  return "update success";

             }
             public String deleteDonTaiQuan (String maDonTaiQuan) {
                    donTaiQuanRepository.deleteById(maDonTaiQuan);
                    return "delete success";
             }


}
