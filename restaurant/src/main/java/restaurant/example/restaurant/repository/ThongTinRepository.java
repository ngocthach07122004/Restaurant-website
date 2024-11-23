package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.ThongTin;

import java.util.Optional;

@Repository
public interface ThongTinRepository extends JpaRepository<ThongTin,String> {
//   @Query("SELECT t FROM ThongTin t WHERE t.tenDangNhap = :tenDangNhap COLLATE utf8_bin")
   Optional<ThongTin> findByTenDangNhap(String tenDangNhap);
}

