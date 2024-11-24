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
     @GetMapping("/{maNhanVienTongDai}")
     public NhanVienTongDai getSpecificNhanVienTongDai  (@PathVariable String maNhanVienTongDai) {

         return nhanVienTongDaiService.getSpecificNhanVienTongDai(maNhanVienTongDai);

     }
     @GetMapping("/all")
     public List<NhanVienTongDai> getAllNhanVienTongDai (){
          return nhanVienTongDaiService.getAllNhanVienTongDai();
     }
     @PutMapping("/update/{maNhanVienTongDai}")
     public String updateNhanVienTongDai (@PathVariable String maNhanVienTongDai,@RequestBody NhanVienTongDai nhanVienTongDai) {
         return nhanVienTongDaiService.updateNhanVienTongDai(maNhanVienTongDai,nhanVienTongDai);

     }
     @DeleteMapping("/delete/{maNhanVienTongDai}")
     public String deleteNhanVienTongDai (@PathVariable String maNhanVienTongDai) {
            return nhanVienTongDaiService.deleteNhanVienTongDai(maNhanVienTongDai);
     }
}
