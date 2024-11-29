package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.dto.request.ThongTinRequest;
import restaurant.example.restaurant.dto.response.ApiResponse;
import restaurant.example.restaurant.entity.ThongTin;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.ThongTinMapper;
import restaurant.example.restaurant.repository.ThongTinRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongTinService {
             ThongTinRepository thongTinRepository;
             ThongTinMapper thongTinMapper;
             PasswordEncoder passwordEncoder;
             public ThongTin createThongTin (ThongTin thongTin) {
                 Optional<ThongTin> existThongTin = thongTinRepository.findByTenDangNhap(thongTin.getTenDangNhap());
                 if(!existThongTin.isPresent() ) {
                     ThongTin newThongTin = thongTinMapper.toThongTin(thongTin);
//                   newThongTin.setCccd(thongTin.getCccd());
                     newThongTin.setMatKhau(passwordEncoder.encode(thongTin.getMatKhau()));
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



}
