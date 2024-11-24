package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,String> {
}

