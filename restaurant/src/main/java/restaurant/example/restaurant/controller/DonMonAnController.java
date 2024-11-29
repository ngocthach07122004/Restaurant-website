package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.dto.response.MonAnThuocDonMonAnResponse;
import restaurant.example.restaurant.entity.DonMonAn;
import restaurant.example.restaurant.entity.DonMonAnBaoGomMonAn;
import restaurant.example.restaurant.service.DonMonAnBaoGomMonAnService;
import restaurant.example.restaurant.service.DonMonAnService;

import java.util.List;

@RestController
@RequestMapping("/donMonAn")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonMonAnController {
      DonMonAnService donMonAnService;
    DonMonAnBaoGomMonAnService donMonAnBaoGomMonAnService;
      @PostMapping("/create")
      public DonMonAn createDonMonAn (@RequestBody DonMonAn donMonAn) {
            return donMonAnService.createDonMonAn(donMonAn);
     }
     @GetMapping("/{maDon}")
     public DonMonAn getSpecificDonMonAn  (@PathVariable String maDon) {

         return donMonAnService.getSpecificDonMonAn(maDon);

     }
     @GetMapping("/all")
     public List<DonMonAn> getAllDonMonAn (){
          return donMonAnService.getAllDonMonAn();
     }
     @PutMapping("/update/{maDon}")
     public String updateDonMonAn (@PathVariable String maDon,@RequestBody DonMonAn donMonAn) {
         return donMonAnService.updateDonMonAn(maDon,donMonAn);

     }
     @DeleteMapping("/delete/{maDon}")
     public String deleteDonMonAn (@PathVariable String maDon) {
            return donMonAnService.deleteDonMonAn(maDon);
     }

    @PostMapping("/create/danhSachMonAn")
    public DonMonAnBaoGomMonAn createDanhSachMonAn (@RequestBody DonMonAnBaoGomMonAn donMonAnBaoGomMonAn ) {
        return donMonAnBaoGomMonAnService.createDonMonAnBaoGomMonAn(donMonAnBaoGomMonAn);
    }
    @GetMapping("/all/danhSachMonAn")
    List<DonMonAnBaoGomMonAn> getAllDonMonAnBaoGomMonAn (){
        return donMonAnBaoGomMonAnService.getAllDonMonAnBaoGomMonAn();
    }
    @GetMapping("/all/danhSachMonAn/{maDon}")
    List<MonAnThuocDonMonAnResponse> getAllMonAnThuocDonMonAn (@PathVariable String maDon) {
           return donMonAnService.getAllMonAnThuocDonMonAn(maDon);
    }
}
