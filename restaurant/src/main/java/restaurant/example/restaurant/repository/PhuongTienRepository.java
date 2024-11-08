package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.controller.PhuongTienController;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.PhuongTien;

@Repository
public interface PhuongTienRepository extends JpaRepository<PhuongTien,String> {
}

