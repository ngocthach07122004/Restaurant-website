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
     @GetMapping("/{cccd}")
     public KhachHang getSpecificKhachHang  (@PathVariable String cccd) {

         return khachHangService.getSpecificKhachHang(cccd);

     }
     @GetMapping("/all")
     public List<KhachHang> getAllKhachHang (){
          return khachHangService.getAllKhachHang();
     }
     @PutMapping("/update/{cccd}")
     public String updateKhachHang (@PathVariable String cccd,@RequestBody KhachHang khachHang) {
         return khachHangService.updateKhachHang(cccd,khachHang);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteKhachHang (@PathVariable String cccd) {
            return khachHangService.deleteKhachHang(cccd);
     }
}
