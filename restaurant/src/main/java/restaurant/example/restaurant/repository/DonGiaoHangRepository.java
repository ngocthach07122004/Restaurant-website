package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.DonGiaoHang;

@Repository
public interface DonGiaoHangRepository extends JpaRepository<DonGiaoHang,String> {
}

