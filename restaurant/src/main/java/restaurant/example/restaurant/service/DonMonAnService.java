package restaurant.example.restaurant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import restaurant.example.restaurant.dto.response.MonAnThuocDonMonAnResponse;
import restaurant.example.restaurant.entity.BaoGom;
import restaurant.example.restaurant.entity.DonMonAn;
import restaurant.example.restaurant.entity.DonMonAnBaoGomMonAn;
import restaurant.example.restaurant.exception.AppException;
import restaurant.example.restaurant.exception.ErrorCode;
import restaurant.example.restaurant.mapper.DonMonAnMapper;
import restaurant.example.restaurant.repository.DonMonAnBaoGomMonAnRepository;
import restaurant.example.restaurant.repository.DonMonAnRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonMonAnService {
             DonMonAnRepository donMonAnRepository;
             DonMonAnBaoGomMonAnRepository donMonAnBaoGomMonAnRepository;
             DonMonAnMapper donMonAnMapper;
    @PersistenceContext
    EntityManager entityManager;
  @Transactional
    public DonMonAn createDonMonAn (DonMonAn donMonAn) {
                   DonMonAn newDonMonAn = donMonAnMapper.toDonMonAn(donMonAn);
                   newDonMonAn.setMaDon(donMonAn.getMaDon());




                 List<BaoGom> listBaoGom = newDonMonAn.getListBaoGom();
                 List<BaoGom> newListBaoGom = new ArrayList<>();
               
                 for( int i =0 ; i < listBaoGom.size() ;i ++ ) {
                     if(listBaoGom.get(i)!=null){
                         BaoGom baoGom = listBaoGom.get(i);
                         if(baoGom.getMaBaoGom()!=null){
                             BaoGom existingBaoGom = entityManager.find(BaoGom.class,baoGom.getMaBaoGom());
                             if (existingBaoGom == null) {
                                 throw new IllegalArgumentException("Address with ID " + baoGom.getMaBaoGom()+ " does not exist.");
                             }
                             newListBaoGom.add(existingBaoGom);

                         }
                         else {
                             BaoGom newBaoGom = entityManager.merge(baoGom);
                             newListBaoGom.add(newBaoGom);
                         }
                     }
                 }
             newDonMonAn.setListBaoGom(newListBaoGom);



                    return donMonAnRepository.save(newDonMonAn);
             }
             public DonMonAn getSpecificDonMonAn (String maDonMonAn) {

                 return donMonAnRepository.findById(maDonMonAn)
                         .orElseThrow(()-> new AppException(ErrorCode.DONMONAN_NOT_EXIST));

             }
             public List<DonMonAn> getAllDonMonAn (){
                  return donMonAnRepository.findAll();
             }

             public String updateDonMonAn (String maDonMonAn, DonMonAn donMonAn) {
                  DonMonAn donMonAnUpdate =getSpecificDonMonAn(maDonMonAn);
                  donMonAnMapper.updateDonMonAn(donMonAnUpdate, donMonAn);
                  donMonAnRepository.save(donMonAnUpdate);
                  return "update success";

             }
             public String deleteDonMonAn (String maDonMonAn) {
                    donMonAnRepository.deleteById(maDonMonAn);
                    return "delete success";
             }
             public List<MonAnThuocDonMonAnResponse> getAllMonAnThuocDonMonAn (String maDonMonAn){
                 DonMonAnBaoGomMonAn donMonAnBaoGomMonAn = donMonAnBaoGomMonAnRepository.findById(maDonMonAn).orElseThrow(
                       ()-> new AppException(ErrorCode.DONMONAN_NOT_EXIST));
                         List<String> listMaMonAn =  Arrays.asList(donMonAnBaoGomMonAn.getCacMaMonAn().split(" "));
                         List<String> listSoLuongMonAnResponse = Arrays.asList(donMonAnBaoGomMonAn.getSoLuongMonAn().split(" "));


                 List<Integer> listSoLuongMonAn = listSoLuongMonAnResponse.stream()
                         .map(Integer::parseInt) // Chuyển từng chuỗi sang số nguyên
                         .collect(Collectors.toList());

                   List<DonMonAn> listDonMonAn = donMonAnRepository.findAllById(listSoLuongMonAnResponse);
                   List<MonAnThuocDonMonAnResponse> listResult = new ArrayList<>();


                   for(int i= 0; i< listDonMonAn.size() ; i++){
                        MonAnThuocDonMonAnResponse monAnThuocDonMonAnResponseTemporary = donMonAnMapper.toMonAnThuocDonMonAnResponse(listDonMonAn.get(i));
                       monAnThuocDonMonAnResponseTemporary.setSoLuongMonAn(listSoLuongMonAn.get(i));
                       listResult.add(monAnThuocDonMonAnResponseTemporary);
                   }
                   return listResult;


             }


}
