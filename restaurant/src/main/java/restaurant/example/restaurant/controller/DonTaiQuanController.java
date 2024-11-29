package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.DonTaiQuan;
import restaurant.example.restaurant.service.DonTaiQuanService;

import java.util.List;

@RestController
@RequestMapping("/dontaiquan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonTaiQuanController {
      DonTaiQuanService donTaiQuanService;
      @PostMapping("/create")
      public DonTaiQuan createDonTaiQuan (@RequestBody DonTaiQuan dontaiquan) {
            return donTaiQuanService.createDonTaiQuan(dontaiquan);
     }
     @GetMapping("/{maDon}")
     public DonTaiQuan getSpecificDonTaiQuan  (@PathVariable String maDon) {

         return donTaiQuanService.getSpecificDonTaiQuan(maDon);

     }
     @GetMapping("/all")
     public List<DonTaiQuan> getAllDonTaiQuan (){
          return donTaiQuanService.getAllDonTaiQuan();
     }
     @PutMapping("/update/{maDon}")
     public String updateDonTaiQuan (@PathVariable String maDon,@RequestBody DonTaiQuan donTaiQuan) {
         return donTaiQuanService.updateDonTaiQuan(maDon,donTaiQuan);

     }
     @DeleteMapping("/delete/{maDon}")
     public String deleteDonTaiQuan (@PathVariable String maDon) {
            return donTaiQuanService.deleteDonTaiQuan(maDon);
     }
}
