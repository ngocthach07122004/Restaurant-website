package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.service.ChiNhanhService;

import java.util.List;

@RestController
@RequestMapping("/chinhanh")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChiNhanhController {
      ChiNhanhService chiNhanhService;
      @PostMapping("/create")
      public ChiNhanh createChiNhanh (@RequestBody ChiNhanh chinhanh) {
            return chiNhanhService.createChiNhanh(chinhanh);
     }
     @GetMapping("/{maChiNhanh}")
     public ChiNhanh getSpecificChiNhanh  (@PathVariable String maChiNhanh) {

         return chiNhanhService.getSpecificChiNhanh(maChiNhanh);

     }
     @GetMapping("/all")
     public List<ChiNhanh> getAllChiNhanh (){
          return chiNhanhService.getAllChiNhanh();
     }
     @PutMapping("/update/{maChiNhanh}")
     public String updateChiNhanh (@PathVariable String maChiNhanh,@RequestBody ChiNhanh chiNhanh) {
         return chiNhanhService.updateChiNhanh(maChiNhanh,chiNhanh);

     }
     @DeleteMapping("/delete/{maChiNhanh}")
     public String deleteChiNhanh (@PathVariable String maChiNhanh) {
            return chiNhanhService.deleteChiNhanh(maChiNhanh);
     }
}
