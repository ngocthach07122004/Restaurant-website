package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.NhanVien;
import restaurant.example.restaurant.service.NhanVienService;

import java.util.List;

@RestController
@RequestMapping("/nhanvien")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienController {
      NhanVienService nhanVienService;
      @PostMapping("/create")
      public NhanVien createNhanVien (@RequestBody NhanVien nhanvien) {
            return nhanVienService.createNhanVien(nhanvien);
     }
     @GetMapping("/{maNhanVien}")
     public NhanVien getSpecificNhanVien  (@PathVariable String maNhanVien) {

         return nhanVienService.getSpecificNhanVien(maNhanVien);

     }
     @GetMapping("/all")
     public List<NhanVien> getAllNhanVien (){
          return nhanVienService.getAllNhanVien();
     }
     @PutMapping("/update/{maNhanVien}")
     public String updateNhanVien (@PathVariable String maNhanVien,@RequestBody NhanVien nhanVien) {
         return nhanVienService.updateNhanVien(maNhanVien,nhanVien);

     }
     @DeleteMapping("/delete/{maNhanVien}")
     public String deleteNhanVien (@PathVariable String maNhanVien) {
            return nhanVienService.deleteNhanVien(maNhanVien);
     }
}
