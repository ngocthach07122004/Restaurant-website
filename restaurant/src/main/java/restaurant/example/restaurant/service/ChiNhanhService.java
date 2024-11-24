package restaurant.example.restaurant.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.ChiNhanhMapper;
import restaurant.example.restaurant.repository.ChiNhanhRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChiNhanhService {
             ChiNhanhRepository chiNhanhRepository;
             ChiNhanhMapper chiNhanhMapper;
             public ChiNhanh createChiNhanh (ChiNhanh chinhanh) {
                   ChiNhanh newChiNhanh = chiNhanhMapper.toChiNhanh(chinhanh);
                   newChiNhanh.setMaChiNhanh(chinhanh.getMaChiNhanh());
                    return chiNhanhRepository.save(newChiNhanh);
             }
             public ChiNhanh getSpecificChiNhanh (String maChiNhanh) {

                 return chiNhanhRepository.findById(maChiNhanh)
                         .orElseThrow(()-> new AppException(ErrorCode.CHINHANH_NOT_EXIST));

             }
             public List<ChiNhanh> getAllChiNhanh (){
                  return chiNhanhRepository.findAll();
             }

             public String updateChiNhanh (String maChiNhanh, ChiNhanh chiNhanh) {
                  ChiNhanh chiNhanhUpdate =getSpecificChiNhanh(maChiNhanh);
                  chiNhanhMapper.updateChiNhanh(chiNhanhUpdate, chiNhanh);
                   chiNhanhRepository.save(chiNhanhUpdate);
                  return "update success";

             }
             public String deleteChiNhanh (String maChiNhanh) {
                    chiNhanhRepository.deleteById(maChiNhanh);
                    return "delete success";
             }


}
