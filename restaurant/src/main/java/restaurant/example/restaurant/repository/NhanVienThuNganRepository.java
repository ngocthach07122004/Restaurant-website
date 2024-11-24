package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.NhanVienThuNgan;

@Repository
public interface NhanVienThuNganRepository extends JpaRepository<NhanVienThuNgan,String> {
}

