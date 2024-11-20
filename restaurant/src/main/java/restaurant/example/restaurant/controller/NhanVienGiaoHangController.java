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
     @GetMapping("/{maNhanVienGiaoHang}")
     public NhanVienGiaoHang getSpecificNhanVienGiaoHang  (@PathVariable String maNhanVienGiaoHang) {

         return nhanVienGiaoHangService.getSpecificNhanVienGiaoHang(maNhanVienGiaoHang);

     }
     @GetMapping("/all")
     public List<NhanVienGiaoHang> getAllNhanVienGiaoHang (){
          return nhanVienGiaoHangService.getAllNhanVienGiaoHang();
     }
     @PutMapping("/update/{maNhanVienGiaoHang}")
     public String updateNhanVienGiaoHang (@PathVariable String maNhanVienGiaoHang,@RequestBody NhanVienGiaoHang nhanVienGiaoHang) {
         return nhanVienGiaoHangService.updateNhanVienGiaoHang(maNhanVienGiaoHang,nhanVienGiaoHang);

     }
     @DeleteMapping("/delete/{maNhanVienGiaoHang}")
     public String deleteNhanVienGiaoHang (@PathVariable String maNhanVienGiaoHang) {
            return nhanVienGiaoHangService.deleteNhanVienGiaoHang(maNhanVienGiaoHang);
     }
}
