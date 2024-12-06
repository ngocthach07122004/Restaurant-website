package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.dto.request.ThongTinRequest;
import restaurant.example.restaurant.dto.response.ApiResponse;
import restaurant.example.restaurant.entity.ThongBao;
import restaurant.example.restaurant.entity.ThongTin;
import restaurant.example.restaurant.service.ThongTinService;

import java.util.List;

@RestController
@RequestMapping("/thongTin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongTinController {
      ThongTinService thongTinService;
      @PostMapping("/create")
      public ThongTin createThongTin (@RequestBody ThongTin thongTin) {
            return thongTinService.createThongTin(thongTin);
     }
     @GetMapping("/{cccd}")
     public ThongTin getSpecificThongTin  (@PathVariable String cccd) {

         return thongTinService.getSpecificThongTin(cccd);

     }
     @GetMapping("/all")
     public List<ThongTin> getAllThongTin (){
          return thongTinService.getAllThongTin();
     }
     @PutMapping("/update/{cccd}")
     public String updateThongTin (@PathVariable String cccd,@RequestBody ThongTin thongTin) {
         return thongTinService.updateThongTin(cccd,thongTin);

     }
     @DeleteMapping("/delete/{cccd}")
     public String deleteThongTin (@PathVariable String cccd) {
            return thongTinService.deleteThongTin(cccd);
     }
     @PostMapping("/authenticate")
    public ApiResponse<?> authenticateThongTin (@RequestBody ThongTinRequest thongTinRequest){
           return thongTinService.authenticateThongTin(thongTinRequest);
     }
     @GetMapping("/notification/{cccd}")
    public List<ThongBao> getAllThongBaoOfThongTin (@PathVariable String cccd){
            return thongTinService.getAllThongBaoOfThongTin(cccd);
     }
 }
