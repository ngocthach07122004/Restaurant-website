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
     @GetMapping("/{maDonTaiQuan}")
     public DonTaiQuan getSpecificDonTaiQuan  (@PathVariable String maDonTaiQuan) {

         return donTaiQuanService.getSpecificDonTaiQuan(maDonTaiQuan);

     }
     @GetMapping("/all")
     public List<DonTaiQuan> getAllDonTaiQuan (){
          return donTaiQuanService.getAllDonTaiQuan();
     }
     @PutMapping("/update/{maDonTaiQuan}")
     public String updateDonTaiQuan (@PathVariable String maDonTaiQuan,@RequestBody DonTaiQuan donTaiQuan) {
         return donTaiQuanService.updateDonTaiQuan(maDonTaiQuan,donTaiQuan);

     }
     @DeleteMapping("/delete/{maDonTaiQuan}")
     public String deleteDonTaiQuan (@PathVariable String maDonTaiQuan) {
            return donTaiQuanService.deleteDonTaiQuan(maDonTaiQuan);
     }
}
