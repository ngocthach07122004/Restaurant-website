package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.NhanVienQuanLy;
import restaurant.example.restaurant.service.NhanVienQuanLyService;

import java.util.List;

@RestController
@RequestMapping("/nhanvienquanly")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienQuanLyController {
      NhanVienQuanLyService nhanVienQuanLyService;
      @PostMapping("/create")
      public NhanVienQuanLy createNhanVienQuanLy (@RequestBody NhanVienQuanLy nhanvienquanly) {
            return nhanVienQuanLyService.createNhanVienQuanLy(nhanvienquanly);
     }
     @GetMapping("/{maNhanVienQuanLy}")
     public NhanVienQuanLy getSpecificNhanVienQuanLy  (@PathVariable String maNhanVienQuanLy) {

         return nhanVienQuanLyService.getSpecificNhanVienQuanLy(maNhanVienQuanLy);

     }
     @GetMapping("/all")
     public List<NhanVienQuanLy> getAllNhanVienQuanLy (){
          return nhanVienQuanLyService.getAllNhanVienQuanLy();
     }
     @PutMapping("/update/{maNhanVienQuanLy}")
     public String updateNhanVienQuanLy (@PathVariable String maNhanVienQuanLy,@RequestBody NhanVienQuanLy nhanVienQuanLy) {
         return nhanVienQuanLyService.updateNhanVienQuanLy(maNhanVienQuanLy,nhanVienQuanLy);

     }
     @DeleteMapping("/delete/{maNhanVienQuanLy}")
     public String deleteNhanVienQuanLy (@PathVariable String maNhanVienQuanLy) {
            return nhanVienQuanLyService.deleteNhanVienQuanLy(maNhanVienQuanLy);
     }
}
