package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.DanhGia;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia,String> {
}

