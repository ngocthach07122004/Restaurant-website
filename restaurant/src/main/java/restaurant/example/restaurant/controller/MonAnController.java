package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
//import restaurant.example.restaurant.entity.DonMonAnBaoGomMonAn;
//import restaurant.example.restaurant.entity.MonAn;
//import restaurant.example.restaurant.service.DonMonAnBaoGomMonAnService;
import restaurant.example.restaurant.entity.MonAn;
import restaurant.example.restaurant.service.MonAnService;

import java.util.List;

@RestController
@RequestMapping("/monAn")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MonAnController {
      MonAnService monAnService;

//      @PostMapping("/create")
//      public MonAn createMonAn (@RequestBody MonAn monAn) {
//            return monAnService.createMonAn(monAn);
//     }
     @GetMapping("/{maMonAn}")
     public MonAn getSpecificMonAn  (@PathVariable String maMonAn) {

         return monAnService.getSpecificMonAn(maMonAn);

     }
     @GetMapping("/all")
     public List<MonAn> getAllMonAn (){
          return monAnService.getAllMonAn();
     }
     @PutMapping("/update/{maMonAn}")
     public String updateMonAn (@PathVariable String maMonAn,@RequestBody MonAn monAn) {
         return monAnService.updateMonAn(maMonAn,monAn);

     }
     @DeleteMapping("/delete/{maMonAn}")
     public String deleteMonAn (@PathVariable String maMonAn) {
            return monAnService.deleteMonAn(maMonAn);
     }

}
