package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonMonAnBaoGomMonAn;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DonMonAnBaoGomMonAnMapper;
import restaurant.example.restaurant.repository.DonMonAnBaoGomMonAnRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonMonAnBaoGomMonAnService {
    DonMonAnBaoGomMonAnRepository donMonAnBaoGomMonAnRepository;
    DonMonAnBaoGomMonAnMapper donMonAnBaoGomMonAnMapper;
    public DonMonAnBaoGomMonAn createDonMonAnBaoGomMonAn (DonMonAnBaoGomMonAn donMonAnBaoGomMonAn) {
        DonMonAnBaoGomMonAn newDonMonAnBaoGomMonAn = donMonAnBaoGomMonAnMapper.toDonMonAnBaoGomMonAn(donMonAnBaoGomMonAn);
//        newDonMonAnBaoGomMonAn.setMaDonMonAnBaoGomMonAn(donMonAnBaoGomMonAn.getMaDonMonAnBaoGomMonAn());
        return donMonAnBaoGomMonAnRepository.save(newDonMonAnBaoGomMonAn);
    }
    public DonMonAnBaoGomMonAn getSpecificDonMonAnBaoGomMonAn (String maDonMonAnBaoGomMonAn) {

        return donMonAnBaoGomMonAnRepository.findById(maDonMonAnBaoGomMonAn)
                .orElseThrow(()-> new AppException(ErrorCode.MONAN_NOT_EXIST));

    }
    public List<DonMonAnBaoGomMonAn> getAllDonMonAnBaoGomMonAn (){
        return donMonAnBaoGomMonAnRepository.findAll();
    }

    public String updateDonMonAnBaoGomMonAn (String maDonMonAnBaoGomMonAn, DonMonAnBaoGomMonAn donMonAnBaoGomMonAn) {
        DonMonAnBaoGomMonAn donMonAnBaoGomMonAnUpdate =getSpecificDonMonAnBaoGomMonAn(maDonMonAnBaoGomMonAn);
        donMonAnBaoGomMonAnMapper.updateDonMonAnBaoGomMonAn(donMonAnBaoGomMonAnUpdate, donMonAnBaoGomMonAn);
        donMonAnBaoGomMonAnRepository.save(donMonAnBaoGomMonAnUpdate);
        return "update success";

    }
    public String deleteDonMonAnBaoGomMonAn (String maDonMonAnBaoGomMonAn) {
        donMonAnBaoGomMonAnRepository.deleteById(maDonMonAnBaoGomMonAn);

        return "delete success";
    }


}
