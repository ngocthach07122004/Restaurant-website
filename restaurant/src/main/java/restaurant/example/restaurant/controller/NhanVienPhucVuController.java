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
     @GetMapping("/{cccd}")
     public NhanVienPhucVu getSpecificNhanVienPhucVu  (@PathVariable String cccd) {

         return nhanVienPhucVuService.getSpecificNhanVienPhucVu(cccd);

     }
     @GetMapping("/all")
     public List<NhanVienPhucVu> getAllNhanVienPhucVu (){
          return nhanVienPhucVuService.getAllNhanVienPhucVu();
     }
     @PutMapping("/update/{cccd}")
     public String updateNhanVienPhucVu (@PathVariable String cccd,@RequestBody NhanVienPhucVu nhanVienPhucVu) {
         return nhanVienPhucVuService.updateNhanVienPhucVu(cccd,nhanVienPhucVu);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteNhanVienPhucVu (@PathVariable String cccd) {
            return nhanVienPhucVuService.deleteNhanVienPhucVu(cccd);
     }
}
