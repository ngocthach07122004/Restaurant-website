package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.ThongBao;
import restaurant.example.restaurant.service.ThongBaoService;

import java.util.List;

@RestController
@RequestMapping("/thongBao")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongBaoController {
      ThongBaoService thongBaoService;
      @PostMapping("/create")
      public ThongBao createThongBao (@RequestBody ThongBao thongBao) {
            return thongBaoService.createThongBao(thongBao);
     }
     @GetMapping("/{maThongBao}")
     public ThongBao getSpecificThongBao  (@PathVariable String maThongBao) {

         return thongBaoService.getSpecificThongBao(maThongBao);

     }
     @GetMapping("/all")
     public List<ThongBao> getAllThongBao (){
          return thongBaoService.getAllThongBao();
     }
     @PutMapping("/update/{maThongBao}")
     public String updateThongBao (@PathVariable String maThongBao,@RequestBody ThongBao thongBao) {
         return thongBaoService.updateThongBao(maThongBao,thongBao);

     }
     @DeleteMapping("/delete/{maThongBao}")
     public String deleteThongBao (@PathVariable String maThongBao) {
            return thongBaoService.deleteThongBao(maThongBao);
     }
}
