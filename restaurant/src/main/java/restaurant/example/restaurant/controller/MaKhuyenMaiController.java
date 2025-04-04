package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.MaKhuyenMai;
import restaurant.example.restaurant.service.MaKhuyenMaiService;

import java.util.List;

@RestController
@RequestMapping("/maKhuyenMai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaKhuyenMaiController {
      MaKhuyenMaiService maKhuyenMaiService;
      @PostMapping("/create")
      public MaKhuyenMai createMaKhuyenMai (@RequestBody MaKhuyenMai maKhuyenMai) {
            return maKhuyenMaiService.createMaKhuyenMai(maKhuyenMai);
     }
     @GetMapping("/{idKhuyenMai}")
     public MaKhuyenMai getSpecificMaKhuyenMai  (@PathVariable String idKhuyenMai) {

         return maKhuyenMaiService.getSpecificMaKhuyenMai(idKhuyenMai);

     }
     @GetMapping("/all")
     public List<MaKhuyenMai> getAllMaKhuyenMai (){
          return maKhuyenMaiService.getAllMaKhuyenMai();
     }
     @PutMapping("/update/{idKhuyenMai}")
     public String updateMaKhuyenMai (@PathVariable String idKhuyenMai,@RequestBody MaKhuyenMai maKhuyenMai) {
         return maKhuyenMaiService.updateMaKhuyenMai(idKhuyenMai,maKhuyenMai);

     }
     @DeleteMapping("/delete/{idKhuyenMai}")
     public String deleteMaKhuyenMai (@PathVariable String idKhuyenMai) {
            return maKhuyenMaiService.deleteMaKhuyenMai(idKhuyenMai);
     }
}
