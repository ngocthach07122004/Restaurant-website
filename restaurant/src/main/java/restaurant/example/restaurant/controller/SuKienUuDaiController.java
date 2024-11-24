package restaurant.example.restaurant.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import restaurant.example.restaurant.entity.SuKienUuDai;
import restaurant.example.restaurant.service.SuKienUuDaiService;

import java.util.List;

@RestController
@RequestMapping("/suKienUuDai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SuKienUuDaiController {
      SuKienUuDaiService suKienUuDaiService;
      @PostMapping("/create")
      public SuKienUuDai createSuKienUuDai (@RequestBody SuKienUuDai suKienUuDai) {
            return suKienUuDaiService.createSuKienUuDai(suKienUuDai);
     }
     @GetMapping("/{maUuDai}")
     public SuKienUuDai getSpecificSuKienUuDai  (@PathVariable String maSuKienUuDai) {

         return suKienUuDaiService.getSpecificSuKienUuDai(maSuKienUuDai);

     }
     @GetMapping("/all")
     public List<SuKienUuDai> getAllSuKienUuDai (){
          return suKienUuDaiService.getAllSuKienUuDai();
     }
     @PutMapping("/update/{maUuDai}")
     public String updateSuKienUuDai (@PathVariable String maSuKienUuDai,@RequestBody SuKienUuDai suKienUuDai) {
         return suKienUuDaiService.updateSuKienUuDai(maSuKienUuDai,suKienUuDai);

     }
     @DeleteMapping("/delete/{maUuDai}")
     public String deleteSuKienUuDai (@PathVariable String maUuDai) {
            return suKienUuDaiService.deleteSuKienUuDai(maUuDai);
     }
}
