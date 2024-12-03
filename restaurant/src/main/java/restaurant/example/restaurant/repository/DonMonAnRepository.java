package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.DonMonAn;

import java.util.List;

@Repository
public interface DonMonAnRepository extends JpaRepository<DonMonAn,String> {
//    @EntityGraph(attributePaths = {"listBaoGom"})
//    List<DonMonAn> findAll();
}

