package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.KhachHang;
import restaurant.example.restaurant.service.KhachHangService;

import java.util.List;

@RestController
@RequestMapping("/khachhang")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangController {
      KhachHangService khachHangService;
      @PostMapping("/create")
      public KhachHang createKhachHang (@RequestBody KhachHang khachhang) {
            return khachHangService.createKhachHang(khachhang);
     }
     @GetMapping("/{maKhachHang}")
     public KhachHang getSpecificKhachHang  (@PathVariable String maKhachHang) {

         return khachHangService.getSpecificKhachHang(maKhachHang);

     }
     @GetMapping("/all")
     public List<KhachHang> getAllKhachHang (){
          return khachHangService.getAllKhachHang();
     }
     @PutMapping("/update/{maKhachHang}")
     public String updateKhachHang (@PathVariable String maKhachHang,@RequestBody KhachHang khachHang) {
         return khachHangService.updateKhachHang(maKhachHang,khachHang);

     }
     @DeleteMapping("/delete/{maKhachHang}")
     public String deleteKhachHang (@PathVariable String maKhachHang) {
            return khachHangService.deleteKhachHang(maKhachHang);
     }
}
