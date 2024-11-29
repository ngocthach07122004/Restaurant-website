package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.NhanVienGiaoHang;
import restaurant.example.restaurant.service.NhanVienGiaoHangService;

import java.util.List;

@RestController
@RequestMapping("/nhanviengiaohang")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienGiaoHangController {
      NhanVienGiaoHangService nhanVienGiaoHangService;
      @PostMapping("/create")
      public NhanVienGiaoHang createNhanVienGiaoHang (@RequestBody NhanVienGiaoHang nhanviengiaohang) {
            return nhanVienGiaoHangService.createNhanVienGiaoHang(nhanviengiaohang);
     }
     @GetMapping("/{cccd}")
     public NhanVienGiaoHang getSpecificNhanVienGiaoHang  (@PathVariable String cccd) {

         return nhanVienGiaoHangService.getSpecificNhanVienGiaoHang(cccd);

     }
     @GetMapping("/all")
     public List<NhanVienGiaoHang> getAllNhanVienGiaoHang (){
          return nhanVienGiaoHangService.getAllNhanVienGiaoHang();
     }
     @PutMapping("/update/{cccd}")
     public String updateNhanVienGiaoHang (@PathVariable String cccd,@RequestBody NhanVienGiaoHang nhanVienGiaoHang) {
         return nhanVienGiaoHangService.updateNhanVienGiaoHang(cccd,nhanVienGiaoHang);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteNhanVienGiaoHang (@PathVariable String cccd) {
            return nhanVienGiaoHangService.deleteNhanVienGiaoHang(cccd);
     }
}
