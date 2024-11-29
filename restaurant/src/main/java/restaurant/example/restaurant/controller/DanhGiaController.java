package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.DanhGia;
import restaurant.example.restaurant.service.DanhGiaService;

import java.util.List;

@RestController
@RequestMapping("/danhgia")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DanhGiaController {
      DanhGiaService danhGiaService;
      @PostMapping("/create")
      public DanhGia createDanhGia (@RequestBody DanhGia danhgia) {
            return danhGiaService.createDanhGia(danhgia);
     }
     @GetMapping("/{maDanhGia}")
     public DanhGia getSpecificDanhGia  (@PathVariable String maDanhGia) {

         return danhGiaService.getSpecificDanhGia(maDanhGia);

     }
     @GetMapping("/all")
     public List<DanhGia> getAllDanhGia (){
          return danhGiaService.getAllDanhGia();
     }
     @PutMapping("/update/{maDanhGia}")
     public String updateDanhGia (@PathVariable String maDanhGia,@RequestBody DanhGia danhGia) {
         return danhGiaService.updateDanhGia(maDanhGia,danhGia);

     }
     @DeleteMapping("/delete/{maDanhGia}")
     public String deleteDanhGia (@PathVariable String maDanhGia) {
            return danhGiaService.deleteDanhGia(maDanhGia);
     }
}
