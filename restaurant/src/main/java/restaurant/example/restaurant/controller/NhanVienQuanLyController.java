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
     @GetMapping("/{cccd}")
     public NhanVienQuanLy getSpecificNhanVienQuanLy  (@PathVariable String cccd) {

         return nhanVienQuanLyService.getSpecificNhanVienQuanLy(cccd);

     }
     @GetMapping("/all")
     public List<NhanVienQuanLy> getAllNhanVienQuanLy (){
          return nhanVienQuanLyService.getAllNhanVienQuanLy();
     }
     @PutMapping("/update/{cccd}")
     public String updateNhanVienQuanLy (@PathVariable String cccd,@RequestBody NhanVienQuanLy nhanVienQuanLy) {
         return nhanVienQuanLyService.updateNhanVienQuanLy(cccd,nhanVienQuanLy);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteNhanVienQuanLy (@PathVariable String cccd) {
            return nhanVienQuanLyService.deleteNhanVienQuanLy(cccd);
     }
}
