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
     @GetMapping("/{cccd}")
     public QuanTriVien getSpecificQuanTriVien  (@PathVariable String cccd) {

         return quanTriVienService.getSpecificQuanTriVien(cccd);

     }
     @GetMapping("/all")
     public List<QuanTriVien> getAllQuanTriVien (){
          return quanTriVienService.getAllQuanTriVien();
     }
     @PutMapping("/update/{cccd}")
     public String updateQuanTriVien (@PathVariable String cccd,@RequestBody QuanTriVien quanTriVien) {
         return quanTriVienService.updateQuanTriVien(cccd,quanTriVien);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteQuanTriVien (@PathVariable String cccd) {
            return quanTriVienService.deleteQuanTriVien(cccd);
     }
}
