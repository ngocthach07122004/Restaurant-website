package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.NhanVienPhucVu;
import restaurant.example.restaurant.service.NhanVienPhucVuService;

import java.util.List;

@RestController
@RequestMapping("/nhanvienphucvu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienPhucVuController {
      NhanVienPhucVuService nhanVienPhucVuService;
      @PostMapping("/create")
      public NhanVienPhucVu createNhanVienPhucVu (@RequestBody NhanVienPhucVu nhanvienphucvu) {
            return nhanVienPhucVuService.createNhanVienPhucVu(nhanvienphucvu);
     }
     @GetMapping("/{maNhanVienPhucVu}")
     public NhanVienPhucVu getSpecificNhanVienPhucVu  (@PathVariable String maNhanVienPhucVu) {

         return nhanVienPhucVuService.getSpecificNhanVienPhucVu(maNhanVienPhucVu);

     }
     @GetMapping("/all")
     public List<NhanVienPhucVu> getAllNhanVienPhucVu (){
          return nhanVienPhucVuService.getAllNhanVienPhucVu();
     }
     @PutMapping("/update/{maNhanVienPhucVu}")
     public String updateNhanVienPhucVu (@PathVariable String maNhanVienPhucVu,@RequestBody NhanVienPhucVu nhanVienPhucVu) {
         return nhanVienPhucVuService.updateNhanVienPhucVu(maNhanVienPhucVu,nhanVienPhucVu);

     }
     @DeleteMapping("/delete/{maNhanVienPhucVu}")
     public String deleteNhanVienPhucVu (@PathVariable String maNhanVienPhucVu) {
            return nhanVienPhucVuService.deleteNhanVienPhucVu(maNhanVienPhucVu);
     }
}
