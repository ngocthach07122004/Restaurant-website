package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.*;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.DonMonAnMapper;
import restaurant.example.restaurant.mapper.MonAnMapper;
import restaurant.example.restaurant.repository.DonMonAnRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DonMonAnService {
    DonMonAnRepository donMonAnRepository;
    //             DonMonAnBaoGomMonAnRepository donMonAnBaoGomMonAnRepository;
    DonMonAnMapper donMonAnMapper;
    MonAnMapper monAnMapper;
    @PersistenceContext
    EntityManager entityManager;

    EntityHelper entityHelper;

    @Transactional
    public DonMonAn createDonMonAn(DonMonAn donMonAn) {
        DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
        newDonMonAn.setMaDon(donMonAn.getMaDon());
//      MANY TO ONE
        if (donMonAn.getMaChiNhanh() != null) {
            ChiNhanh chiNhanh = entityHelper.findOrMerge_MTO(
                    entityManager,
                    donMonAn.getMaChiNhanh(),
                    ChiNhanh.class,
                    donMonAn.getMaChiNhanh().getMaChiNhanh(),
                    "ChiNhanh with ID "
            );
            newDonMonAn.setMaChiNhanh(chiNhanh);
        }

//        MANY TO ONE
        if (donMonAn.getCccdKhachHang() != null) {
            KhachHang khachHang = entityHelper.findOrMerge_MTO(
                    entityManager,
                    donMonAn.getCccdKhachHang(),
                    KhachHang.class,
                    donMonAn.getCccdKhachHang().getMaKhachHang(),
                    "KhachHang with ID "
            );
            newDonMonAn.setCccdKhachHang(khachHang);
        }

//        MANY TO ONE
        if (donMonAn.getCccdNhanVienThuNgan() != null) {
            NhanVienThuNgan nhanVienThuNgan = entityHelper.findOrMerge_MTO(
                    entityManager,
                    donMonAn.getCccdNhanVienThuNgan(),
                    NhanVienThuNgan.class,
                    donMonAn.getCccdNhanVienThuNgan().getCccd(),
                    "NhanVienThuNgan with ID "
            );
            newDonMonAn.setCccdNhanVienThuNgan(nhanVienThuNgan);
        }



//      ONE TO MANY
        if (donMonAn.getListMaKhuyenMai() != null) {
            List<MaKhuyenMai> updatedMaKhuyenMaiList = entityHelper.updateOrCreateRelatedEntities_OTM(
                    newDonMonAn.getListMaKhuyenMai(),
                    MaKhuyenMai::getIdKhuyenMai, // Hàm lấy ID
                    id -> entityManager.find(MaKhuyenMai.class, id), // Hàm tìm kiếm trong cơ sở dữ liệu
                    maKhuyenMai -> maKhuyenMai.setMaDon(newDonMonAn), // Thiết lập quan hệ ngược
                    maKhuyenMai -> entityManager.merge(maKhuyenMai) // Lưu entity mới hoặc cập nhật
            );

            // Gán lại danh sách đã xử lý
            newDonMonAn.setListMaKhuyenMai(updatedMaKhuyenMaiList);
        }


       // MANY TO MANY PHUC TAP
        if (donMonAn.getListMonAnGioHang() != null) {
            List<MonAnGioHang> newListMonAnGioHang = entityHelper.processNestedEntityList_MTMP(
                    entityManager,
                    donMonAn.getListMonAnGioHang(),
                    MonAnGioHang.class,
                    MonAnGioHang::getMaMonAnGioHang,
                    MonAnGioHang::getMonAn,
                    MonAnGioHang::setMonAn,
                    MonAn.class,
                    MonAn::getMaMonAn,
                    "MonAnGioHang with ID ",
                    "MonAn with ID "
            );
            newDonMonAn.setListMonAnGioHang(newListMonAnGioHang);
        }


        return donMonAnRepository.save(newDonMonAn);
    }

    public DonMonAn getSpecificDonMonAn (String maDonMonAn) {

        return donMonAnRepository.findById(maDonMonAn)
                .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

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
//    HAM CREATE

        //        if (donMonAn.getMaChiNhanh() != null) {
//            ChiNhanh chiNhanh = donMonAn.getMaChiNhanh();
//            if (chiNhanh.getMaChiNhanh() != null) {
//                ChiNhanh existingChiNhanh = entityManager.find(ChiNhanh.class,chiNhanh.getMaChiNhanh());
//
//                if (existingChiNhanh == null) {
//                    throw new IllegalArgumentException("Department with ID " +chiNhanh.getMaChiNhanh() + " does not exist.");
//                }
//
//
//                newDonMonAn.setMaChiNhanh(existingChiNhanh);
//            } else {
//
//                ChiNhanh newChiNhanh = entityManager.merge(chiNhanh);
//                newDonMonAn.setMaChiNhanh(newChiNhanh);
//            }
//
//        }

        //        if (donMonAn.getCccdKhachHang() != null) {
//            KhachHang khachHang = donMonAn.getCccdKhachHang();
//            if (khachHang.getCccd() != null) {
//                KhachHang existingKhachHang = entityManager.find(KhachHang.class,khachHang.getCccd());
//
//                if (existingKhachHang == null) {
//                    throw new IllegalArgumentException("Department with ID " +khachHang.getCccd() + " does not exist.");
//                }
//
//
//                newDonMonAn.setCccdKhachHang(existingKhachHang);
//            } else {
//
//                KhachHang newKhachHang = entityManager.merge(khachHang);
//                newDonMonAn.setCccdKhachHang(newKhachHang);
//            }
//
//        }

    //        if (donMonAn.getCccdNhanVienThuNgan() != null) {
//            NhanVienThuNgan nhanVienThuNgan = donMonAn.getCccdNhanVienThuNgan();
//            if (nhanVienThuNgan.getCccd() != null) {
//                NhanVienThuNgan existingNhanVienThuNgan = entityManager.find(NhanVienThuNgan.class,nhanVienThuNgan.getCccd());
//
//                if (existingNhanVienThuNgan == null) {
//                    throw new IllegalArgumentException("Department with ID " +nhanVienThuNgan.getCccd() + " does not exist.");
//                }
//
//
//                newDonMonAn.setCccdNhanVienThuNgan(existingNhanVienThuNgan);
//            } else {
//
//                NhanVienThuNgan newNhanVienThuNgan = entityManager.merge(nhanVienThuNgan);
//                newDonMonAn.setCccdNhanVienThuNgan(newNhanVienThuNgan);
//            }
//
//        }

    //        List<MaKhuyenMai> listMaKhuyenMai = newDonMonAn.getListMaKhuyenMai();
//        List<MaKhuyenMai> newListMaKhuyenMai = new ArrayList<>();
//        for (int i = 0; i < listMaKhuyenMai.size(); i++) {
//            if (listMaKhuyenMai.get(i) != null) {
//                MaKhuyenMai maKhuyenMai = listMaKhuyenMai.get(i);
//                if (maKhuyenMai.getIdKhuyenMai() != null) {
//                    MaKhuyenMai existingMaKhuyenMai = entityManager.find(MaKhuyenMai.class, maKhuyenMai.getIdKhuyenMai());
//                    if (existingMaKhuyenMai == null) {
//                        throw new IllegalArgumentException("MaKhuyenMai with ID " + maKhuyenMai.getIdKhuyenMai() + " does not exist.");
//                    }
//                    // Cập nhật ngược quan hệ
//                    existingMaKhuyenMai.setMaDon(newDonMonAn);
//                    newListMaKhuyenMai.add(existingMaKhuyenMai);
//                } else {
//                    // Tạo mới MaKhuyenMai
//                    maKhuyenMai.setMaDon(newDonMonAn);
//                    MaKhuyenMai newMaKhuyenMai = entityManager.merge(maKhuyenMai);
//                    newListMaKhuyenMai.add(newMaKhuyenMai);
//                }
//            }
//        }
//        newDonMonAn.setListMaKhuyenMai(newListMaKhuyenMai);


//        List<MaKhuyenMai> listMaKhuyenMai = newDonMonAn.getListMaKhuyenMai();
//        List<MaKhuyenMai> newListMaKhuyenMai = new ArrayList<>();
//        for( int i =0 ; i < listMaKhuyenMai.size() ;i ++ ) {
//            if(listMaKhuyenMai.get(i)!=null){
//                MaKhuyenMai maKhuyenMai = listMaKhuyenMai.get(i);
//                if(maKhuyenMai.getIdKhuyenMai()!=null){
//                    MaKhuyenMai existingMaKhuyenMai = entityManager.find(MaKhuyenMai.class,maKhuyenMai.getIdKhuyenMai());
//                    if (existingMaKhuyenMai == null) {
//                        throw new IllegalArgumentException("Address with ID " + maKhuyenMai.getIdKhuyenMai()+ " does not exist.");
//                    }
//                    newListMaKhuyenMai.add(existingMaKhuyenMai);
//
//                }
//                else {
//                    MaKhuyenMai newMaKhuyenMai = entityManager.merge(maKhuyenMai);
//                    newListMaKhuyenMai.add(newMaKhuyenMai);
//                }
//            }
//        }
//        newDonMonAn.setListMaKhuyenMai(newListMaKhuyenMai);

//        if (donMonAn.getListMaKhuyenMai() != null) {
//            List<MaKhuyenMai> newListMaKhuyenMai = entityHelper.processEntityList(
//                    entityManager,
//                    donMonAn.getListMaKhuyenMai(),
//                    MaKhuyenMai.class,
//                    MaKhuyenMai::getIdKhuyenMai,
//                    "MaKhuyenMai with ID "
//            );
//            newDonMonAn.setListMaKhuyenMai(newListMaKhuyenMai);
//        }


    //        List<MonAnGioHang> listMonAnGioHang = newDonMonAn.getListMonAnGioHang();
//        List<MonAnGioHang> newListMonAnGioHang = new ArrayList<>();
//        for (int i = 0; i < listMonAnGioHang.size(); i++) {
//            if (listMonAnGioHang.get(i) != null) {
//                MonAnGioHang monAnGioHang = listMonAnGioHang.get(i);
//                if (monAnGioHang.getMaMonAnGioHang() != null) {
//                    MonAnGioHang existingMonAnGioHang = entityManager.find(MonAnGioHang.class, monAnGioHang.getMaMonAnGioHang());
//
//
//                    if (existingMonAnGioHang.getMonAn() != null) {
//                        MonAn MonAnTemporary = existingMonAnGioHang.getMonAn();
//                        if (MonAnTemporary.getMaMonAn() != null) {
//                            MonAn addressInMonAnGioHang = entityManager.find(MonAn.class, MonAnTemporary.getMaMonAn());
//                            if (addressInMonAnGioHang == null) {
//                                throw new IllegalArgumentException("MonAn with ID " + MonAnTemporary.getMaMonAn() + " does not exist.");
//                            }
//                            existingMonAnGioHang.setMonAn(addressInMonAnGioHang);
//
//
//                        } else {
//                            // tạo mới địa chỉ
//                        }
//                    }
//                    if (existingMonAnGioHang == null) {
//                        throw new IllegalArgumentException("MonAn with ID " + monAnGioHang.getMaMonAnGioHang() + " does not exist.");
//                    }
//                    newListMonAnGioHang.add(existingMonAnGioHang);
//
//                } else {
//                    if (monAnGioHang.getMonAn() != null) {
//                        MonAn MonAnTemporary = monAnGioHang.getMonAn();
//                        if (MonAnTemporary.getMaMonAn()!= null) {
//                            MonAn addressInMonAnGioHang = entityManager.find(MonAn.class, MonAnTemporary.getMaMonAn());
//                            if (addressInMonAnGioHang == null) {
//                                throw new IllegalArgumentException("MonAn with ID " + MonAnTemporary.getMaMonAn() + " does not exist.");
//                            }
//                            monAnGioHang.setMonAn(addressInMonAnGioHang);
//
//
//                        } else {
//                            // tạo mới địa chỉ
//                        }
//                    }
////                    log.info("CHECK123");
////                    log.info("CHECK ID {}", monAnGioHang.getIdMonAnGioHang());
////                    log.info("CHECK ID {}", monAnGioHang.getMonAn().getIdMonAn());
////                    monAnGioHangRepository.save(monAnGioHang);
//                    MonAnGioHang newMonAnGioHang = entityManager.merge(monAnGioHang);
//                    newListMonAnGioHang.add(monAnGioHang);
//
//
//                }
//            }
//        }
//        newDonMonAn.setListMonAnGioHang(newListMonAnGioHang);


//        if (donMonAn.getListMonAnGioHang() != null) {
//            List<MonAnGioHang> newListMonAnGioHang = entityHelper.processNestedEntityList(
//                    entityManager,
//                    donMonAn.getListMonAnGioHang(),
//                    MonAnGioHang.class,
//                    MonAnGioHang::getMaMonAnGioHang,
//                    MonAnGioHang::getMonAn,
//                    MonAnGioHang::setMonAn,
//                    MonAn.class,
//                    MonAn::getMaMonAn,
//                    "MonAnGioHang with ID ",
//                    "MonAn with ID "
//            );
//            newDonMonAn.setListMonAnGioHang(newListMonAnGioHang);
//        }
    //    HAM CREATE


//            @Transactional
//    public DonMonAn createDonMonAn(DonMonAn donMonAn) {
//        // Tạo DonMonAn mới
//        DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
//
//        // Xử lý danh sách BaoGom
//        List<BaoGom> listBaoGom = donMonAn.getListBaoGom();
//        List<BaoGom> newListBaoGom = new ArrayList<>();
//
//        if (listBaoGom != null) {
//            for (BaoGom baoGom : listBaoGom) {
//                if (baoGom.getMaBaoGom() != null) {
//                    // Kiểm tra nếu BaoGom đã tồn tại
//                    BaoGom existingBaoGom = entityManager.find(BaoGom.class, baoGom.getMaBaoGom());
//                    if (existingBaoGom != null) {
//                        newListBaoGom.add(existingBaoGom);
//                    } else {
//                        throw new IllegalArgumentException("BaoGom với ID " + baoGom.getMaBaoGom() + " không tồn tại.");
//                    }
//                } else {
//                    // Lưu mới BaoGom
//                    baoGom.setMaDonBaoGom(newDonMonAn); // Thiết lập liên kết với DonMonAn
//                    entityManager.persist(baoGom);
//                    newListBaoGom.add(baoGom);
//                }
//            }
//        }
//
//        // Gán danh sách BaoGom đã xử lý vào DonMonAn
//        newDonMonAn.setListBaoGom(newListBaoGom);
//
//        // Lưu DonMonAn
//        entityManager.persist(newDonMonAn);
//
//        return newDonMonAn;
//    }


//@Transactional
//public DonMonAn createDonMonAn(DonMonAn donMonAn) {
//    DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
//    newDonMonAn.setMaDon(donMonAn.getMaDon());
//
//    List<BaoGom> listBaoGom = donMonAn.getListBaoGom();
//    List<BaoGom> newListBaoGom = new ArrayList<>();
//
//    if (listBaoGom != null) {
//        for (BaoGom baoGom : listBaoGom) {
//            if (baoGom != null) {
//                if (baoGom.getMaMonAnMaDonKey() != null) {
//
//                    BaoGom existingBaoGom = entityManager.find(BaoGom.class, baoGom.getMaMonAnMaDonKey());
//                    MonAn monAnKey = entityManager.find(MonAn.class, baoGom.getMaMonAnMaDonKey().getMaMonAnId());
//                    DonMonAn donMonAnKey = entityManager.find(DonMonAn.class, baoGom.getMaMonAnMaDonKey().getMaDonId());
//
//                    // Thiết lập các thực thể liên quan
//                    baoGom.setMonAn(monAnKey);
//                    baoGom.setDonMonAn(donMonAnKey);
//                    if (existingBaoGom != null) {
//                        newListBaoGom.add(existingBaoGom);
//                    } else {
//                        // Tùy chọn: Tự động thêm mới nếu đối tượng không tồn tại
//                        BaoGom newBaoGom = entityManager.merge(baoGom);
//                        newListBaoGom.add(newBaoGom);
//                    }
//                } else {
//                    BaoGom newBaoGom = entityManager.merge(baoGom);
//                    newListBaoGom.add(newBaoGom);
//                }
//            }
//        }
//    }
//    newDonMonAn.setListBaoGom(newListBaoGom);
//    return donMonAnRepository.save(newDonMonAn);
//}
//@Transactional
//public DonMonAn createDonMonAn(DonMonAn donMonAn) {
//    DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
//    newDonMonAn.setMaDon(donMonAn.getMaDon());
//
//    List<BaoGom> listBaoGom = donMonAn.getListBaoGom();
//    List<BaoGom> newListBaoGom = new ArrayList<>();
//
//    if (listBaoGom != null) {
//        for (BaoGom baoGom : listBaoGom) {
//            if (baoGom != null && baoGom.getMaMonAnMaDonKey() != null) {
//                // Kiểm tra sự tồn tại
//                BaoGom existingBaoGom = entityManager.find(BaoGom.class, baoGom.getMaMonAnMaDonKey());
//                if (existingBaoGom != null) {
//
//                    existingBaoGom.setDonMonAn(newDonMonAn);
//
//                    newListBaoGom.add(existingBaoGom);
//                } else {
//                    // Nạp các thực thể liên quan
//                    MonAn monAnKey = entityManager.find(MonAn.class, baoGom.getMaMonAnMaDonKey().getMaMonAnId());
//                    DonMonAn donMonAnKey = entityManager.find(DonMonAn.class, baoGom.getMaMonAnMaDonKey().getMaDonId());
//
//                    baoGom.setMonAn(monAnKey);
//                    baoGom.setDonMonAn(donMonAnKey);
//
//                    // Thêm mới
//                    newListBaoGom.add(entityManager.merge(baoGom));
//                }
//            } else if (baoGom != null) {
//                baoGom.setDonMonAn(newDonMonAn);
//                newListBaoGom.add(entityManager.merge(baoGom));
//            }
//        }
//    }
//
//    newDonMonAn.setListBaoGom(newListBaoGom);
//
//    return donMonAnRepository.save(newDonMonAn);
//}
//   @Transactional
//    public DonMonAn createDonMonAn(DonMonAn donMonAn) {
//        DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
//        newDonMonAn.setMaDon(donMonAn.getMaDon());
//
//        List<BaoGom> listBaoGom = donMonAn.getListBaoGom();
//        List<BaoGom> newListBaoGom = new ArrayList<>();
//
//        if (listBaoGom != null) {
//            for (BaoGom baoGom : listBaoGom) {
//                if (baoGom != null) {
//                    if (baoGom.getMaMonAnMaDonKey() != null) {
//                        // Kiểm tra sự tồn tại của BaoGom trong DB
//                        BaoGom existingBaoGom = entityManager.find(BaoGom.class, baoGom.getMaMonAnMaDonKey());
//                        if (existingBaoGom != null) {
//                            // Nếu tồn tại, gắn lại liên kết với DonMonAn mới
//                            existingBaoGom.setDonMonAn(newDonMonAn);
//                            newListBaoGom.add(existingBaoGom);
//                        } else {
//                            // Nạp các thực thể liên quan
//                            MonAn monAnKey = entityManager.find(MonAn.class, baoGom.getMaMonAnMaDonKey().getMaMonAnId());
//                            DonMonAn donMonAnKey = entityManager.find(DonMonAn.class, baoGom.getMaMonAnMaDonKey().getMaDonId());
//
//                            baoGom.setMonAn(monAnKey);
//                            baoGom.setDonMonAn(donMonAnKey); // Gắn DonMonAn mới
//
//                            // Thêm mới vào danh sách
//                            newListBaoGom.add(entityManager.merge(baoGom));
//                        }
//                    } else {
//                        // Nếu không có MaMonAnMaDonKey, chỉ thêm BaoGom mới
//                        baoGom.setDonMonAn(newDonMonAn); // Gắn DonMonAn mới
//                        newListBaoGom.add(entityManager.merge(baoGom));
//                    }
//                }
//            }
//        }
//
//        // Gắn danh sách BaoGom vào DonMonAn
//        newDonMonAn.setListBaoGom(newListBaoGom);
//
//        // Lưu DonMonAn mới
//        return donMonAnRepository.save(newDonMonAn);
//    }


    //      @Transactional
//    public DonMonAn createDonMonAn (DonMonAn donMonAn) {
//                   DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
//                   newDonMonAn.setMaDon(donMonAn.getMaDon());
//
//
//
//
//                 List<BaoGom> listBaoGom = donMonAn.getListBaoGom();
//                 List<BaoGom> newListBaoGom = new ArrayList<>();
//            if(listBaoGom!=null) {
//                for (int i = 0; i < listBaoGom.size(); i++) {
//                    if (listBaoGom.get(i) != null) {
//                        BaoGom baoGom = listBaoGom.get(i);
//                        if (baoGom.getMaMonAnMaDonKey() != null) {
//                            BaoGom existingBaoGom = entityManager.find(BaoGom.class, baoGom.getMaMonAnMaDonKey());
//                            if (existingBaoGom == null) {
//                                throw new IllegalArgumentException("MonAn with ID " + baoGom.getMaMonAnMaDonKey() + " does not exist.");
//                            }
//                            newListBaoGom.add(existingBaoGom);
//
//                        } else {
//                            BaoGom newBaoGom = entityManager.merge(baoGom);
//                            newListBaoGom.add(newBaoGom);
//                        }
//                    }
//                }
//            }
//             newDonMonAn.setListBaoGom(newListBaoGom);
//
//
//
//                    return donMonAnRepository.save(newDonMonAn);
//             }


}
