package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.DonGiaoHang;
import restaurant.example.restaurant.service.DonGiaoHangService;

import java.util.List;

@RestController
@RequestMapping("/dongiaohang")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonGiaoHangController {
      DonGiaoHangService donGiaoHangService;
      @PostMapping("/create")
      public DonGiaoHang createDonGiaoHang (@RequestBody DonGiaoHang dongiaohang) {
            return donGiaoHangService.createDonGiaoHang(dongiaohang);
     }
     @GetMapping("/{maDon}")
     public DonGiaoHang getSpecificDonGiaoHang  (@PathVariable String maDon) {

         return donGiaoHangService.getSpecificDonGiaoHang(maDon);

     }
     @GetMapping("/all")
     public List<DonGiaoHang> getAllDonGiaoHang (){
          return donGiaoHangService.getAllDonGiaoHang();
     }
     @PutMapping("/update/{maDon}")
     public String updateDonGiaoHang (@PathVariable String maDon,@RequestBody DonGiaoHang donGiaoHang) {
         return donGiaoHangService.updateDonGiaoHang(maDon,donGiaoHang);

     }
     @DeleteMapping("/delete/{maDon}")
     public String deleteDonGiaoHang (@PathVariable String maDon) {
            return donGiaoHangService.deleteDonGiaoHang(maDon);
     }
}
