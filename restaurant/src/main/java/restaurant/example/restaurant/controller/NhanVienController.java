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
     @GetMapping("/{cccd}")
     public NhanVien getSpecificNhanVien  (@PathVariable String cccd) {

         return nhanVienService.getSpecificNhanVien(cccd);

     }
     @GetMapping("/all")
     public List<NhanVien> getAllNhanVien (){
          return nhanVienService.getAllNhanVien();
     }
     @PutMapping("/update/{cccd}")
     public String updateNhanVien (@PathVariable String cccd,@RequestBody NhanVien nhanVien) {
         return nhanVienService.updateNhanVien(cccd,nhanVien);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteNhanVien (@PathVariable String cccd) {
            return nhanVienService.deleteNhanVien(cccd);
     }
}
