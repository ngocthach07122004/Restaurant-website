package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.QuanTriVien;
import restaurant.example.restaurant.service.QuanTriVienService;

import java.util.List;

@RestController
@RequestMapping("/quantrivien")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuanTriVienController {
      QuanTriVienService quanTriVienService;
      @PostMapping("/create")
      public QuanTriVien createQuanTriVien (@RequestBody QuanTriVien quantrivien) {
            return quanTriVienService.createQuanTriVien(quantrivien);
     }
     @GetMapping("/{maQuanTriVien}")
     public QuanTriVien getSpecificQuanTriVien  (@PathVariable String maQuanTriVien) {

         return quanTriVienService.getSpecificQuanTriVien(maQuanTriVien);

     }
     @GetMapping("/all")
     public List<QuanTriVien> getAllQuanTriVien (){
          return quanTriVienService.getAllQuanTriVien();
     }
     @PutMapping("/update/{maQuanTriVien}")
     public String updateQuanTriVien (@PathVariable String maQuanTriVien,@RequestBody QuanTriVien quanTriVien) {
         return quanTriVienService.updateQuanTriVien(maQuanTriVien,quanTriVien);

     }
     @DeleteMapping("/delete/{maQuanTriVien}")
     public String deleteQuanTriVien (@PathVariable String maQuanTriVien) {
            return quanTriVienService.deleteQuanTriVien(maQuanTriVien);
     }
}
