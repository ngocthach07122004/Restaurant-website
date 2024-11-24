package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.QuanTriVien;

@Repository
public interface QuanTriVienRepository extends JpaRepository<QuanTriVien,String> {
}

