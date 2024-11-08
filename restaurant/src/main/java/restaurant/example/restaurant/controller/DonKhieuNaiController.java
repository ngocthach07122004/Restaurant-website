package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.DonKhieuNai;
import restaurant.example.restaurant.service.DonKhieuNaiService;

import java.util.List;

@RestController
@RequestMapping("/donKhieuNai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonKhieuNaiController {
      DonKhieuNaiService donKhieuNaiService;
      @PostMapping("/create")
      public DonKhieuNai createDonKhieuNai (@RequestBody DonKhieuNai donKhieuNai) {
            return donKhieuNaiService.createDonKhieuNai(donKhieuNai);
     }
     @GetMapping("/{maDonKhieuNai}")
     public DonKhieuNai getSpecificDonKhieuNai  (@PathVariable String maDonKhieuNai) {

         return donKhieuNaiService.getSpecificDonKhieuNai(maDonKhieuNai);

     }
     @GetMapping("/all")
     public List<DonKhieuNai> getAllDonKhieuNai (){
          return donKhieuNaiService.getAllDonKhieuNai();
     }
     @PutMapping("/update/{maDonKhieuNai}")
     public String updateDonKhieuNai (@PathVariable String maDonKhieuNai,@RequestBody DonKhieuNai donKhieuNai) {
         return donKhieuNaiService.updateDonKhieuNai(maDonKhieuNai,donKhieuNai);

     }
     @DeleteMapping("/delete/{maDonKhieuNai}")
     public String deleteDonKhieuNai (@PathVariable String maDonKhieuNai) {
            return donKhieuNaiService.deleteDonKhieuNai(maDonKhieuNai);
     }
}
