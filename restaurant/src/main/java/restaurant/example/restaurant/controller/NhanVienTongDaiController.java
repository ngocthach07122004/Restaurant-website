package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.NhanVienTongDai;
import restaurant.example.restaurant.service.NhanVienTongDaiService;

import java.util.List;

@RestController
@RequestMapping("/nhanvientongdai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienTongDaiController {
      NhanVienTongDaiService nhanVienTongDaiService;
      @PostMapping("/create")
      public NhanVienTongDai createNhanVienTongDai (@RequestBody NhanVienTongDai nhanvientongdai) {
            return nhanVienTongDaiService.createNhanVienTongDai(nhanvientongdai);
     }
     @GetMapping("/{cccd}")
     public NhanVienTongDai getSpecificNhanVienTongDai  (@PathVariable String cccd) {

         return nhanVienTongDaiService.getSpecificNhanVienTongDai(cccd);

     }
     @GetMapping("/all")
     public List<NhanVienTongDai> getAllNhanVienTongDai (){
          return nhanVienTongDaiService.getAllNhanVienTongDai();
     }
     @PutMapping("/update/{cccd}")
     public String updateNhanVienTongDai (@PathVariable String cccd,@RequestBody NhanVienTongDai nhanVienTongDai) {
         return nhanVienTongDaiService.updateNhanVienTongDai(cccd,nhanVienTongDai);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteNhanVienTongDai (@PathVariable String cccd) {
            return nhanVienTongDaiService.deleteNhanVienTongDai(cccd);
     }
}
