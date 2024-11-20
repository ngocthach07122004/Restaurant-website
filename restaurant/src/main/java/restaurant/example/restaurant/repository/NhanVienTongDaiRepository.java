package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.NhanVienTongDai;

@Repository
public interface NhanVienTongDaiRepository extends JpaRepository<NhanVienTongDai,String> {
}

