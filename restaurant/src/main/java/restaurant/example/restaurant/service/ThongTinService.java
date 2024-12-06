package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.dto.request.ThongTinRequest;
import restaurant.example.restaurant.dto.response.ApiResponse;
import restaurant.example.restaurant.entity.*;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.helper.EntityHelper;
import restaurant.example.restaurant.mapper.ThongTinMapper;
import restaurant.example.restaurant.repository.ThongBaoRepository;
import restaurant.example.restaurant.repository.ThongTinRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongTinService {
    SimpMessagingTemplate messagingTemplate;
    ThongBaoRepository thongBaoRepository;
    ThongBaoService thongBaoService;


             ThongTinRepository thongTinRepository;
             ThongTinMapper thongTinMapper;
             PasswordEncoder passwordEncoder;
             EntityHelper entityHelper;
    @PersistenceContext
    EntityManager entityManager;

             @Transactional
             public ThongTin createThongTin (ThongTin thongTin) {
                 Optional<ThongTin> existThongTin = thongTinRepository.findByTenDangNhap(thongTin.getTenDangNhap());
                 if(!existThongTin.isPresent() ) {
                     ThongTin newThongTin = thongTinMapper.toThongTin(thongTin);
//                   newThongTin.setCccd(thongTin.getCccd());
                     newThongTin.setMatKhau(passwordEncoder.encode(thongTin.getMatKhau()));

                     if (thongTin.getCccdQuanTriVien() != null) {
                         QuanTriVien quanTriVien = entityHelper.findOrMerge_MTO(
                                 entityManager,
                                 thongTin.getCccdQuanTriVien(),
                                 QuanTriVien.class,
                                 thongTin.getCccdQuanTriVien().getCccd(),
                                 "Cccd Quan Tri Vien with ID "
                         );
                         newThongTin.setCccdQuanTriVien(quanTriVien);
                     }
                     if (thongTin.getListThongBao() != null) {
                         List<ThongBao> updateThongBaoList = entityHelper.processEntityList_MTM(
                                 entityManager,
                                 thongTin.getListThongBao(),
                                 ThongBao.class,
                                 ThongBao::getMaThongBao,
                                 "ThongBao with ID"

                         );
                         newThongTin.setListThongBao(updateThongBaoList);
                     }

//                     if (thongTin.getListThongBao() != null) {
//                         List<ThongBao> updatedThongBaoList = entityHelper.updateOrCreateRelatedEntities_OTM(
//                                 newThongTin.getListThongBao(), // Danh sách cần xử lý
//                                 ThongBao::getMaThongBao, // Hàm lấy ID
//                                 id -> entityManager.find(ThongBao.class, id), // Hàm tìm kiếm trong cơ sở dữ liệu
//                                 maThongBao -> maThongBao.setCccdThongTin(newThongTin), // Thiết lập quan hệ ngược
//                                 maThongBao -> entityManager.merge(maThongBao) // Lưu entity mới hoặc cập nhật
//                         );
//
//                         // Gán lại danh sách đã xử lý
//                         thongTin.setListThongBao(updatedThongBaoList);
//                     }



                     return thongTinRepository.save(newThongTin);
                 }
                 else {
                      throw  new AppException(ErrorCode.USERNAME_ALREADY_EXIST);
                 }
             }
             public ThongTin getSpecificThongTin (String maThongTin) {

                 return thongTinRepository.findById(maThongTin)
                         .orElseThrow(()-> new AppException(ErrorCode.THONGTIN_NOT_EXIST));

             }
             public List<ThongTin> getAllThongTin (){
                  return thongTinRepository.findAll();
             }

             public String updateThongTin (String maThongTin, ThongTin thongTin) {
                  ThongTin thongTinUpdate =getSpecificThongTin(maThongTin);
                  thongTinMapper.updateThongTin(thongTinUpdate, thongTin);
                  thongTinRepository.save(thongTinUpdate);
                  return "update success";

             }
             public String deleteThongTin (String maThongTin) {
                    thongTinRepository.deleteById(maThongTin);
                    return "delete success";
             }
             public ApiResponse<ThongTin> authenticateThongTin (ThongTinRequest thongTinRequest){

                 ThongTin thongTinUser = thongTinRepository.findByTenDangNhap(thongTinRequest.getTenDangNhap()).orElseThrow(
                         ()-> new AppException(ErrorCode.USERNAME_NOT_EXIST)
                 );

                 log.info(thongTinUser.toString());

                 boolean authenticate = passwordEncoder.matches(thongTinRequest.getMatKhau(),thongTinUser.getMatKhau());
                 if(authenticate) {
                         return ApiResponse.<ThongTin>builder().code("200").message("success").entity(thongTinUser).build();
                 }
                 else {
                     return ApiResponse.<ThongTin>builder().code("401").message("fail").build();
                 }

             }
    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    @Transactional
    public void consumeNotification(ThongBao thongBao) {
        thongBao.setListThongTin(thongTinRepository.findAll());
//        log.info("THONGBAO {}", thongBao);
        ThongBao responseThongBao =  thongBaoService.createThongBao(thongBao);
//        List<ThongTin> listThongTin = thongTinRepository.findAll();
        // Lấy tất cả người dùng từ database
//        for (ThongTin thongTin : listThongTin) {
//            // Thêm thông báo vào danh sách thông báo của người dùng
////            thongTin.getListThongBao().add(thongBao);
//
//            List<ThongBao> listThongBao =  thongTin.getListThongBao();
//            if(listThongBao == null){
//                 listThongBao = new ArrayList<>();
//            }
//
//            listThongBao.add(responseThongBao);
//
//            log.info("LIST {}",listThongBao);
//
//            thongTin.setListThongBao(listThongBao);
//            log.info("THONGTIN {}",thongTin);
//             thongTinRepository.save(thongTin);
//        }

//        log.info("NOTIFICATION {}", responseThongBao);
        messagingTemplate.convertAndSend("/topic/notifications", thongBao);
    }
    public List<ThongBao> getAllThongBaoOfThongTin (String cccd ){
                  return getSpecificThongTin(cccd).getListThongBao();
    }



}
