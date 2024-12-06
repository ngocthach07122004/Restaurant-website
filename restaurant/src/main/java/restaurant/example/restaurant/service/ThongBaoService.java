package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.*;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.ThongBaoMapper;
import restaurant.example.restaurant.repository.ThongBaoRepository;

import java.time.LocalDateTime;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongBaoService {
             ThongBaoRepository thongBaoRepository;
             ThongBaoMapper thongBaoMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public ThongBao createThongBao (ThongBao thongBao) {

                   ThongBao newThongBao = thongBaoMapper.toThongBao(thongBao);
                   newThongBao.setThoiGian(LocalDateTime.now());
                   newThongBao.setMaThongBao(thongBao.getMaThongBao());
        if (thongBao.getCccdQuanTriVien()!= null) {
            QuanTriVien quanTriVien = entityHelper.findOrMerge_MTO(
                    entityManager,
                    thongBao.getCccdQuanTriVien(),
                    QuanTriVien.class,
                    thongBao.getCccdQuanTriVien().getCccd(),
                    "QuanTriVien with ID "
            );
            newThongBao.setCccdQuanTriVien(quanTriVien);
        }

        if (thongBao.getListThongTin() != null) {
            List<ThongTin> updateThongTinList = entityHelper.processEntityList_MTM(
                    entityManager,
                    thongBao.getListThongTin(),
                    ThongTin.class,
                    ThongTin::getCccd,
                    "ThongTin with ID"

            );
            newThongBao.setListThongTin(updateThongTinList);
        }

//        if (thongBao.getCccdThongTin()!= null) {
//            ThongTin thongTin = entityHelper.findOrMerge_MTO(
//                    entityManager,
//                    thongBao.getCccdThongTin(),
//                    ThongTin.class,
//                    thongBao.getCccdThongTin().getCccd(),
//                    "ThongTin with ID "
//            );
//            newThongBao.setCccdThongTin(thongTin);
//        }





        if (thongBao.getCccdNhanVienQuanLy()!= null) {
            NhanVienQuanLy nhanVienQuanLy = entityHelper.findOrMerge_MTO(
                    entityManager,
                    thongBao.getCccdNhanVienQuanLy(),
                    NhanVienQuanLy.class,
                    thongBao.getCccdNhanVienQuanLy().getMaNhanVienQuanLy(),
                    "NhanVienQuanLy with ID "
            );
            newThongBao.setCccdNhanVienQuanLy(nhanVienQuanLy);
        }

                    return thongBaoRepository.save(newThongBao);
             }
             public ThongBao getSpecificThongBao (String maThongBao) {

                 return thongBaoRepository.findById(maThongBao)
                         .orElseThrow(()-> new AppException(ErrorCode.THONGBAO_NOT_EXIST));

             }
             public List<ThongBao> getAllThongBao (){
                  return thongBaoRepository.findAll();
             }

             public String updateThongBao (String maThongBao, ThongBao thongBao) {
                  ThongBao thongBaoUpdate =getSpecificThongBao(maThongBao);
                  thongBaoMapper.updateThongBao(thongBaoUpdate, thongBao);
                  thongBaoRepository.save(thongBaoUpdate);
                  return "update success";

             }
             public String deleteThongBao (String maThongBao) {
                    thongBaoRepository.deleteById(maThongBao);
                    return "delete success";
             }


}
