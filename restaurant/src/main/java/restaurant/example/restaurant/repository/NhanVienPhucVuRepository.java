package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.NhanVienPhucVu;

@Repository
public interface NhanVienPhucVuRepository extends JpaRepository<NhanVienPhucVu,String> {
}

