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
import restaurant.example.restaurant.mapper.DonKhieuNaiMapper;
import restaurant.example.restaurant.repository.DonKhieuNaiRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class DonKhieuNaiService {
             DonKhieuNaiRepository donKhieuNaiRepository;
             DonKhieuNaiMapper donKhieuNaiMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public DonKhieuNai createDonKhieuNai (DonKhieuNai donKhieuNai) {
                   DonKhieuNai newDonKhieuNai = donKhieuNaiMapper.toDonKhieuNai(donKhieuNai);
                   newDonKhieuNai.setMaDonKhieuNai(donKhieuNai.getMaDonKhieuNai());
                 if (donKhieuNai.getCccdKhachHang() != null) {
                     KhachHang khachHang = entityHelper.findOrMerge_MTO(
                             entityManager,
                             donKhieuNai.getCccdKhachHang(),
                             KhachHang.class,
                             donKhieuNai.getCccdKhachHang().getMaKhachHang(),
                             "KhachHang with ID "
                     );
                     newDonKhieuNai.setCccdKhachHang(khachHang);
                 }
        if (donKhieuNai.getDonMonAn() != null) {
            DonMonAn khachHang = entityHelper.findOrMerge_MTO(
                    entityManager,
                    donKhieuNai.getDonMonAn(),
                    DonMonAn.class,
                    donKhieuNai.getDonMonAn().getMaDon(),
                    "DonMonAn with ID "
            );
            newDonKhieuNai.setDonMonAn(khachHang);
        }
        if (donKhieuNai.getCccdNhanVienQuanLy() != null) {
            NhanVienQuanLy nhanVienQuanLy = entityHelper.findOrMerge_MTO(
                    entityManager,
                    donKhieuNai.getCccdNhanVienQuanLy(),
                    NhanVienQuanLy.class,
                    donKhieuNai.getCccdNhanVienQuanLy().getMaNhanVienQuanLy(),
                    "NhanVienQuanLy with ID "
            );
            newDonKhieuNai.setCccdNhanVienQuanLy(nhanVienQuanLy);
        }


                    return donKhieuNaiRepository.save(newDonKhieuNai);
             }
             public DonKhieuNai getSpecificDonKhieuNai (String maDonKhieuNai) {

                 return donKhieuNaiRepository.findById(maDonKhieuNai)
                         .orElseThrow(()-> new AppException(ErrorCode.DONKHIEUNAI_NOT_EXIST));

             }
             public List<DonKhieuNai> getAllDonKhieuNai (){
                  return donKhieuNaiRepository.findAll();
             }

             public String updateDonKhieuNai (String maDonKhieuNai, DonKhieuNai donKhieuNai) {
                  DonKhieuNai donKhieuNaiUpdate =getSpecificDonKhieuNai(maDonKhieuNai);
                  donKhieuNaiMapper.updateDonKhieuNai(donKhieuNaiUpdate, donKhieuNai);
                  donKhieuNaiRepository.save(donKhieuNaiUpdate);
                  return "update success";

             }
             public String deleteDonKhieuNai (String maDonKhieuNai) {
                    donKhieuNaiRepository.deleteById(maDonKhieuNai);
                    return "delete success";
             }


}
