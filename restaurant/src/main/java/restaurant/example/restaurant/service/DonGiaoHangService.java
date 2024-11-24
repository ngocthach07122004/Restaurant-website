package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.DonGiaoHang;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DonGiaoHangMapper;
import restaurant.example.restaurant.repository.DonGiaoHangRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonGiaoHangService {
             DonGiaoHangRepository donGiaoHangRepository;
             DonGiaoHangMapper donGiaoHangMapper;
             public DonGiaoHang createDonGiaoHang (DonGiaoHang dongiaohang) {
                   DonGiaoHang newDonGiaoHang = donGiaoHangMapper.toDonGiaoHang(dongiaohang);
                   newDonGiaoHang.setMaDon(dongiaohang.getMaDon());
                    return donGiaoHangRepository.save(newDonGiaoHang);
             }
             public DonGiaoHang getSpecificDonGiaoHang (String maDonGiaoHang) {

                 return donGiaoHangRepository.findById(maDonGiaoHang)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<DonGiaoHang> getAllDonGiaoHang (){
                  return donGiaoHangRepository.findAll();
             }

             public String updateDonGiaoHang (String maDonGiaoHang, DonGiaoHang donGiaoHang) {
                  DonGiaoHang donGiaoHangUpdate =getSpecificDonGiaoHang(maDonGiaoHang);
                  donGiaoHangMapper.updateDonGiaoHang(donGiaoHangUpdate, donGiaoHang);
                  donGiaoHangRepository.save(donGiaoHangUpdate);
                  return "update success";

             }
             public String deleteDonGiaoHang (String maDonGiaoHang) {
                    donGiaoHangRepository.deleteById(maDonGiaoHang);
                    return "delete success";
             }


}
