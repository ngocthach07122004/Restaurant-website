package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.PhuongTien;
import restaurant.example.restaurant.service.PhuongTienService;

import java.util.List;

@RestController
@RequestMapping("/phuongTien")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhuongTienController {
      PhuongTienService phuongTienService;
      @PostMapping("/create")
      public PhuongTien createPhuongTien (@RequestBody PhuongTien phuongTien) {
            return phuongTienService.createPhuongTien(phuongTien);
     }
     @GetMapping("/{bienSoXe}")
     public PhuongTien getSpecificPhuongTien  (@PathVariable String bienSoXe) {

         return phuongTienService.getSpecificPhuongTien(bienSoXe);

     }
     @GetMapping("/all")
     public List<PhuongTien> getAllPhuongTien (){
          return phuongTienService.getAllPhuongTien();
     }
     @PutMapping("/update/{bienSoXe}")
     public String updatePhuongTien(@PathVariable String bienSoXe, @RequestBody PhuongTien phuongTien) {
         return phuongTienService.updatePhuongTien(bienSoXe,phuongTien);

     }
     @DeleteMapping("/delete/{bienSoXe}")
     public String deletePhuongTien (@PathVariable String bienSoXe) {
            return phuongTienService.deletePhuongTien(bienSoXe);
     }
}
