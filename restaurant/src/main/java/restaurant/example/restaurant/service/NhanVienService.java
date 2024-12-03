package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.entity.NhanVienQuanLy;
import restaurant.example.restaurant.entity.ThongTin;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.NhanVienMapper;
import restaurant.example.restaurant.repository.NhanVienRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienService {
             NhanVienRepository nhanVienRepository;
             NhanVienMapper nhanVienMapper;
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
             public NhanVien createNhanVien (NhanVien nhanvien) {
                   NhanVien newNhanVien = nhanVienMapper.toNhanVien(nhanvien);
                   newNhanVien.setMaNhanVien(nhanvien.getMaNhanVien());
                   if (nhanvien.getCccd() != null) {
                    ThongTin cccd = entityHelper.findOrMerge_OTO(
                            entityManager,
                            nhanvien.getCccd(),
                            ThongTin.class,
                            nhanvien.getCccd().getCccd(),
                            "ThongTin with ID "
                    );
                       newNhanVien.setCccd(cccd); // Thiết lập mối quan hệ One-to-One
                }
                entityManager.persist(newNhanVien);

        if (nhanvien.getCccdNhanVienQuanLy() != null) {
            NhanVienQuanLy nhanVienQuanLy = entityHelper.findOrMerge_MTO(
                    entityManager,
                    nhanvien.getCccdNhanVienQuanLy(),
                    NhanVienQuanLy.class,
                    nhanvien.getCccdNhanVienQuanLy().getCccd(),
                    "NhanVienQuanLy with ID "
            );
            newNhanVien.setCccdNhanVienQuanLy(nhanVienQuanLy);
        }

        if (nhanvien.getMaChiNhanh() != null) {
            ChiNhanh chiNhanh = entityHelper.findOrMerge_MTO(
                    entityManager,
                    nhanvien.getMaChiNhanh(),
                    ChiNhanh.class,
                    nhanvien.getMaChiNhanh().getMaChiNhanh(),
                    "ChiNhanh with ID "
            );
            newNhanVien.setMaChiNhanh(chiNhanh);
        }

                

        return nhanVienRepository.save(newNhanVien);
             }
             public NhanVien getSpecificNhanVien (String maNhanVien) {

                 return nhanVienRepository.findById(maNhanVien)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<NhanVien> getAllNhanVien (){
                  return nhanVienRepository.findAll();
             }

             public String updateNhanVien (String maNhanVien, NhanVien nhanVien) {
                  NhanVien nhanVienUpdate =getSpecificNhanVien(maNhanVien);
                  nhanVienMapper.updateNhanVien(nhanVienUpdate, nhanVien);
                  nhanVienRepository.save(nhanVienUpdate);
                  return "update success";

             }
             public String deleteNhanVien (String maNhanVien) {
                    nhanVienRepository.deleteById(maNhanVien);
                    return "delete success";
             }


}
