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
     @GetMapping("/{maDonGiaoHang}")
     public DonGiaoHang getSpecificDonGiaoHang  (@PathVariable String maDonGiaoHang) {

         return donGiaoHangService.getSpecificDonGiaoHang(maDonGiaoHang);

     }
     @GetMapping("/all")
     public List<DonGiaoHang> getAllDonGiaoHang (){
          return donGiaoHangService.getAllDonGiaoHang();
     }
     @PutMapping("/update/{maDonGiaoHang}")
     public String updateDonGiaoHang (@PathVariable String maDonGiaoHang,@RequestBody DonGiaoHang donGiaoHang) {
         return donGiaoHangService.updateDonGiaoHang(maDonGiaoHang,donGiaoHang);

     }
     @DeleteMapping("/delete/{maDonGiaoHang}")
     public String deleteDonGiaoHang (@PathVariable String maDonGiaoHang) {
            return donGiaoHangService.deleteDonGiaoHang(maDonGiaoHang);
     }
}
