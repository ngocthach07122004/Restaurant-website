package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.NhanVienThuNgan;
import restaurant.example.restaurant.service.NhanVienThuNganService;

import java.util.List;

@RestController
@RequestMapping("/nhanvienthungan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienThuNganController {
      NhanVienThuNganService nhanVienThuNganService;
      @PostMapping("/create")
      public NhanVienThuNgan createNhanVienThuNgan (@RequestBody NhanVienThuNgan nhanvienthungan) {
            return nhanVienThuNganService.createNhanVienThuNgan(nhanvienthungan);
     }
     @GetMapping("/{maNhanVienThuNgan}")
     public NhanVienThuNgan getSpecificNhanVienThuNgan  (@PathVariable String maNhanVienThuNgan) {

         return nhanVienThuNganService.getSpecificNhanVienThuNgan(maNhanVienThuNgan);

     }
     @GetMapping("/all")
     public List<NhanVienThuNgan> getAllNhanVienThuNgan (){
          return nhanVienThuNganService.getAllNhanVienThuNgan();
     }
     @PutMapping("/update/{maNhanVienThuNgan}")
     public String updateNhanVienThuNgan (@PathVariable String maNhanVienThuNgan,@RequestBody NhanVienThuNgan nhanVienThuNgan) {
         return nhanVienThuNganService.updateNhanVienThuNgan(maNhanVienThuNgan,nhanVienThuNgan);

     }
     @DeleteMapping("/delete/{maNhanVienThuNgan}")
     public String deleteNhanVienThuNgan (@PathVariable String maNhanVienThuNgan) {
            return nhanVienThuNganService.deleteNhanVienThuNgan(maNhanVienThuNgan);
     }
}
