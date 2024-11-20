package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,String> {
}

