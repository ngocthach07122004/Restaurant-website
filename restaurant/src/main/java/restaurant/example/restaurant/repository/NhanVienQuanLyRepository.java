package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.NhanVienQuanLy;

@Repository
public interface NhanVienQuanLyRepository extends JpaRepository<NhanVienQuanLy,String> {
}

