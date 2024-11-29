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
     @GetMapping("/{cccd}")
     public NhanVienThuNgan getSpecificNhanVienThuNgan  (@PathVariable String cccd) {

         return nhanVienThuNganService.getSpecificNhanVienThuNgan(cccd);

     }
     @GetMapping("/all")
     public List<NhanVienThuNgan> getAllNhanVienThuNgan (){
          return nhanVienThuNganService.getAllNhanVienThuNgan();
     }
     @PutMapping("/update/{cccd}")
     public String updateNhanVienThuNgan (@PathVariable String cccd,@RequestBody NhanVienThuNgan nhanVienThuNgan) {
         return nhanVienThuNganService.updateNhanVienThuNgan(cccd,nhanVienThuNgan);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteNhanVienThuNgan (@PathVariable String cccd) {
            return nhanVienThuNganService.deleteNhanVienThuNgan(cccd);
     }
}
