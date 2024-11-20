package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.NhanVienGiaoHang;

@Repository
public interface NhanVienGiaoHangRepository extends JpaRepository<NhanVienGiaoHang,String> {
}

