package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.ThongBao;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.ThongBaoMapper;
import restaurant.example.restaurant.repository.ThongBaoRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongBaoService {
             ThongBaoRepository thongBaoRepository;
             ThongBaoMapper thongBaoMapper;
             public ThongBao createThongBao (ThongBao thongBao) {
                   ThongBao newThongBao = thongBaoMapper.toThongBao(thongBao);
                   newThongBao.setMaThongBao(thongBao.getMaThongBao());
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
                  return "update success";

             }
             public String deleteThongBao (String maThongBao) {
                    thongBaoRepository.deleteById(maThongBao);
                    return "delete success";
             }


}
