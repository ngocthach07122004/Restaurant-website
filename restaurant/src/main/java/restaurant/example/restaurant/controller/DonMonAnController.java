package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.DonMonAn;
import restaurant.example.restaurant.service.DonMonAnService;

import java.util.List;

@RestController
@RequestMapping("/donMonAn")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonMonAnController {
      DonMonAnService donMonAnService;
      @PostMapping("/create")
      public DonMonAn createDonMonAn (@RequestBody DonMonAn donMonAn) {
            return donMonAnService.createDonMonAn(donMonAn);
     }
     @GetMapping("/{maDon}")
     public DonMonAn getSpecificDonMonAn  (@PathVariable String maDonMonAn) {

         return donMonAnService.getSpecificDonMonAn(maDonMonAn);

     }
     @GetMapping("/all")
     public List<DonMonAn> getAllDonMonAn (){
          return donMonAnService.getAllDonMonAn();
     }
     @PutMapping("/update/{maDon}")
     public String updateDonMonAn (@PathVariable String maDonMonAn,@RequestBody DonMonAn donMonAn) {
         return donMonAnService.updateDonMonAn(maDonMonAn,donMonAn);

     }
     @DeleteMapping("/delete/{maDon}")
     public String deleteDonMonAn (@PathVariable String maDonMonAn) {
            return donMonAnService.deleteDonMonAn(maDonMonAn);
     }
}
